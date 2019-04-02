package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.user.api.bot.MovieApi
import com.vo.movie.forecast.bot.util.call
import com.vo.movie.forecast.commons.data.MovieInfo
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieInfoCallbackHandler(private val movieApi: MovieApi) : CallbackUpdateHandler(Callback.MOVIE_INFO) {

    override fun handle(update: Update) {
        val userId = update.userId()
        val movieInfo = parseCallbackData(update.getCallbackData())
        val movie = call({ movieApi.searchMovie(movieInfo, userId) }, update.chatId())
        call({ movieApi.deleteMovie(userId, movie.kinopoiskMovieId) }, update.chatId())
        val editMessageText = update.createEditMessageText("Фильм <b>${movie.title} (${movie.year})</b> удалён из отслеживаемых")
        getBot().execute(editMessageText)
    }

    private fun parseCallbackData(callbackData: String): MovieInfo {
        if (callbackData.contains('∫')) {
            val movieInfo = callbackData.split('∫')
            return MovieInfo(movieInfo[0], null, movieInfo[1])
        }
        return MovieInfo(callbackData, null, null)
    }
}