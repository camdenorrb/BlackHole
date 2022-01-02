package dev.twelveoclock.blackhole.sender

import dev.twelveoclock.blackhole.sender.base.Sender

object ConsoleSender : Sender() {

    // TODO: Maybe add logging?
    override fun sendMessage(message: String) {
        println(message)
    }

    override suspend fun waitForReply(): String {
        return readln()
    }

}