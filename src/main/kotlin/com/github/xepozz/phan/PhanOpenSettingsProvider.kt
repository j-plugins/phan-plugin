package com.github.xepozz.phan

import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.jetbrains.php.composer.actions.log.ComposerLogMessageBuilder

class PhanOpenSettingsProvider : ComposerLogMessageBuilder.Settings("\u300C") {
    override fun show(project: Project) {
        ShowSettingsUtil.getInstance()
            .showSettingsDialog(project, PhanBundle.message("configurable.quality.tool.php.phan"))
    }

    companion object {
        val INSTANCE = PhanOpenSettingsProvider()
    }
}
