package com.glycin.metallij

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.EditorFactory
import kotlinx.coroutines.CoroutineScope

@Service(Service.Level.APP)
class MetalService(
    private val scope: CoroutineScope
): Disposable {

    private var metalDocumentListener : MetalDocumentListener? = null

    fun rockOn() {
        println("ROCK ON!!")
        metalDocumentListener = MetalDocumentListener().also {
            EditorFactory.getInstance().eventMulticaster.addDocumentListener(it, this)
        }
    }

    override fun dispose() {
        rockOff()
    }

    private fun rockOff() {
        // We never turn the metal off
    }
}