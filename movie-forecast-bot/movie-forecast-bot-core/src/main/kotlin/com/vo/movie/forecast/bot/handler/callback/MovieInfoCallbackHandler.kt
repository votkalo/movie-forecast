package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.api.UserApi
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieInfoCallbackHandler(private val userApi: UserApi) : CallbackUpdateHandler(Callback.MOVIE_INFO) {

    override fun handle(update: Update) {
        val kinopoiskMovieId = update.getCallbackData()
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        //TODO: UserId equals ChatId
        if (userApi.isUserExist(update.userId())) {
            answerCallbackQuery.text = "Фильм добавлен в отслеживаемые"
        } else {
            answerCallbackQuery.text = "Создайте персональный чат с ботом для отслеживания фильма"
            answerCallbackQuery.showAlert = true
        }
        getBot().execute(answerCallbackQuery)
    }

}