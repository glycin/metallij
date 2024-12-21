package com.glycin.metallij

import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener

class MetalDocumentListener: DocumentListener {

    // For some reason the TypedHanlderDelegate doesn't fire on `{` so we will do that here instead
    override fun documentChanged(event: DocumentEvent) {
        val newText = event.newFragment.toString()
        if(!MetalPlayer.ready) {
            return
        }

        if(newText == "{") {
            MetalPlayer.playSound(Sounds.OPEN_BRACKET)
        }
    }
}