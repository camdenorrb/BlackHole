package dev.twelveoclock.blackhole.conversationbuilder.base

import dev.twelveoclock.blackhole.sender.base.Sender

interface ConversationBuilder<T> {

    suspend fun build(sender: Sender): T

}