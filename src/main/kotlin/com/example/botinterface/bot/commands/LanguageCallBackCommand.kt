package com.example.botinterface.bot.commands

import com.example.botinterface.bot.events.MessageEvent
import com.example.botinterface.bot.service.KeyboardService
import com.example.botinterface.bot.service.LocalizationService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.Locale

@Component
class LanguageCallBackCommand(
    private val eventPublisher: ApplicationEventPublisher,
    private val localizationService: LocalizationService,
    private val keyboardService: KeyboardService
) : Command {

    override fun canHandle(update: Update): Boolean {
        if (!update.hasCallbackQuery()) {
            return false
        }
        val callbackData = update.callbackQuery.data
        return callbackData == LanguageCommand.LANG_RUS ||
                callbackData == LanguageCommand.LANG_EN
    }

    override fun handle(update: Update) {
        val chatId = update.callbackQuery.message.chatId
        val callbackData = update.callbackQuery.data
        val messageId = update.callbackQuery.message.messageId

        when (callbackData) {
            LanguageCommand.LANG_RUS -> {
                localizationService.setLocale(Locale("ru"))
                changeLanguage(chatId, messageId)
            }

            LanguageCommand.LANG_EN -> {
                localizationService.setLocale(Locale.ENGLISH)
                changeLanguage(chatId, messageId)
            }
        }
    }

    private fun changeLanguage(chatId: Long, messageId: Int) {
        val switched = localizationService.getLocalizedMessage("language.switched")
        val editMessage = EditMessageText.builder()
            .chatId(chatId)
            .messageId(messageId)
            .text(switched)
            .build()
        eventPublisher.publishEvent(MessageEvent(this, editMessage))

        val message = SendMessage.builder()
            .chatId(chatId)
            .text(localizationService.getLocalizedMessage("menu.welcome"))
            .replyMarkup(keyboardService.mainMenu())
            .build()
        eventPublisher.publishEvent(MessageEvent(this, message))
    }

    override fun getCommand(): String {
        return CommandName.LANGUAGE.commandName
    }
}