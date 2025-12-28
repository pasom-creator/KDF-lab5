package com.example.botinterface.bot.service

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class LocalizationService(
    private val messageSource: MessageSource
) {
    private var locale: Locale? = null

    fun getLocalizedMessage(key: String, vararg args: Any): String {
        return messageSource.getMessage(key, args, locale)
    }

    fun setLocale(locale: Locale) {
        this.locale = locale
    }
}