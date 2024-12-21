package com.glycin.metallij

import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupManagerListener

class MetalLookupListenerManager: LookupManagerListener {
    override fun activeLookupChanged(old: Lookup?, new: Lookup?) {
        new?.addLookupListener(MetalLookupListener())
    }
}