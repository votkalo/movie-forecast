package com.vo.movie.forecast.bot.handler.inline

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.bot.handler.callback.Callback
import com.vo.movie.forecast.bot.message.movie.MovieMessageCreator
import com.vo.movie.forecast.bot.util.*
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.movie.MovieSearchParamsDTO
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

@Component
class InlineQueryHandler(private val movieApi: MovieApi,
                         private val movieMessageCreator: MovieMessageCreator) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean =
        update.hasInlineQuery() && update.inlineQuery.hasQuery() && update.inlineQuery.query.isNotBlank()

    override fun handle(update: Update) {
        val inlineQuery = update.inlineQuery
        val movies =
            call({ movieApi.searchMovie(MovieSearchParamsDTO(inlineQuery.query)) }, update.chatId())
        val inlineQueryResults = movies.toInlineQueryResults()
        val answerInlineQuery = createAnswerInlineQuery(inlineQuery.id, inlineQueryResults)
        getBot().execute(answerInlineQuery)
    }

    private fun createAnswerInlineQuery(inlineQueryId: String,
                                        inlineQueryResults: List<InlineQueryResult>): AnswerInlineQuery {
        val answerInlineQuery = AnswerInlineQuery()
        answerInlineQuery.inlineQueryId = inlineQueryId
        answerInlineQuery.results = inlineQueryResults
        return answerInlineQuery
    }

    private fun List<MovieDTO>.toInlineQueryResults(): List<InlineQueryResult> =
        this.map { it.toInlineQueryResultArticle() }

    private fun MovieDTO.toInlineQueryResultArticle(): InlineQueryResultArticle {
        val article = InlineQueryResultArticle()
        article.inputMessageContent = toInputTextMessageContent()
        article.id = kinopoiskMovieId.toString()
        article.title = movieMessageCreator.createMovieTitle(this)
        article.description = movieMessageCreator.createShortMovieDescription(this)
        article.thumbUrl = smallPosterURL
        article.replyMarkup = createFollowButtonMarkup()
        return article
    }


    private fun MovieDTO.toInputTextMessageContent(): InputTextMessageContent {
        val messageContent = InputTextMessageContent()
        messageContent.enableWebPagePreview()
        messageContent.enableHtml(true)
        messageContent.messageText = movieMessageCreator.createFullMovieInfo(this)
        return messageContent
    }

    private fun MovieDTO.createFollowButtonMarkup(): InlineKeyboardMarkup {
        val button = createInlineKeyboardButton(
                "Добавить в отслеживаемые",
                Callback.MOVIE_ID.addCallbackPrefix(kinopoiskMovieId.toString())
        )
        val keyboard = createOneRowButton(button)
        return createInlineKeyboardMarkup(keyboard)
    }
}