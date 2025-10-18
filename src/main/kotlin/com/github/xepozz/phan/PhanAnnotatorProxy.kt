package com.github.xepozz.phan

import com.github.xepozz.phan.config.PhanProjectConfiguration
import com.intellij.codeInspection.InspectionProfile
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.jetbrains.php.tools.quality.QualityToolAnnotator
import com.jetbrains.php.tools.quality.QualityToolAnnotatorInfo
import com.jetbrains.php.tools.quality.QualityToolConfiguration

open class PhanAnnotatorProxy : QualityToolAnnotator<PhanValidationInspection>() {
    companion object {
        private val LOG: Logger = Logger.getInstance(PhanAnnotatorProxy::class.java)

        val INSTANCE = PhanAnnotatorProxy()

        fun getFormatOptions(projectPath: String, files: Collection<String>) = buildList {
            add("--workspace=$projectPath")

            add("fmt")
            addAll(files)
        }
//            .apply { println("format options: ${this.joinToString(" ")}") }

        fun getAnalyzeOptions(projectPath: String, filePath: String?) = buildList {
            add("--allow-polyfill-parser")
            add("-m")
            add("json")
            add("--no-progress-bar")

        }
//            .apply { println("analyze options: ${this.joinToString(" ")}") }
    }

    override fun getOptions(
        filePath: String?,
        inspection: PhanValidationInspection,
        profile: InspectionProfile?,
        project: Project,
    ): List<String> {
        val projectPath = project.basePath ?: return emptyList()
        val settings = project.getService(PhanProjectConfiguration::class.java)

        return getAnalyzeOptions(projectPath, filePath)
            .plus(settings.additionalParameters.split(" ").filter { it.isNotBlank() })
    }

    override fun getEnvironment() = mapOf(
        "XDEBUG_MODE" to "off",
        "PHAN_DISABLE_XDEBUG_WARN" to "1",
        "PHAN_ALLOW_XDEBUG" to "1",
    )

    override fun createAnnotatorInfo(
        file: PsiFile?,
        tool: PhanValidationInspection,
        inspectionProfile: InspectionProfile,
        project: Project,
        configuration: QualityToolConfiguration,
        isOnTheFly: Boolean,
    ): QualityToolAnnotatorInfo<PhanValidationInspection> {
        if (!isOnTheFly) {
            LOG.warn("isOnTheFly is False")
        }

        val settings = project.getService(PhanProjectConfiguration::class.java)

        return QualityToolAnnotatorInfo(
            file,
            tool,
            inspectionProfile,
            project,
            configuration.interpreterId,
            settings.phanExecutable,
            configuration.maxMessagesPerFile,
            configuration.timeout,
            false
        )
    }

    override fun getQualityToolType() = PhanQualityToolType.INSTANCE

    override fun createMessageProcessor(collectedInfo: QualityToolAnnotatorInfo<PhanValidationInspection>) =
        PhanMessageProcessor(collectedInfo)

    override fun getPairedBatchInspectionShortName() = qualityToolType.inspectionId

    override fun runOnTempFiles() = false
}
