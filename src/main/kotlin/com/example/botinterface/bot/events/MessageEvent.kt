package com.example.botinterface.bot.events

import org.springframework.context.ApplicationEvent
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod
import java.io.Serializable

class MessageEvent(
    source: Any,
    val message: BotApiMethod<out Serializable>
) : ApplicationEvent(source)