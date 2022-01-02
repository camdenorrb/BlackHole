package dev.twelveoclock.blackhole.module.base

interface Module {

    val isEnabled: Boolean


    fun enable()

    fun disable()

}