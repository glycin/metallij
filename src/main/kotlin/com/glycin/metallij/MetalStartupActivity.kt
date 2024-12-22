package com.glycin.metallij

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class MetalStartupActivity: ProjectActivity, DumbAware {

    override suspend fun execute(project: Project) {
        val application = ApplicationManager.getApplication()
        application.getService(MetalService::class.java).rockOn()
    }
}