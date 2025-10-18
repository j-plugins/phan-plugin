package com.github.xepozz.phan

import com.intellij.codeInspection.ex.ExternalAnnotatorBatchInspection
import com.intellij.openapi.util.Key
import com.jetbrains.php.tools.quality.QualityToolValidationGlobalInspection
import com.jetbrains.php.tools.quality.QualityToolXmlMessageProcessor.ProblemDescription

class PhanGlobalInspection : QualityToolValidationGlobalInspection(), ExternalAnnotatorBatchInspection {
    override fun getAnnotator() = PhanAnnotatorProxy.INSTANCE

    override fun getKey() = PHAN_ANNOTATOR_INFO

    override fun getSharedLocalInspectionTool() = PhanValidationInspection()

    companion object {
        private val PHAN_ANNOTATOR_INFO = Key.create<List<ProblemDescription>>("ANNOTATOR_INFO_PHAN")
    }
}
