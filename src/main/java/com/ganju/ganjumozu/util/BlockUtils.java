package com.ganju.ganjumozu.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BlockUtils {
    public static void setAndUpdate(Level level, BlockPos blockPos, BlockState oldState, BlockState newState) {
        level.setBlock(blockPos, newState, 2);
        level.sendBlockUpdated(blockPos, oldState, newState, 11);
    }
}
