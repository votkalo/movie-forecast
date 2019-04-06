package com.vo.movie.forecast.backend.storage.core.worker

import com.vo.movie.forecast.backend.storage.core.updater.LocalityUpdater
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component
class LocalityWorker(private val localityApi: LocalityApi,
                     private val localityUpdater: LocalityUpdater) {

    @Scheduled(cron = "0 0 0 1 * ?", zone = "Europe/Minsk")
    fun cleanUpdateCaches() {
        localityUpdater.update(localityApi.getLocalities())
    }

}