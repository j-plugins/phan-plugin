package com.github.xepozz.phan.config

import com.github.xepozz.phan.PhanQualityToolType
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import com.jetbrains.php.tools.quality.QualityToolProjectConfiguration

@Service(Service.Level.PROJECT)
@State(name = "PhanProjectConfiguration", storages = [Storage(StoragePathMacros.WORKSPACE_FILE)])
class PhanProjectConfiguration : QualityToolProjectConfiguration<PhanConfiguration>(),
    PersistentStateComponent<PhanProjectConfiguration> {
    var linterEnabled = true
    var formatterEnabled = true
    var phanExecutable = "vendor/bin/phan"
    var additionalParameters = ""

    override fun getState() = this

    override fun loadState(state: PhanProjectConfiguration) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun getQualityToolType() = PhanQualityToolType.INSTANCE

    companion object {
        fun getInstance(project: Project): PhanProjectConfiguration =
            project.getService(PhanProjectConfiguration::class.java)
    }
}