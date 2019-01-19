package com.vo.movie.forecast.bot.handler.inline

import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.parser.api.MovieForecastParserApi
import com.vo.movie.forecast.parser.dto.Movie
import com.vo.movie.forecast.parser.dto.MovieSearchParams
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle

@Component
class InlineQueryHandler(private val movieForecastParserApi: MovieForecastParserApi) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean = update.hasInlineQuery()
            && update.inlineQuery.hasQuery()
            && update.inlineQuery.query.isNotBlank()

    override fun handle(update: Update) {
        val inlineQuery = update.inlineQuery

        val usefulQuery = inlineQuery.query.substring(3)

        val movies = movieForecastParserApi.searchMovie(MovieSearchParams(usefulQuery))
        val inlineQueryResults = movies.toInlineQueryResults()
        val answerInlineQuery = createAnswerInlineQuery(inlineQuery.id, inlineQueryResults)
        getBot().execute(answerInlineQuery)
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