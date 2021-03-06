package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.bot.util.call
import feign.FeignException
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieIdCallbackHandler(private val movieApi: MovieApi,
                             private val userMovieApi: UserMovieApi) : CallbackUpdateHandler(Callback.MOVIE_ID) {

    override fun handle(update: Update) {
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        registerMovie(update.userId(), update.getCallbackData().toLong())
        answerCallbackQuery.text = "Фильм добавлен в отслеживаемые"
        getBot().execute(answerCallbackQuery)
    }

    @Throws(FeignException::class)
    private fun registerMovie(userId: Long, kinopoiskMovieId: Long) {
        if (!call({ userMovieApi.existsMovie(userId, kinopoiskMovieId) }, userId)) {
            call({ userMovieApi.registerMovie(movieApi.getMovie(kinopoiskMovieId), userId) }, userId)
        }
    }
}