package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.bot.util.call
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieInfoCallbackHandler(private val userMovieApi: UserMovieApi,
                               private val movieApi: MovieApi) : CallbackUpdateHandler(Callback.MOVIE_INFO) {

    override fun handle(update: Update) {
        val userId = update.userId()
        val kinopoiskMovieId = update.getCallbackData()
        val movie = call({ movieApi.getMovie(kinopoiskMovieId.toLong()) }, update.chatId())
        call({ userMovieApi.deleteMovie(userId, movie.kinopoiskMovieId) }, update.chatId())
        val editMessageText =
            update.createEditMessageText("Фильм <b>${movie.title} (${movie.year})</b> удалён из отслеживаемых")
        getBot().execute(editMessageText)
    }
}