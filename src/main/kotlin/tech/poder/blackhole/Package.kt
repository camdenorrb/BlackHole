package tech.poder.blackhole

import kotlinx.serialization.Serializable

@Serializable
data class Package(val name: String, val version: String, val experimental: Boolean = false, val type: PackageType = PackageType.GIT, var installed: Boolean = false) {

    fun parse(target: Target) {
        when (target) {
            Target.INSTALL -> {
                if (installed) {
                    println("Package $name is already installed")
                } else {
                    println("Installing package $name")
                    installed = true
                }
            }
            Target.UNINSTALL -> {
                if (installed) {
                    println("Uninstalling package $name")
                    installed = false
                } else {
                    println("Package $name is not installed")
                }
            }
            Target.UPDATE -> {
                if (installed) {
                    println("Updating package $name")
                    parse(Target.INSTALL)
                    if (TODO("Check Dep graph")) {
                        parse(Target.UNINSTALL)
                    }
                } else {
                    println("Installing package $name")
                    parse(Target.INSTALL)
                }
            }
            Target.REINSTALL -> TODO()
        }
    }
}
