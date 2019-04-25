package com.vo.movie.forecast.bot.message.schedule

import com.vo.movie.forecast.bot.message.commons.MessageCreator
import com.vo.movie.forecast.bot.message.movie.MovieMessageCreator
import com.vo.movie.forecast.parser.dto.schedule.CinemaScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.SessionScheduleDTO
import org.springframework.stereotype.Component

@Component
class ScheduleBotMessageCreator(private val movieMessageCreator: MovieMessageCreator) : MessageCreator(),
                                                                                        ScheduleMessageCreator {

    override fun createSchedule(localityName: String, notificationMoviesSchedule: List<MovieScheduleDTO>): String =
        notificationMoviesSchedule.scheduleTemplate(localityName)

    private fun List<MovieScheduleDTO>.scheduleTemplate(localityName: String): String = """
        |Расписание отслеживаемых фильмов на сегодня в <b>$localityName</b>:

        |${joinToString("\n\n") { it.scheduleTemplate() }}
    """.trimMargin()

    private fun MovieScheduleDTO.scheduleTemplate(): String = """
        |<b>${movieMessageCreator.createMovieTitle(title, year)}</b>${valueOrEmpty(originalTitle, "\n|<i>$originalTitle</i>")}
        |${cinemas.joinToString("\n") { it.scheduleTemplate() }}
    """.trimMargin()

    private fun CinemaScheduleDTO.scheduleTemplate(): String = """
        |📍$name
        |🕒${sessions.joinToString("   ") { it.scheduleTemplate() }}
    """.trimMargin()

    private fun SessionScheduleDTO.scheduleTemplate(): String = "$time${valueOrEmpty(is3D, "(3D)")}"
}