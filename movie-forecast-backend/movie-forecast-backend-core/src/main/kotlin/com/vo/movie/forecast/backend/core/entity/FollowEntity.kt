package com.vo.movie.forecast.backend.core.entity

import javax.persistence.*

@Entity
@Table(name = "follow", schema = "public")
data class FollowEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, unique = true)
        val id: Long = 0,

        @Column(name = "telegram_user_id", nullable = false)
        val telegramUserId: Long,

        @Column(name = "kinopoisk_movie_id", nullable = false)
        var kinopoiskMovieId: Long
)