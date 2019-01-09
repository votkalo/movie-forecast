package com.vo.movie.collector.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "movie", schema = "public")
data class MovieEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, unique = true)
        val id: Long = 0,

        @Column(name = "year", nullable = false)
        val year: Int,

        @Column(name = "russian_title", nullable = false)
        val russianTitle: String,

        @Column(name = "alternative_title", nullable = false)
        val alternativeTitle: String,

        @Column(name = "source_url", nullable = false)
        val sourceURL: String,

        @Column(name = "russian_premiere", nullable = false)
        val russianPremiere: LocalDate,

        @Column(name = "world_premiere")
        val worldPremiere: LocalDate? = null,

        @Column(name = "tagline")
        val tagline: String? = null,

        @Column(name = "blu_ray_release")
        val bluRayRelease: LocalDate? = null,

        @Column(name = "digital_release")
        val digitalRelease: LocalDate? = null,

        @Column(name = "re_release")
        val reRelease: LocalDate? = null,

        @Column(name = "allow_age")
        val allowAge: String? = null,

        @Column(name = "pg")
        val pg: String? = null,

        @Column(name = "duration")
        val duration: String? = null,

        @Column(name = "kinopoisk_rating")
        val kinopoiskRating: BigDecimal? = null,

        @Column(name = "imdb_rating")
        val imdbRating: BigDecimal? = null,

        @Column(name = "poster_url")
        val posterURL: String? = null,

        @CreationTimestamp
        @Column(name = "created", nullable = false)
        val created: Instant? = null,

        @UpdateTimestamp
        @Column(name = "updated", nullable = false)
        val updated: Instant? = null,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "movie_country",
                joinColumns = [JoinColumn(name = "fk_movie_id")],
                inverseJoinColumns = [JoinColumn(name = "fk_country_id")])
        val counties: MutableList<CountryEntity> = ArrayList(),

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "movie_genre",
                joinColumns = [JoinColumn(name = "fk_movie_id")],
                inverseJoinColumns = [JoinColumn(name = "fk_genre_id")])
        val genres: MutableList<GenreEntity> = ArrayList()
)