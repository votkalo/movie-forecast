package com.vo.movie.collector.entity

import org.hibernate.annotations.Immutable
import javax.persistence.*

@Entity
@Immutable
@Table(name = "country", schema = "public")
data class CountryEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, unique = true)
        val id: Long = 0,

        @Column(name = "name", nullable = false, unique = true)
        val name: String
)