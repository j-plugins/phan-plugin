package com.github.xepozz.phan

import com.github.xepozz.phan.config.PhanConfigurationBaseManager.Companion.PHAN
import com.jetbrains.php.tools.quality.QualityToolValidationInspection

class PhanValidationInspection : QualityToolValidationInspection<PhanValidationInspection>() {
    override fun getAnnotator() = PhanAnnotatorProxy.INSTANCE

    override fun getToolName() = PHAN
}
