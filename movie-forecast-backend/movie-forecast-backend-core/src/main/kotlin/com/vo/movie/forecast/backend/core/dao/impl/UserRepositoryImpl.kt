package com.vo.movie.forecast.backend.core.dao.impl

import com.vo.movie.forecast.backend.core.dao.UserRepository
import com.vo.movie.forecast.backend.core.document.User
import com.vo.movie.forecast.backend.core.document.User.Companion.DOCUMENT_USER_NAME
import com.vo.movie.forecast.backend.core.document.User.Companion.PROPERTY_USER_LOCALITY
import com.vo.movie.forecast.backend.core.document.User.Companion.PROPERTY_USER_USER_ID
import com.vo.movie.forecast.backend.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.upsert
import org.springframework.stereotype.Repository

@Repository
open class UserRepositoryImpl(private val mongoOperation: MongoOperations) : UserRepository {

    override fun updateLocality(userId: Long, locality: Locality) {
        mongoOperation.upsert(queryUser(userId), Update().set(PROPERTY_USER_LOCALITY, locality), User::class)
    }

    override fun removeLocality(userId: Long) {
        mongoOperation.findAndModify(queryUser(userId), Update().unset(PROPERTY_USER_LOCALITY), User::class.java)
    }

    override fun getUsersInfoWithLocality(page: Int, size: Int): List<UserInfo> {
        val pageRequest = PageRequest.of(page, size)
        val query = Query(Criteria.where(PROPERTY_USER_LOCALITY).exists(true))
                .with(pageRequest)
        query.fields()
                .include(PROPERTY_USER_USER_ID)
                .include(PROPERTY_USER_LOCALITY)
        return mongoOperation.find(query, DOCUMENT_USER_NAME)
    }

    override fun getUsersIds(page: Int, size: Int): List<Long> =
            mongoOperation.findDistinct(Query().with(PageRequest.of(page, size)), PROPERTY_USER_USER_ID, DOCUMENT_USER_NAME, Long::class.java)
}