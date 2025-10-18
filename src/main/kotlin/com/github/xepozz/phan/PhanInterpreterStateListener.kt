package com.github.xepozz.phan

import com.github.xepozz.phan.config.PhanConfigurationManager
import com.intellij.openapi.project.Project
import com.jetbrains.php.config.interpreters.PhpInterpretersStateListener

class PhanInterpreterStateListener : PhpInterpretersStateListener {
    override fun onInterpretersUpdate(project: Project) {
        PhanConfigurationManager.getInstance(project).onInterpretersUpdate()
    }
}
