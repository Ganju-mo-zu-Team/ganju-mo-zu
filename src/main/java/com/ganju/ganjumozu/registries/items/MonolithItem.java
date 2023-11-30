package com.ganju.ganjumozu.registries.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class MonolithItem extends BlockItem {
    public MonolithItem(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    @Nullable
    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos basePos = context.getClickedPos();
        for (int i = 1; i < 3; i++) {
            if (!level.getBlockState(basePos.offset(0, i, 0)).is(Blocks.AIR)) {
                return null;
            }
        }
        return context;
    }
}
