package com.example.botinterface.bot.commands

import com.example.botinterface.bot.events.MessageEvent
import com.example.botinterface.bot.service.LocalizationService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class AboutCommand(
    private val eventPublisher: ApplicationEventPublisher,
    private val localizationService: LocalizationService
) : Command {

    override fun canHandle(update: Update): Boolean {
        if (!update.hasMessage() || !update.message.hasText()) {
            return false
        }
        val localizedMessage = localizationService.getLocalizedMessage("menu.about")
        return update.message.text == localizedMessage
    }

    override fun handle(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val chatId = update.message.chatId
            val localizedMessage = localizationService.getLocalizedMessage("system.about")
            val message = SendMessage.builder()
                .chatId(chatId)
                .text(localizedMessage)
                .build()
            eventPublisher.publishEvent(MessageEvent(this, message))
        }
    }

    override fun getCommand(): String {
        return CommandName.ABOUT.commandName
    }
}