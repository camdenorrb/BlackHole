package dev.twelveoclock.blackhole

import tech.poder.blackhole.PackageType
import java.net.URL
import java.nio.file.Path

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) {
            println("Usage: blackhole <install/package> <output>")
            return
        }
        if (args[0] == "package") {

        } else {
            TODO()
        }
    }

}