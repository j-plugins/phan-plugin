package com.github.xepozz.phan.composer

import com.github.xepozz.phan.PhanOpenSettingsProvider
import com.github.xepozz.phan.PhanQualityToolType
import com.github.xepozz.phan.PhanValidationInspection
import com.github.xepozz.phan.config.PhanConfiguration
import com.github.xepozz.phan.config.PhanConfigurationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.jetbrains.php.tools.quality.QualityToolsComposerConfig

class PhanComposerConfig : QualityToolsComposerConfig<PhanConfiguration, PhanValidationInspection>(
    PACKAGE,
    RELATIVE_PATH
) {
    override fun getQualityInspectionShortName() = PhanQualityToolType.Companion.INSTANCE.inspectionId

    override fun getConfigurationManager(project: Project) = PhanConfigurationManager.Companion.getInstance(project)

    override fun getSettings() = PhanOpenSettingsProvider.Companion.INSTANCE

    companion object {
        private const val PACKAGE: String = "phan/phan"
        private val RELATIVE_PATH: String = "bin/phan${if (SystemInfo.isWindows) ".exe" else ""}"
    }
}
