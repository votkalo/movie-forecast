package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.api.FollowApi
import com.vo.movie.forecast.backend.api.dto.Follow
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieInfoCallbackHandler(private val followApi: FollowApi) : CallbackUpdateHandler(Callback.MOVIE_INFO) {

    override fun handle(update: Update) {
        val kinopoiskMovieId = update.getCallbackData().toLong()
        val userId = update.chatId()
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        try {
            followApi.registerFollow(Follow(userId, kinopoiskMovieId))
            answerCallbackQuery.text = "Фильм добавлен в отслеживаемые"
        } catch (e: FeignException) {
            handleFeignException(e, answerCallbackQuery)
        }
        getBot().execute(answerCallbackQuery)
    }

    private fun handleFeignException(exception: FeignException, answerCallbackQuery: AnswerCallbackQuery) {
        if (exception.status() == HttpStatus.CONFLICT.value()) {
            answerCallbackQuery.text = "Фильм уже отслеживается"
        } else {
            answerCallbackQuery.showAlert = true
            answerCallbackQuery.text = "К сожалению данная функция сейчас не доступна"
        }
    }

}