package com.sample.gutenberg.utils

import android.content.Context
import android.graphics.Typeface


object CustomFontFamily {

    var fontMap: HashMap<String, String> = HashMap()
    var fontCache: HashMap<String, Typeface?> = HashMap()

    fun addFont(alias: String, fontName: String) {
        fontMap[alias] = fontName
    }

    fun getFont(context: Context, alias: String): Typeface? {
        val fontFilename = fontMap[alias] ?: return null
        return if (fontCache.containsKey(alias))
            fontCache[alias]
        else {
            val typeface = Typeface.createFromAsset(
                context.assets, "fonts/$fontFilename"
            )
            fontCache[fontFilename] = typeface
            typeface
        }
    }
}