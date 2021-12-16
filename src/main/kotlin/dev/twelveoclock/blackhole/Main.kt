package dev.twelveoclock.blackhole

import java.net.URL
import java.nio.file.Path

object Main {

	fun createPackage(name: String, git: URL): Package {

	}

	data class Package(val name: String, val path: Path) {

		// TODO: Detect if package is installed
		var isInstalled = false
			private set


		fun build() {

		}

		fun clean() {

		}

		fun install() {

		}

		fun uninstall() {

		}

	}



}