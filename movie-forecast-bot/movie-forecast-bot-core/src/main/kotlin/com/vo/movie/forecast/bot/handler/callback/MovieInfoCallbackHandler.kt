package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.api.UserApi
import com.vo.movie.forecast.parser.provider.MovieProvider
import feign.FeignException
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieInfoCallbackHandler(private val userApi: UserApi,
                               private val movieProvider: MovieProvider) : CallbackUpdateHandler(Callback.MOVIE_INFO) {

    override fun handle(update: Update) {
        val movie = movieProvider.getMovie(update.getCallbackData().toLong())
        val userId = update.userId()
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        try {
            userApi.registerMovie(userId, movie)
            answerCallbackQuery.text = "Фильм добавлен в отслеживаемые"
        } catch (e: FeignException) {
            answerCallbackQuery.showAlert = true
            answerCallbackQuery.text = "К сожалению данная функция сейчас не доступна"
        }
        getBot().execute(answerCallbackQuery)
    }
}