package dev.twelveoclock.blackhole

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) {
            println("Usage: blackhole <install/package> <output>")
            return
        }
        if (args[0] == "package") {
            //Vars
            // STRACE commad: $strace <command>
            // ADD FLAG: $flag=[name] <command>
            // ADD Dep: $dep=[name] <packageName> <vararg flags>

        } else {
            TODO()
        }
    }

}