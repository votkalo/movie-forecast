package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.bot.message.movie.MovieMessageCreator
import com.vo.movie.forecast.bot.util.call
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieDescriptionCallbackHandler(private val movieApi: MovieApi,
                                      private val movieMessageCreator: MovieMessageCreator) :
        CallbackUpdateHandler(Callback.MOVIE_DESCRIPTION) {

    override fun handle(update: Update) {
        val kinopoiskMovieId = update.getCallbackData()
        val movie = call({ movieApi.getMovie(kinopoiskMovieId.toLong()) }, update.chatId())
        getBot().execute(update.createEditMessageText(movieMessageCreator.createFullMovieInfo(movie)))
    }
}