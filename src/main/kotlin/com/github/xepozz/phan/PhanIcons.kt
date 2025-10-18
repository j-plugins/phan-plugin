package com.github.xepozz.phan

import com.intellij.openapi.util.IconLoader

// https://intellij-icons.jetbrains.design
// https://plugins.jetbrains.com/docs/intellij/icons.html#new-ui-tool-window-icons
// https://plugins.jetbrains.com/docs/intellij/icons-style.html
object PhanIcons {
    @JvmField
    val PHAN = IconLoader.getIcon("/icons/phan/icon.svg", this::class.java)
}