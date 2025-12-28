package com.example.botinterface.bot.commands

import com.example.botinterface.bot.events.MessageEvent
import com.example.botinterface.bot.service.LocalizationService
import com.example.botinterface.service.UserService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Document
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class UploadCommand(
    private val eventPublisher: ApplicationEventPublisher,
    private val localizationService: LocalizationService,
    private val userService: UserService
) : Command {
    override fun canHandle(update: Update): Boolean {
        return update.hasMessage() && update.message.hasDocument()
    }

    override fun handle(update: Update) {
        if (update.hasMessage() && update.message.hasDocument()) {
            val chatId = update.message.chatId
            val user = update.message.from
            val document: Document = update.message.document

            val userId = user.id
            val fullName = "${user.firstName} ${user.lastName ?: ""}".trim()

            val fileId = document.fileId

            try {
                userService.addUpload(userId, fullName, fileId)

                val message = SendMessage.builder()
                    .chatId(chatId)
                    .text(localizationService.getLocalizedMessage("save.to.database"))
                    .build()

                eventPublisher.publishEvent(MessageEvent(this, message))
            } catch (e: Exception) {
                val error = SendMessage.builder()
                    .chatId(chatId)
                    .text(localizationService.getLocalizedMessage("save.to.database.error"))
                    .build()

                eventPublisher.publishEvent(MessageEvent(this, error))
            }
        }
    }

    override fun getCommand(): String {
        TODO("Not yet implemented")
    }

}