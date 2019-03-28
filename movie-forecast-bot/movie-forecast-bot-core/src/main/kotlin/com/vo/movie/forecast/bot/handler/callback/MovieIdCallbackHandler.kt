package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.bot.MovieApi
import com.vo.movie.forecast.parser.provider.movie.MovieProvider
import feign.FeignException
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieIdCallbackHandler(private val movieApi: MovieApi,
                             private val movieProvider: MovieProvider) : CallbackUpdateHandler(Callback.MOVIE_ID) {

    override fun handle(update: Update) {
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        try {
            registerMovie(update.userId(), update.getCallbackData().toLong())
            answerCallbackQuery.text = "Фильм добавлен в отслеживаемые"
        } catch (e: FeignException) {
            answerCallbackQuery.showAlert = true
            answerCallbackQuery.text = "К сожалению данная функция сейчас не доступна"
        }
        getBot().execute(answerCallbackQuery)
    }

    @Throws(FeignException::class)
    private fun registerMovie(userId: Long, kinopoiskMovieId: Long) {
        if (!movieApi.existsMovie(userId, kinopoiskMovieId)) {
            movieApi.registerMovie(movieProvider.getMovie(kinopoiskMovieId), userId)
        }
    }
}