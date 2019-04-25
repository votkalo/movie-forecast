package com.vo.movie.forecast.bot.message.online.cinema

import com.vo.movie.forecast.bot.message.commons.MessageCreator
import com.vo.movie.forecast.bot.message.movie.MovieMessageCreator
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class OnlineCinemaBotMessageCreator(private val movieMessageCreator: MovieMessageCreator) : MessageCreator(), OnlineCinemaMessageCreator {

    override fun createMoviesAccess(onlineCinema: OnlineCinemaDTO,
                                    notificationMovieAccessInfoList: List<MovieAccessDTO>): String {
        return notificationMovieAccessInfoList.moviesAccessTemplate(onlineCinema)
    }

    //TODO: Выводить фильм и снижу список кинотеатров и информацию о ильме в соответствуюем кинотеатре
    private fun List<MovieAccessDTO>.moviesAccessTemplate(onlineCinema: OnlineCinemaDTO): String = """
        |Информация отслеживаемых фильмов в онлайн-кинотеатре <b>$onlineCinema</b>:

        |${joinToString("\n\n") { it.movieAccessTemplate() }}
    """.trimMargin()

    private fun MovieAccessDTO.movieAccessTemplate(): String = """
        |<b>${movieMessageCreator.createMovieTitle(title, year)}</b>
        |${valueOrEmpty(originalTitle, "<i>$originalTitle</i>")}
        |${valueOrEmpty(isAllowBySubscription, "\uD83D\uDD14Подписка ")
        .plus(valueOrEmpty(price.compareTo(BigDecimal.ZERO) == 1, "\uD83D\uDC8EПокупка(от ${price} ${currency}) "))
        .plus(valueOrEmpty(isPreOrder, "\uD83D\uDCC5Предзаказ "))
        .plus(valueOrEmpty(!isAllowBySubscription && !isPreOrder && price.compareTo(BigDecimal.ZERO) == 0, "\uD83C\uDF81Бесплатно "))}
    """.trimMargin()
}