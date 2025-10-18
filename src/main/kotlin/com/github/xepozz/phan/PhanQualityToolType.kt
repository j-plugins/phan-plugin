package com.github.xepozz.phan

import com.github.xepozz.phan.config.PhanConfigurable
import com.github.xepozz.phan.config.PhanConfigurableForm
import com.github.xepozz.phan.config.PhanConfiguration
import com.github.xepozz.phan.config.PhanConfigurationBaseManager.Companion.PHAN
import com.github.xepozz.phan.config.PhanConfigurationManager
import com.github.xepozz.phan.config.PhanConfigurationProvider
import com.github.xepozz.phan.config.PhanProjectConfiguration
import com.intellij.codeInspection.InspectionProfile
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.profile.codeInspection.InspectionProjectProfileManager
import com.intellij.util.ObjectUtils.tryCast
import com.jetbrains.php.tools.quality.QualityToolType
import com.jetbrains.php.tools.quality.QualityToolValidationGlobalInspection

class PhanQualityToolType : QualityToolType<PhanConfiguration>() {
    override fun getDisplayName() = PHAN

    override fun getQualityToolBlackList(project: Project) = PhanBlackList.getInstance(project)

    override fun getConfigurationManager(project: Project) = PhanConfigurationManager.getInstance(project)

    public override fun getInspection() = PhanValidationInspection()

    override fun getConfigurationProvider() = PhanConfigurationProvider.getInstances()

    override fun createConfigurableForm(project: Project, settings: PhanConfiguration) =
        PhanConfigurableForm(project, settings)

    override fun getToolConfigurable(project: Project): Configurable = PhanConfigurable(project)

    public override fun getProjectConfiguration(project: Project) =
        PhanProjectConfiguration.getInstance(project)

    override fun createConfiguration() = PhanConfiguration()

    override fun getInspectionId() = "PhanGlobal"

    override fun getHelpTopic() = "reference.settings.php.phan"

    override fun getGlobalTool(project: Project, profile: InspectionProfile?): QualityToolValidationGlobalInspection? {
        val newProfile = profile ?: InspectionProjectProfileManager.getInstance(project).currentProfile

        val inspectionTool = newProfile.getInspectionTool(inspectionId, project) ?: return null

        return tryCast(inspectionTool.tool, PhanGlobalInspection::class.java)
    }

    override fun getInspectionShortName(project: Project) = getGlobalTool(project, null)?.shortName
        ?: inspection.shortName

    companion object {
        val INSTANCE = PhanQualityToolType()
    }
}
