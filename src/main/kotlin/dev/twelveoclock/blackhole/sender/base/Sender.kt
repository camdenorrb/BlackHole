package dev.twelveoclock.blackhole.sender.base

import java.util.*

abstract class Sender {

    abstract fun sendMessage(message: String)

    abstract suspend fun waitForReply(): String


    suspend fun waitForReply(prompt: String): String {
        sendMessage(prompt)
        return waitForReply()
    }

    suspend fun <E : Enum<E>> waitForValidReplies(
        prompt: String,
        enumClass: Class<E>,
        breakPhrase: String = ""
    ): EnumSet<E> {

        val enumConstants = enumClass.enumConstants.associateBy { it.name }

        return EnumSet.copyOf(
            waitForValidReplies(
                prompt,
                { enumConstants[it] },
                { "Please enter one of these ${enumConstants.keys}"},
                breakPhrase
            )
        )
    }

    suspend fun waitForReplies(prompt: String, breakPhrase: String = ""): List<String> {

        sendMessage(prompt)

        val responses = mutableListOf<String>()
        var response = waitForReply()

        while (response != breakPhrase) {
            responses += response
            response = waitForReply()
        }

        return responses
    }

    suspend inline fun <T> waitForValidReplies(
        prompt: String,
        convertBlock: (input: String) -> T?,
        invalidResponse: (input: String) -> String,
        breakPhrase: String = ""
    ): List<T> {

        sendMessage(prompt)

        val responses = mutableListOf<T>()
        var response = waitForReply()

        while (response != breakPhrase) {

            val convertedResponse = convertBlock(response)

            if (convertedResponse == null) {
                println("Please enter a valid response: ${invalidResponse(response)}")
                continue
            }

            responses += convertedResponse
            response = waitForReply()
        }

        return responses
    }

    suspend fun waitForValidReplies(
        prompt: String,
        validReplies: Set<String>,
        breakPhrase: String = ""
    ): List<String> {

        sendMessage(prompt)

        val responses = mutableListOf<String>()
        var response = waitForReply()

        while (response != breakPhrase) {

            if (response !in validReplies) {
                println("Please enter a valid response: $validReplies")
                continue
            }

            responses += response
            response = waitForReply()
        }

        return responses
    }

    suspend inline fun waitForByte(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid Byte" },
    ): Byte {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toByteOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForShort(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid Short" }
    ): Short {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toShortOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForInt(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid Int" }
    ): Int {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toIntOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForLong(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid Long" }
    ): Long {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toLongOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForUByte(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid UByte" }
    ): UByte {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toUByteOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForUShort(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid UShort" }
    ): UShort {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toUShortOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForUInt(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a valid UInt" }
    ): UInt {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toUIntOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForULong(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a ULong value" }
    ): ULong {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toULongOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForFloat(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a Float value" }
    ): Float {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toFloatOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForDouble(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter a Double value" }
    ): Double {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toDoubleOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForBoolean(
        prompt: String,
        incorrectPrompt: (response: String) -> String = { "Please enter true or false" }
    ): Boolean {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = { it.toBooleanStrictOrNull() },
            incorrectPrompt = incorrectPrompt
        )
    }

    suspend inline fun waitForConfirmation(
        prompt: String,
        incorrectPrompt: (response: String) -> String
    ): Boolean {
        return waitForReplyUntilNotNull(
            prompt = prompt,
            conversionBlock = {
                when (it.lowercase()) {
                    "y", "yes" -> true
                    "n", "no" -> false
                    else -> null
                }
            },
            incorrectPrompt = incorrectPrompt
        )
    }


    suspend inline fun waitForReplyUntil(
        prompt: String,
        checkPredicate: (response: String) -> Boolean,
        incorrectPrompt: (response: String) -> String
    ): String {

        var response = waitForReply(prompt)

        while (checkPredicate(response)) {
            response = waitForReply(incorrectPrompt(response))
        }

        return response
    }

    suspend inline fun <T> waitForReplyUntilNotNull(
        prompt: String,
        conversionBlock: (response: String) -> T?,
        incorrectPrompt: (response: String) -> String
    ): T {

        var response = waitForReply(prompt)
        var result = conversionBlock(response)

        while (result == null) {
            response = waitForReply(incorrectPrompt(response))
            result = conversionBlock(response)
        }

        return result
    }

}