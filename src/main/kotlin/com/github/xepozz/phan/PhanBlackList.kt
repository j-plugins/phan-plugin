package com.github.xepozz.phan

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import com.jetbrains.php.tools.quality.QualityToolBlackList

@State(name = "PhanBlackList", storages = [Storage(StoragePathMacros.WORKSPACE_FILE)])
@Service(Service.Level.PROJECT)
class PhanBlackList : QualityToolBlackList() {
    companion object {
        fun getInstance(project: Project): PhanBlackList = project.getService(PhanBlackList::class.java)
    }
}
