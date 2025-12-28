package com.example.botinterface.bot.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import org.telegram.telegrambots.meta.generics.TelegramClient
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.Serializable

@Component
class EventListener(
    private val telegramClient: TelegramClient
) {
    @EventListener
    @Throws(TelegramApiException::class)
    fun on(event: MessageEvent) {
//        @Suppress("UNCHECKED_CAST")
        telegramClient.execute(event.message as BotApiMethod<Serializable>)
    }
}