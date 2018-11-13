package com.vo.movie.db.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class MovieEntity(@Id @GeneratedValue @Column(name = "id") val id: Long = 0,
                       @Column(name = "title") val title: String) {

    @Column(name = "year")
    var year: Int? = null

    @Column(name = "county")
    lateinit var county: String
}