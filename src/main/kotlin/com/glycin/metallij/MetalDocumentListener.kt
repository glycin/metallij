package com.glycin.metallij

import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener

class MetalDocumentListener: DocumentListener {

    override fun documentChanged(event: DocumentEvent) {
        val newText = event.newFragment.toString()
        println(newText)
    }
}