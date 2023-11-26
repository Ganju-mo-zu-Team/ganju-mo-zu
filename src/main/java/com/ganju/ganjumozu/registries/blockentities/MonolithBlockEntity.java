package com.ganju.ganjumozu.registries.blockentities;

import com.ganju.ganjumozu.registries.GanjuBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MonolithBlockEntity extends BlockEntity {
    public MonolithBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(GanjuBlockEntityTypes.MONOLITH.get(), p_155229_, p_155230_);
    }

    public void clientTick(Level level, BlockPos blockPos, BlockState blockState) {

    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {

    }
}
