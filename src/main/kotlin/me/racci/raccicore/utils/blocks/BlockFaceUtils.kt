package me.racci.raccicore.utils.blocks

import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockFace.DOWN
import org.bukkit.block.BlockFace.EAST
import org.bukkit.block.BlockFace.EAST_NORTH_EAST
import org.bukkit.block.BlockFace.EAST_SOUTH_EAST
import org.bukkit.block.BlockFace.NORTH
import org.bukkit.block.BlockFace.NORTH_EAST
import org.bukkit.block.BlockFace.NORTH_NORTH_EAST
import org.bukkit.block.BlockFace.NORTH_NORTH_WEST
import org.bukkit.block.BlockFace.NORTH_WEST
import org.bukkit.block.BlockFace.SELF
import org.bukkit.block.BlockFace.SOUTH
import org.bukkit.block.BlockFace.SOUTH_EAST
import org.bukkit.block.BlockFace.SOUTH_SOUTH_EAST
import org.bukkit.block.BlockFace.SOUTH_SOUTH_WEST
import org.bukkit.block.BlockFace.SOUTH_WEST
import org.bukkit.block.BlockFace.UP
import org.bukkit.block.BlockFace.WEST
import org.bukkit.block.BlockFace.WEST_NORTH_WEST
import org.bukkit.block.BlockFace.WEST_SOUTH_WEST
import org.bukkit.block.data.Directional
import org.jetbrains.annotations.ApiStatus

/**
 * Returns the opposite BlockFace for a given BlockFace. E.g. EAST_NORTH_EAST will return WEST_SOUTH_WEST. SELF will return SELF.
 *
 * @param face Original BlockFace
 * @return Opposite BlockFace
 */
@Deprecated("Moved", ReplaceWith("BlockUtils.getOpposite"))
@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
fun getOpposite(face: BlockFace?): BlockFace {
    when (face) {
        UP -> return DOWN
        DOWN -> return UP
        SOUTH -> return NORTH
        NORTH -> return SOUTH
        EAST -> return WEST
        WEST -> return EAST
        SOUTH_EAST -> return NORTH_WEST
        SOUTH_WEST -> return NORTH_EAST
        SOUTH_SOUTH_EAST -> return NORTH_NORTH_WEST
        SOUTH_SOUTH_WEST -> return NORTH_NORTH_EAST
        NORTH_EAST -> return SOUTH_WEST
        NORTH_WEST -> return SOUTH_EAST
        NORTH_NORTH_EAST -> return SOUTH_SOUTH_WEST
        NORTH_NORTH_WEST -> return SOUTH_SOUTH_EAST
        EAST_NORTH_EAST -> return WEST_SOUTH_WEST
        EAST_SOUTH_EAST -> return WEST_NORTH_WEST
        WEST_NORTH_WEST -> return EAST_SOUTH_EAST
        WEST_SOUTH_WEST -> return EAST_NORTH_EAST
        SELF -> return SELF
    }
    throw IllegalArgumentException()
}

/**
 * Gets the block another block (e.g. a ladder) is attached to
 *
 * @param directional Block to check
 * @return Block that supports the block to check
 */
@Deprecated("Moved", ReplaceWith("BlockUtils.getSupportingBlock"))
@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
fun getSupportingBlock(directional: Block): Block {
    if (directional.blockData is Directional) {
        return directional.getRelative(getOpposite((directional.blockData as Directional).facing))
    }
    throw IllegalArgumentException("Provided Block's BlockData is not an instance of Directional")
}

/**
 * Gets the BlockFace of the existing block that must have been right-clicked to place the new Block
 *
 * @param existing Existing block
 * @param newBlock New block
 * @return Existing block's BlockFace that must have been right-clicked to place the new block
 */
@Deprecated("Moved", ReplaceWith("BlockUtils.getPlacedAgainstFace"))
@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
fun getPlacedAgainstFace(existing: Block, newBlock: Block?): BlockFace {
    for (blockFace in BlockFace.values()) {
        if (existing.getRelative(blockFace) == newBlock) return blockFace
    }
    throw IllegalArgumentException("No BlockFace found")
}