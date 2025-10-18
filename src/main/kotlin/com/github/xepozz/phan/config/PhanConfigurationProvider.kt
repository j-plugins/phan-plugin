package com.github.xepozz.phan.config

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.extensions.ExtensionPointName
import com.jetbrains.php.tools.quality.QualityToolConfigurationProvider

abstract class PhanConfigurationProvider : QualityToolConfigurationProvider<PhanConfiguration>() {
    companion object {
        private val LOG: Logger = Logger.getInstance(PhanConfigurationProvider::class.java)
        private val EP_NAME: ExtensionPointName<PhanConfigurationProvider> =
            ExtensionPointName.create("com.github.xepozz.phan.phanConfigurationProvider")

        fun getInstances(): PhanConfigurationProvider? {
            val extensions: Array<PhanConfigurationProvider> = EP_NAME.extensions
            if (extensions.size > 1) {
                LOG.error("Several providers for remote Phan configuration was found")
            }
            return if (extensions.isEmpty()) null else extensions[0]
        }
    }
}
