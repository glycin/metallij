package com.glycin.metallij

import com.intellij.codeInsight.lookup.LookupEvent
import com.intellij.codeInsight.lookup.LookupListener

private const val PUBLIC_CLASS = "class"
private const val GARBAGE_COLLECTION = "gc"
private const val NULL = "null"

class MetalLookupListener: LookupListener {
    override fun itemSelected(event: LookupEvent) {
        if(!MetalPlayer.ready){ return }

        if(event.item.toString() == PUBLIC_CLASS) {
            MetalPlayer.playSound(Sounds.PUBLIC_CLASS)
        }

        if(event.item.toString() == GARBAGE_COLLECTION) {
            MetalPlayer.playSound(Sounds.GARBAGE_COLLECTION)
        }

        if(event.item.toString() == NULL) {
            MetalPlayer.playSound(Sounds.NPE)
        }

        super.itemSelected(event)
    }
}