package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.bot.MovieApi
import com.vo.movie.forecast.commons.data.MovieInfo
import feign.FeignException
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieInfoCallbackHandler(private val movieApi: MovieApi) : CallbackUpdateHandler(Callback.MOVIE_INFO) {

    override fun handle(update: Update) {
        try {
            val userId = update.userId()
            val movieInfo = parseCallbackData(update.getCallbackData())
            val movie = movieApi.searchMovie(movieInfo, userId)
            movieApi.deleteMovie(userId, movie.kinopoiskMovieId)
            val editMessageText = update.createEditMessageText("Фильм <b>${movie.title} (${movie.year})</b> удалён из отслеживаемых")
            getBot().execute(editMessageText)
        } catch (e: FeignException) {
            val answerCallbackQuery = AnswerCallbackQuery()
            answerCallbackQuery.callbackQueryId = update.callbackQuery.id
            answerCallbackQuery.showAlert = true
            //TODO: вынести сообщения ошибок в отдельный enum
            answerCallbackQuery.text = "К сожалению данная функция сейчас не доступна"
            getBot().execute(answerCallbackQuery)
        }
    }

    private fun parseCallbackData(callbackData: String): MovieInfo {
        if (callbackData.contains('∫')) {
            val movieInfo = callbackData.split('∫')
            return MovieInfo(movieInfo[0], null, movieInfo[1])
        }
        return MovieInfo(callbackData, null, null)
    }
}