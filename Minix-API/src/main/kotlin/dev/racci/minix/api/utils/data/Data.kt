package dev.racci.minix.api.utils.data

import dev.racci.minix.api.plugin.Minix
import dev.racci.minix.api.utils.getKoin
import org.spongepowered.configurate.ConfigurateException
import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.serialize.TypeSerializer
import java.lang.reflect.Type

data class Data(val bytes: Long) : Comparable<Data>, TypeSerializer<Data> {

    val kilobytes: Double by lazy { bytes / 1024.0 }
    val megabytes: Double by lazy { bytes / 1048576.0 }
    val gigabytes: Double by lazy { bytes / 1073741824.0 }
    val terabytes: Double by lazy { bytes / 1099511627776.0 }

    val kilobytesRounded: Long by lazy { bytes / 1024 }
    val megabytesRounded: Long by lazy { bytes / 1048576 }
    val gigabytesRounded: Long by lazy { bytes / 1073741824 }
    val terabytesRounded: Long by lazy { bytes / 1099511627776 }

    override fun compareTo(other: Data): Int = when {
        bytes > other.bytes -> 1
        bytes < other.bytes -> -1
        else -> 0
    }

    override fun deserialize(
        type: Type,
        node: ConfigurationNode,
    ): Data {
        val match = node.get<String>()?.let(regex::matchEntire)
        return when (match?.groups?.get("identifier")?.value?.lowercase()) {
            "kb" -> fromKilobytes(match.groups["value"]!!.value.toLong())
            "mb" -> fromMegabytes(match.groups["value"]!!.value.toLong())
            "gb" -> fromGigabytes(match.groups["value"]!!.value.toLong())
            "tb" -> fromTerabytes(match.groups["value"]!!.value.toLong())
            "b" -> Data(match.groups["value"]!!.value.toLong())
            else -> {
                val throwable = ConfigurateException(node, "Invalid data format")
                logger.warn(throwable) {
                    "Unable to parse data from node: $node" +
                        "\n\t\tExpected format: <identifier> <value>" +
                        "\n\t\tExpected identifiers: b, kb, mb, gb, tb" +
                        "\n\t\tReceived: ${node.get<String>()}"
                }
                throw throwable
            }
        }
    }

    override fun serialize(
        type: Type,
        obj: Data?,
        node: ConfigurationNode,
    ) {
        if (obj == null) { node.raw(null); return }
        when {
            obj.terabytes > 0 -> node.set("${obj.terabytes}TB")
            obj.gigabytes > 0 -> node.set("${obj.gigabytes}GB")
            obj.megabytes > 0 -> node.set("${obj.megabytes}MB")
            obj.kilobytes > 0 -> node.set("${obj.kilobytes}KB")
            else -> node.set("${bytes}B")
        }
    }

    // TODO: There has to be a better way to do this
    fun humanReadableSize(): String {
        return when {
            terabytes > 0 -> "${terabytes}TB"
            gigabytes > 0 -> "${gigabytes}GB"
            megabytes > 0 -> "${megabytes}MB"
            kilobytes > 0 -> "${kilobytes}KB"
            else -> "${bytes}B"
        }
    }

    override fun toString(): String {
        return "${bytes}B"
    }

    override fun hashCode(): Int {
        return bytes.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Data) {
            return bytes == other.bytes
        }
        return false
    }

    companion object {
        private val regex = Regex("(?<size>[0-9]+)(?<identifier>[a-z]+)")
        private val logger by lazy { getKoin().get<Minix>().log }

        fun fromKilobytes(kilobytes: Long): Data = Data(kilobytes * 1024)
        fun fromMegabytes(megabytes: Long): Data = Data(megabytes * 1048576)
        fun fromGigabytes(gigabytes: Long): Data = Data(gigabytes * 1073741824)
        fun fromTerabytes(terabytes: Long): Data = Data(terabytes * 1099511627776)
    }
}

fun Long.kilobytes(): Data = Data.fromKilobytes(this)
fun Long.megabytes(): Data = Data.fromMegabytes(this)
fun Long.gigabytes(): Data = Data.fromGigabytes(this)
fun Long.terabytes(): Data = Data.fromTerabytes(this)
