package com.example.botinterface.bot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Service
class KeyboardService(
    private val localizationService: LocalizationService
) {

    fun mainMenu(): ReplyKeyboard {
        val keyboard = mutableListOf<KeyboardRow>()

        val row1 = KeyboardRow()
        row1.add(localizationService.getLocalizedMessage("menu.about"))
        keyboard.add(row1)

        val row2 = KeyboardRow()
        row2.add(localizationService.getLocalizedMessage("menu.language"))
        keyboard.add(row2)

        val row3 = KeyboardRow()
        row3.add(localizationService.getLocalizedMessage("menu.upload"))
        keyboard.add(row3)

        val keyboardMarkup = ReplyKeyboardMarkup(keyboard)
        keyboardMarkup.resizeKeyboard = true
        keyboardMarkup.oneTimeKeyboard = false

        return keyboardMarkup
    }
}