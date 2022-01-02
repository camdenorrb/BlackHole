package dev.twelveoclock.blackhole.conversationbuilder

import dev.twelveoclock.blackhole.conversationbuilder.base.ConversationBuilder
import dev.twelveoclock.blackhole.sender.base.Sender
import tech.poder.blackhole.Package

class PackageConvoBuilder(val installedPackages: Map<String, Package>) : ConversationBuilder<Package> {

    override suspend fun build(sender: Sender): Package = with(sender) {
        
        val name = waitForReply(
            "Please enter package name"
        )
        
        val version = waitForReply(
            "Please enter package version"
        )

        val dependencies = waitForReplies(
            "Enter dependency package names, use blank line when done"
        ).mapNotNull { installedPackages[it] }

        val isExperimental = waitForBoolean(
            "Enter if package is experimental (True/False)"
        )

        Package(name, version, dependencies, )
    }
    
}