package dev.twelveoclock.blackhole.module

import java.nio.file.Path
import dev.twelveoclock.blackhole.`package`.Package
import dev.twelveoclock.blackhole.module.base.BasicModule
import org.eclipse.jgit.api.Git
import java.net.URL
import kotlin.io.path.*

class PackageModule(val packageDir: Path) : BasicModule() {
    
    val installed = mutableMapOf<String, Package>()


    // TODO: Add symlinks
    fun install(`package`: Package) {
        download(`package`.downloadURL, directoryFor(`package`))
        installed[`package`.name] = `package`
    }

    // TODO: Remove symlinks
    fun uninstall(`package`: Package) {
        directoryFor(`package`).deleteIfExists()
        installed.remove(`package`.name)
    }
    

    private fun directoryFor(`package`: Package): Path {
        return Path(packageDir.absolutePathString(), `package`.name)
    }


    /**
     * Determines the url type and downloads it to a specified path
     */
    private fun download(url: String, path: Path) {

        when (val protocol = url.substringBefore("://").lowercase()) {

            "https" -> downloadHTTPS(url, path)
            "git" -> downloadGit(url, path)

            else -> error("Unsupported protocol $protocol")
        }

    }

    private fun downloadHTTPS(url: String, path: Path) {
        return URL(url).openStream().use { inputStream ->
            path.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    }

    private fun downloadGit(url: String, path: Path) {
        Git.cloneRepository()
            .setURI(url)
            .setDirectory(path.toFile())
            .call()
    }


}