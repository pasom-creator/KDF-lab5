package com.example.botinterface.bot.commands

import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun canHandle(update: Update): Boolean
    fun handle(update: Update)
    fun getCommand(): String
}