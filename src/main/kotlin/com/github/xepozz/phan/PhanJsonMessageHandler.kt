package com.github.xepozz.phan

import com.google.gson.JsonParser
import com.jetbrains.php.tools.quality.QualityToolMessage

class PhanJsonMessageHandler {
    fun parseJson(line: String): List<PhanProblemDescription> {
        return JsonParser.parseString(line)
            .apply { if (this == null || this.isJsonNull) return emptyList() }
            .asJsonArray
            .map { it.asJsonObject }
            .map { issue ->

                val location = issue.get("location").asJsonObject
                PhanProblemDescription(
                    levelToSeverity(issue.get("severity").asInt),
                    location.get("lines").asJsonObject.get("begin").asInt,
                    location.get("lines").asJsonObject.get("end").asInt,
                    location.get("lines").asJsonObject.get("end").asInt,
                    "Phan: ${issue.get("description").asString.trimEnd('.')} [${issue.get("check_name").asString}]",
                    location.get("path").asString,
                    issue.get("check_name").asString,
                )
//                                PhanProblemDescription(
//                                    levelToSeverity(issue.get("level").asString),
//                                    span.get("start").asJsonObject.get("line").asInt,
//                                    span.get("start").asJsonObject.get("offset").asInt,
//                                    span.get("end").asJsonObject.get("offset").asInt,
//                                    annotation.get("message").asString,
//                                    span.get("file_id").asJsonObject.get("path").asString,
//                                    issue.get("code").asString,
//                                    issue.get("help").asString,
//                                    issue.get("notes").asJsonArray.map { it.asString },
//                                ),
            }
            .apply { println("problems: $this") }
    }

    fun levelToSeverity(level: Int) = when (level) {
        in 6..10 -> QualityToolMessage.Severity.ERROR
        in 1..5 -> QualityToolMessage.Severity.WARNING
        else -> null
    }
}