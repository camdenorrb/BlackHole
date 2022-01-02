package dev.twelveoclock.blackhole.module.base

abstract class BasicModule : Module {

    final override var isEnabled = false
        private set


    protected open fun onEnable() = Unit

    protected open fun onDisable() = Unit


    override fun enable() {
        TODO("Not yet implemented")
    }

    override fun disable() {
        TODO("Not yet implemented")
    }

}