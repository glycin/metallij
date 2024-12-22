package com.glycin.metallij

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class MetalTypedHandler: TypedHandlerDelegate() {

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        if(!MetalPlayer.ready) return Result.CONTINUE

        val document = editor.document

        when(c) {
            '}' -> MetalPlayer.playSound(Sounds.CLOSE_BRACKET)
            ';' -> MetalPlayer.playSound(Sounds.SEMICOLON)
        }

        val caretOffset = editor.caretModel.offset
        val currentLineNumber = document.getLineNumber(caretOffset)
        val lineStartOffset = document.getLineStartOffset(currentLineNumber)
        val lineEndOffset = document.getLineEndOffset(currentLineNumber)
        val currentLineText = document.text.substring(lineStartOffset, lineEndOffset).trim()

        if (currentLineText.endsWith("public class")) {
            MetalPlayer.playSound(Sounds.PUBLIC_CLASS)
        } else if (currentLineText.endsWith("gc()")) {
            MetalPlayer.playSound(Sounds.GARBAGE_COLLECTION)
        } else if (currentLineText.endsWith("null")) {
            MetalPlayer.playSound(Sounds.NPE)
        }

        return Result.CONTINUE
    }
}