package com.vo.movie.forecast.backend.core.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user", schema = "public")
data class UserEntity(

        @Id
        @Column(name = "user_id", nullable = false, unique = true)
        val userId: Long = 0,

        @Column(name = "chat_id", nullable = false)
        var chatId: Long
)