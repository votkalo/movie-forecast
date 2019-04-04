package com.vo.movie.forecast.bot.handler.answer

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.AddressComponentType
import com.google.maps.model.AddressType
import com.google.maps.model.LatLng
import com.vo.movie.forecast.backend.storage.api.LocalityApi
import com.vo.movie.forecast.backend.user.api.bot.UserApi
import com.vo.movie.forecast.bot.configuration.GeocodingProperties
import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.bot.util.call
import com.vo.movie.forecast.bot.util.createMessage
import com.vo.movie.forecast.bot.util.createSearchLocalityInlineKeyboardMarkup
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove

@Component
class LocationAnswerHandler(private val geocodingProperties: GeocodingProperties,
                            private val localityApi: LocalityApi,
                            private val userApi: UserApi) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean = update.hasMessage() && update.message.hasLocation()

    override fun handle(update: Update) {
        val chatId: Long = update.chatId()
        var message = createMessage(chatId, "Геолокация получена, идёт поиск населённого пункта", ReplyKeyboardRemove())
        getBot().execute(message)

        val location = update.message.location
        val geolocationLocality = getLocality(location.latitude, location.longitude)

        val inlineKeyboardMarkup = createSearchLocalityInlineKeyboardMarkup()

        if (geolocationLocality == null) {
            message =
                createMessage(chatId, "В данный момент вы находитесь вне населённого пункта", inlineKeyboardMarkup)
            getBot().execute(message)
            return
        }

        val isLocalityKnown = call(
                { localityApi.getLocalities() },
                update.chatId()
        ).any { locality -> locality.name == geolocationLocality }

        if (isLocalityKnown) {
            call(
                    {
                        userApi.updateLocality(
                                update.userId(),
                                localityApi.getLocalityByName(geolocationLocality)
                        )
                    },
                    update.chatId()
            )
            message = createMessage(chatId, "Ваш текущий населённый пункт обновлён на:\n<b>$geolocationLocality</b>")
            getBot().execute(message)
            return
        }

        message = createMessage(
                chatId,
                "К сожалению, нам неизвестно о кинотеатрах вашего населённого пункта:\n<b>$geolocationLocality</b>",
                inlineKeyboardMarkup
        )
        getBot().execute(message)
    }

    private fun getLocality(latitude: Float, longitude: Float): String? {
        val context = GeoApiContext.Builder().apiKey(geocodingProperties.apiKey).build()

        val request = GeocodingApi
            .newRequest(context)
            .language("ru")
            .resultType(AddressType.LOCALITY)
            .latlng(LatLng(latitude.toDouble(), longitude.toDouble()))

        return request.await().flatMap {
            it.addressComponents
                .filter { address -> address.types.contains(AddressComponentType.LOCALITY) }
                .map { address -> address.longName }
        }.getOrNull(0)
    }
}