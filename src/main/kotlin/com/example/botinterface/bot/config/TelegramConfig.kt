package com.example.botinterface.bot.config

import com.example.botinterface.bot.MainBot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.generics.TelegramClient

@Configuration
class TelegramConfig {
    @Bean
    fun telegramClient(mainBot: MainBot): TelegramClient {
        return OkHttpTelegramClient(mainBot.botToken)
    }
}