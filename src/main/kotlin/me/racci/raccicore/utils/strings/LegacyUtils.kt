package me.racci.raccicore.utils.strings

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer


/**
 * Legacy
 *
 * @constructor Create empty Legacy
 */
class Legacy private constructor() {
    companion object {

        val SERIALIZER = LegacyComponentSerializer.builder()
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build()
    }
}

object LegacyUtils {
    fun parseLegacy(string: String?): Component {
        return LegacyComponentSerializer.legacySection().deserialize(string!!)
    }

    fun parseLegacy(list: List<String>?): List<Component> {
        val compList = ArrayList<Component>()
        list?.forEach {var1x -> compList.add(LegacyComponentSerializer.legacySection().deserialize(var1x))}
        return compList
    }
}