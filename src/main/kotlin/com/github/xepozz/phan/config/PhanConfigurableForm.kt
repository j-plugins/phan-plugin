package com.github.xepozz.phan.config

import com.github.xepozz.phan.PhanCustomOptionsForm
import com.github.xepozz.phan.PhanQualityToolType
import com.github.xepozz.phan.config.PhanConfigurationBaseManager.Companion.PHAN
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Pair
import com.jetbrains.php.PhpBundle
import com.jetbrains.php.tools.quality.QualityToolConfigurableForm
import com.jetbrains.php.tools.quality.QualityToolConfiguration
import com.jetbrains.php.tools.quality.QualityToolType

// TODO: change to PhpStanOptionsPanel
class PhanConfigurableForm(project: Project, configuration: PhanConfiguration) :
    QualityToolConfigurableForm<PhanConfiguration>(project, configuration, PHAN, "Phan") {
    override fun getQualityToolType(): QualityToolType<QualityToolConfiguration> {
        try {
            @Suppress("UNCHECKED_CAST")
            return PhanQualityToolType.INSTANCE as QualityToolType<QualityToolConfiguration>
        } catch (e: Throwable) {
            throw e
        }
    }

    override fun getCustomConfigurable(project: Project, configuration: PhanConfiguration) =
        PhanCustomOptionsForm(project, configuration)

    override fun getHelpTopic() = "reference.settings.php.phan"

    override fun validateWithNoAnsi() = false

    override fun validateMessage(message: String): Pair<Boolean, String> {
        val regex = Regex("^phan (?<version>.+)$")

        return regex.find(message)?.groups?.get("version")
            ?.let { Pair.create(true, "OK, Phan version ${it.value}") }
            ?: Pair.create(false, PhpBundle.message("quality.tool.can.not.determine.version", message))
    }
}
