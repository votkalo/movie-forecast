package com.vo.movie.forecast.backend.core.dao.impl

import com.vo.movie.forecast.backend.core.document.User
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

fun criteriaUserId(userId: Long): Criteria = Criteria.where(User.PROPERTY_USER_USER_ID).`is`(userId)

fun queryUser(userId: Long) = Query(criteriaUserId(userId))
