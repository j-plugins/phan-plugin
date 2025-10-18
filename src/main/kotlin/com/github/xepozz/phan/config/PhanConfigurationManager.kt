package com.github.xepozz.phan.config

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolConfigurationManager

@Service(Service.Level.PROJECT)
class PhanConfigurationManager(project: Project?) :
    QualityToolConfigurationManager<PhanConfiguration>(project) {
    init {
        if (project != null) {
            myProjectManager = project.getService(PhanProjectConfigurationManager::class.java)
        }
        myApplicationManager = ApplicationManager.getApplication().getService(PhanAppConfigurationManager::class.java)
    }

    @Service(Service.Level.PROJECT)
    @State(name = "Phan", storages = [Storage("php-tools.xml")])
    internal class PhanProjectConfigurationManager : PhanConfigurationBaseManager()

    @Service(Service.Level.APP)
    @State(name = "Phan", storages = [Storage("php-tools.xml")])
    internal class PhanAppConfigurationManager : PhanConfigurationBaseManager()

    companion object {
        fun getInstance(project: Project): PhanConfigurationManager =
            project.getService(PhanConfigurationManager::class.java)
    }
}
