package com.vo.movie.forecast.bot.handler

import com.vo.movie.forecast.bot.configuration.BotProperties
import com.vo.movie.forecast.parser.api.MovieForecastParserApi
import com.vo.movie.forecast.parser.dto.Movie
import com.vo.movie.forecast.parser.dto.MovieSearchParams
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class MovieForecastBotHandler(private val botProperties: BotProperties,
                              private val movieForecastParserApi: MovieForecastParserApi) : TelegramLongPollingBot() {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun getBotUsername(): String {
        return botProperties.username!!
    }

    override fun getBotToken(): String {
        return botProperties.token!!
    }

    override fun onUpdateReceived(update: Update) {
        inlineQueryHandler(update)
    }

    private fun inlineQueryHandler(update: Update) {
        if (!update.hasInlineQuery()) {
            return
        }
        val inlineQuery = update.inlineQuery
        val query = inlineQuery.query
        if (query.isEmpty()) {
            return
        }
        val movies = movieForecastParserApi.searchMovie(MovieSearchParams(query))
        val inlineQueryResults = movies.toInlineQueryResults()
        val answerInlineQuery = createAnswerInlineQuery(inlineQuery.id, inlineQueryResults)
        try {
            execute(answerInlineQuery)
        } catch (e: TelegramApiException) {
            logger.error(e.message)
        }
    }

    private fun createAnswerInlineQuery(inlineQueryId: String, inlineQueryResults: List<InlineQueryResult>): AnswerInlineQuery {
        val answerInlineQuery = AnswerInlineQuery()
        answerInlineQuery.inlineQueryId = inlineQueryId
//        answerInlineQuery.cacheTime = 300
        answerInlineQuery.results = inlineQueryResults
        return answerInlineQuery
    }

    private fun List<Movie>.toInlineQueryResults(): List<InlineQueryResult> = this.map { it.toInlineQueryResultArticle() }

    private fun Movie.toInlineQueryResultArticle(): InlineQueryResultArticle {
        val article = InlineQueryResultArticle()
        article.inputMessageContent = toInputTextMessageContent()
        article.id = kinopoiskMovieId.toString()
        article.title = "$title ($year)"
        article.description = "$originalTitle ★$kinopoiskRating\n$genres"
        article.thumbUrl = smallPosterURL
//        article.thumbHeight =
//        article.thumbWidth =
        return article
    }

    private fun Movie.toInputTextMessageContent(): InputTextMessageContent {
        val messageContent = InputTextMessageContent()
        messageContent.disableWebPagePreview()
        messageContent.enableMarkdown(true)
        messageContent.messageText = title
        return messageContent
    }
}