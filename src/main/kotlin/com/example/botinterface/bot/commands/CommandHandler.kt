package com.example.botinterface.bot.commands

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class CommandHandler(
    private val commands: Collection<Command>
) {

    fun handle(update: Update) {
        for (command in commands) {
            if (command.canHandle(update)) {
                command.handle(update)
                return
            }
        }
    }
}