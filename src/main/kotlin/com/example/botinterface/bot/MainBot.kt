package com.example.botinterface.bot

import com.example.botinterface.bot.commands.CommandHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MainBot(
    private val commandHandler: CommandHandler
) : SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    @Value("\${telegram.bot.token}")
    private lateinit var botToken: String

    override fun getBotToken(): String {
        return botToken
    }

    override fun getUpdatesConsumer(): LongPollingUpdateConsumer {
        return this
    }

    override fun consume(update: Update) {
        commandHandler.handle(update)
    }
}