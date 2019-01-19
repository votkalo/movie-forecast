package com.vo.movie.forecast.bot.handler.answer

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.AddressComponentType
import com.google.maps.model.GeocodingResult
import com.google.maps.model.LatLng
import com.vo.movie.forecast.bot.configuration.GeocodingProperties
import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.bot.handler.command.Command
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.util.*

@Component
class LocationAnswerHandler(private val geocodingProperties: GeocodingProperties) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean = update.hasMessage() && update.message.hasLocation()

    override fun handle(update: Update) {
        val location = update.message.location

        val message1 = SendMessage()
        message1.chatId = update.message.chatId.toString()
        message1.text = "Геолокация получена, идёт поиск населённого пунтка."
        message1.replyMarkup = ReplyKeyboardRemove()
        getBot().execute(message1)

        val context = GeoApiContext.Builder()
                .apiKey(geocodingProperties.apiKey)
                .build()

        val request = GeocodingApi.newRequest(context)
                .language("ru")
                .latlng(LatLng(location.latitude.toDouble(), location.longitude.toDouble()))

        val results = request.await()
        val cities = getPossibleCities(results)

        if (cities.isEmpty()) {

        }

        val buttons: MutableList<List<InlineKeyboardButton>> = cities.map {
            val inlineKeyboardButton = InlineKeyboardButton()
            inlineKeyboardButton.text = it
            inlineKeyboardButton.callbackData = it
            Collections.singletonList(inlineKeyboardButton)
        }.toMutableList()

        val inlineKeyboardButton = InlineKeyboardButton()
        inlineKeyboardButton.text = "Выбрать населённый пункт"
        inlineKeyboardButton.callbackData = Command.LOCALITY.value
        buttons.add(Collections.singletonList(inlineKeyboardButton))

        val inlineKeyboardMarkup = InlineKeyboardMarkup()
        inlineKeyboardMarkup.keyboard = buttons

        val message = SendMessage()
        message.chatId = update.message.chatId.toString()
        message.text = CityCallbackQueryHandler.CITY_CALLBACK_QUERY_MESSAGE
        message.replyMarkup = inlineKeyboardMarkup
        message.enableMarkdown(true)
        getBot().execute(message)
    }

    private fun getPossibleCities(results: Array<GeocodingResult>): List<String> {
        return results.flatMap {
            it.addressComponents
                    .filter { address ->
                        address.types.contains(AddressComponentType.LOCALITY) && address.types.contains(AddressComponentType.POLITICAL)
                    }
                    .map { address -> address.longName }
        }.distinct()
    }
}