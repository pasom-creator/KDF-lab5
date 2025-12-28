package com.example.botinterface.bot.commands

import com.example.botinterface.bot.events.MessageEvent
import com.example.botinterface.bot.service.LocalizationService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow

@Component
class LanguageCommand(
    private val eventPublisher: ApplicationEventPublisher,
    private val localizationService: LocalizationService
) : Command {

    companion object {
        const val LANG_RUS = "lang_rus"
        const val LANG_EN = "lang_en"
    }

    override fun canHandle(update: Update): Boolean {
        if (!update.hasMessage() || !update.message.hasText()) {
            return false
        }
        val localizedMessage = localizationService.getLocalizedMessage("menu.language")
        return update.message.text == localizedMessage
    }

    override fun handle(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val chatId = update.message.chatId
            val localizedMessage = localizationService.getLocalizedMessage("language.select")

            val message = SendMessage.builder()
                .chatId(chatId)
                .text(localizedMessage)
                .replyMarkup(languageInline())
                .build()
            eventPublisher.publishEvent(MessageEvent(this, message))
        }
    }

    private fun languageInline(): ReplyKeyboard {
        val rows = mutableListOf<InlineKeyboardRow>()
        rows.add(
            InlineKeyboardRow(
                InlineKeyboardButton.builder()
                    .text(localizationService.getLocalizedMessage("language.ru"))
                    .callbackData(LANG_RUS)
                    .build()
            )
        )
        rows.add(
            InlineKeyboardRow(
                InlineKeyboardButton.builder()
                    .text(localizationService.getLocalizedMessage("language.en"))
                    .callbackData(LANG_EN)
                    .build()
            )
        )
        return InlineKeyboardMarkup.builder().keyboard(rows).build()
    }

    override fun getCommand(): String {
        return CommandName.LANGUAGE.commandName
    }
}