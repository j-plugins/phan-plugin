package com.github.xepozz.phan

import com.github.xepozz.phan.config.PhanConfiguration
import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolAddToIgnoredAction
import com.jetbrains.php.tools.quality.QualityToolType

class PhanAddToIgnoredAction : QualityToolAddToIgnoredAction() {
    override fun getQualityToolType(project: Project?): QualityToolType<PhanConfiguration> {
        return PhanQualityToolType.INSTANCE
    }
}
