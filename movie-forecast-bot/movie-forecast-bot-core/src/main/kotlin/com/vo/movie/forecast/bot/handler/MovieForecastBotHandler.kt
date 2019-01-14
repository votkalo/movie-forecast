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
        answerInlineQuery.results = inlineQueryResults
        return answerInlineQuery
    }

    private fun List<Movie>.toInlineQueryResults(): List<InlineQueryResult> = this.map { it.toInlineQueryResultArticle() }

    private fun Movie.toInlineQueryResultArticle(): InlineQueryResultArticle {
        val article = InlineQueryResultArticle()
        article.inputMessageContent = toInputTextMessageContent()
        article.id = kinopoiskMovieId.toString()
        article.title = createArticleTitle()
        article.description = createArticleDescription()
        article.thumbUrl = smallPosterURL
        return article
    }

    private fun Movie.createArticleTitle(): String {
        val titleBuilder = StringBuilder()
        titleBuilder.append(title)
        if (year != null) {
            titleBuilder.append(" ($year)")
        }
        return titleBuilder.toString()
    }

    private fun Movie.createArticleDescription(): String {
        val descriptionBuilder = StringBuilder()
        if (originalTitle != null) {
            descriptionBuilder.append("$originalTitle ")
        }
        if (kinopoiskRating != null) {
            descriptionBuilder.append("★$kinopoiskRating")
        }
        if (genres != null) {
            descriptionBuilder.append("\n$genres")
        }
        if (countries != null) {
            descriptionBuilder.append("\n$countries")
        }
        return descriptionBuilder.toString()
    }

    private fun Movie.toInputTextMessageContent(): InputTextMessageContent {
        val messageContent = InputTextMessageContent()
        messageContent.enableWebPagePreview()
        messageContent.enableHtml(true)
        messageContent.messageText = createMessageText()
        return messageContent
    }

    private fun Movie.createMessageText(): String {
        val messageBuilder = StringBuilder()
        messageBuilder.append("<b>")
        messageBuilder.append(title)
        if (year != null) {
            messageBuilder.append(" ($year)")
        }
        messageBuilder.append("</b>")
        if (originalTitle != null) {
            messageBuilder.append("\n<i>$originalTitle</i>")
        }
        messageBuilder.append("\n<a href=\"$bigPosterURL\">&#8205;</a>")
        messageBuilder.append("\n<a href=\"$sourceURL\">Кинопоиск</a>")
        if (kinopoiskRating != null) {
            messageBuilder.append(" &#x2B50; $kinopoiskRating")
        }
        if (genres != null) {
            messageBuilder.append("\nЖанр: $genres")
        }
        if (countries != null) {
            messageBuilder.append("\nСтрана: $countries")
        }
        return messageBuilder.toString()
    }
}