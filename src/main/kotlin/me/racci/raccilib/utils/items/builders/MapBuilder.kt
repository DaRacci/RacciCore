@file:Suppress("unused")
@file:JvmName("MapBuilder")
package me.racci.raccilib.utils.items.builders

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapView

/**
 * Item builder for [Material.MAP] only
 *
 * @author GabyTM [https://github.com/iGabyTM](https://github.com/iGabyTM)
 * @since 3.0.1
 */
class MapBuilder : BaseItemBuilder<MapBuilder> {
    internal constructor() : super(ItemStack(MAP))
    internal constructor(itemStack: ItemStack) : super(itemStack) {
        if (itemStack.type != MAP) {
            throw UnsupportedOperationException("BookBuilder requires the material to be a MAP!")
        }
    }

    /**
     * Sets the map color. A custom map color will alter the display of the map
     * in an inventory slot.
     *
     * @param color the color to set
     * @return [MapBuilder]
     * @since 3.0.1
     */
    fun color(color: Color?): MapBuilder {
        val mapMeta = getMeta() as MapMeta
        mapMeta.color = color
        setMeta(mapMeta)
        return this
    }

    /**
     * Sets the location name. A custom map color will alter the display of the
     * map in an inventory slot.
     *
     * @param name the name to set
     * @return [MapMeta]
     * @since 3.0.1
     */
    fun locationName(name: String?): MapBuilder {
        val mapMeta = getMeta() as MapMeta
        mapMeta.locationName = name
        setMeta(mapMeta)
        return this
    }

    /**
     * Sets if this map is scaling or not.
     *
     * @param scaling true to scale
     * @return [MapMeta]
     * @since 3.0.1
     */
    fun scaling(scaling: Boolean): MapBuilder {
        val mapMeta = getMeta() as MapMeta
        mapMeta.isScaling = scaling
        setMeta(mapMeta)
        return this
    }

    /**
     * Sets the associated map. This is used to determine what map is displayed.
     *
     *
     *
     * The implementation **may** allow null to clear the associated map, but
     * this is not required and is liable to generate a new (undefined) map when
     * the item is first used.
     *
     * @param view the map to set
     * @return [MapBuilder]
     * @since 3.0.1
     */
    fun view(view: MapView): MapBuilder {
        val mapMeta = getMeta() as MapMeta
        mapMeta.setMapView(view)
        setMeta(mapMeta)
        return this
    }

    companion object {
        private val MAP = Material.MAP
    }
}