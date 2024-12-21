package com.glycin.metallij

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

object MetalPlayer {

    private val semicolonSound : Clip? = AudioSystem.getClip()
    private val openBracketsSound : Clip? = AudioSystem.getClip()
    private val closeBracketsSound : Clip? = AudioSystem.getClip()
    private val nullPointerSound : Clip? = AudioSystem.getClip()
    private val garbageCollectionSound : Clip? = AudioSystem.getClip()
    private val publicClassSound : Clip? = AudioSystem.getClip()

    var ready = false

    fun init(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            try{
                loadClip("close_brackets.wav") { stream ->
                    closeBracketsSound?.open(AudioSystem.getAudioInputStream(stream))
                }

                loadClip("garbage_collection.wav") { stream ->
                    garbageCollectionSound?.open(AudioSystem.getAudioInputStream(stream))
                }

                loadClip("npe.wav") { stream ->
                    nullPointerSound?.open(AudioSystem.getAudioInputStream(stream))
                }

                loadClip("open_brackets.wav") { stream ->
                    openBracketsSound?.open(AudioSystem.getAudioInputStream(stream))
                }

                loadClip("public_class.wav") { stream ->
                    publicClassSound?.open(AudioSystem.getAudioInputStream(stream))
                }

                loadClip("semicolon.wav") { stream ->
                    semicolonSound?.open(AudioSystem.getAudioInputStream(stream))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            ready = true
        }
    }

    fun playSound(sound: Sounds) {
        println("Playing sound $sound")
        try {
            when (sound) {
                Sounds.SEMICOLON -> semicolonSound
                Sounds.OPEN_BRACKET -> openBracketsSound
                Sounds.CLOSE_BRACKET -> closeBracketsSound
                Sounds.PUBLIC_CLASS -> publicClassSound
                Sounds.NPE -> nullPointerSound
                Sounds.GARBAGE_COLLECTION -> garbageCollectionSound
            }?.let {
                if(it.isRunning) {
                    it.stop()
                }
                it.framePosition = 0
                it.start()
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadClip(soundName: String, assign: (stream: BufferedInputStream) -> Unit) {
        this::class.java.getResourceAsStream("/sounds/$soundName")?.use { stream ->
            BufferedInputStream(stream).use { bis ->
                assign(bis)
            }
        }
    }
}