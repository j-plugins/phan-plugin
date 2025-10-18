package com.github.xepozz.phan.config

import com.github.xepozz.phan.PhanQualityToolType
import com.intellij.util.xmlb.XmlSerializer
import com.jetbrains.php.tools.quality.QualityToolConfigurationBaseManager
import org.jdom.Element

open class PhanConfigurationBaseManager : QualityToolConfigurationBaseManager<PhanConfiguration>() {
    override fun getQualityToolType() = PhanQualityToolType.INSTANCE

    override fun getOldStyleToolPathName() = PHAN_PATH

    override fun getConfigurationRootName() = PHAN_ROOT_NAME

    override fun loadLocal(element: Element?): PhanConfiguration? {
        if (element == null) return null

        return XmlSerializer.deserialize(element, PhanConfiguration::class.java)
    }

    companion object {
        const val PHAN = "Phan"
        const val PHAN_PATH = "PhanPath"
        const val PHAN_ROOT_NAME = "Phan_settings"
    }
}
