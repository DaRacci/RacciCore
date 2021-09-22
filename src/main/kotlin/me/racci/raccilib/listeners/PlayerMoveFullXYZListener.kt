@file:Suppress("unused")
@file:JvmName("PlayerMoveFullXYZListener")
package me.racci.raccilib.listeners

import me.racci.raccilib.RacciLib
import me.racci.raccilib.events.PlayerEnterLiquidEvent
import me.racci.raccilib.events.PlayerExitLiquidEvent
import me.racci.raccilib.events.PlayerMoveFullXYZEvent
import me.racci.raccilib.skedule.SynchronizationContext
import me.racci.raccilib.skedule.schedule
import me.racci.raccilib.skedule.skeduleAsync
import me.racci.raccilib.utils.blocks.isLiquid
import org.bukkit.Bukkit
import org.bukkit.block.Block
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitScheduler

class PlayerMoveFullXYZListener(
    private val plugin: RacciLib,
): Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onPlayerMoveFullXYZEvent(event: PlayerMoveFullXYZEvent) {
        skeduleAsync(plugin) {
            val from: Block = event.from.block
            val to: Block = event.to.block
            val var1: Int = isLiquid(from)
            val var2: Int = isLiquid(to)
            var newEvent: Event? = null
            if (var1 == 0) {
                newEvent = when (var2) {
                    1, 2 -> PlayerEnterLiquidEvent(event.player, var2, from, to)
                    else -> null
                }
            } else if (var2 == 0) {
                newEvent = when (var1) {
                    1, 2 -> PlayerExitLiquidEvent(event.player, var1, from, to)
                    else -> null
                }
            }
            if (newEvent != null) {
                Bukkit.getPluginManager().callEvent(newEvent)
            }
        }
    }
}