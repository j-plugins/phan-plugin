//package com.github.xepozz.phan.remote
//
//import com.github.xepozz.phan.config.PhanConfigurableForm
//import com.github.xepozz.phan.config.PhanConfiguration
//import com.github.xepozz.phan.config.PhanConfigurationProvider
//import com.intellij.openapi.project.Project
//import com.intellij.util.xmlb.XmlSerializer
//import com.jetbrains.php.config.interpreters.PhpInterpreter
//import com.jetbrains.php.remote.tools.quality.QualityToolByInterpreterConfigurableForm
//import com.jetbrains.php.tools.quality.QualityToolConfigurableForm
//import org.jdom.Element
//
//class PhanRemoteConfigurationProvider : PhanConfigurationProvider() {
//    override fun canLoad(tagName: String) = tagName == PHAN_BY_INTERPRETER
//
//    override fun load(element: Element) = XmlSerializer.deserialize(element, PhanRemoteConfiguration::class.java)
//
//    override fun createConfigurationForm(
//        project: Project,
//        settings: PhanConfiguration,
//    ): QualityToolConfigurableForm<*>? {
//        if (settings !is PhanRemoteConfiguration) {
//            return null
//        }
//
//        val delegate = PhanConfigurableForm(project, settings)
//        return QualityToolByInterpreterConfigurableForm(
//            project,
//            settings,
//            delegate,
//        )
//    }
//
//    override fun createNewInstance(
//        project: Project?,
//        existingSettings: List<PhanConfiguration>,
//    ) = PhanRemoteConfiguration()
//
//    override fun createConfigurationByInterpreter(interpreter: PhpInterpreter): PhanConfiguration {
//        val settings = PhanRemoteConfiguration()
//        settings.setInterpreterId(interpreter.id)
//        return settings
//    }
//
//    companion object {
//        private const val PHAN_BY_INTERPRETER: String = "phan_by_interpreter"
//    }
//}
