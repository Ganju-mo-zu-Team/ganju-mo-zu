package com.ganju.ganjumozu.registries.blocks;

import com.ganju.ganjumozu.registries.GanjuBlockEntityTypes;
import com.ganju.ganjumozu.registries.GanjuBlocks;
import com.ganju.ganjumozu.registries.blockentities.MonolithBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MonolithBlock extends BaseEntityBlock {
    public static final EnumProperty<MonolithState> MONOLITH_PART = EnumProperty.create("monolith_part", MonolithState.class);
    public static final BooleanProperty CHARGED = BooleanProperty.create("charged");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public MonolithBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        if (p_60555_.getValue(MONOLITH_PART).equals(MonolithState.BASE)) {
            return Shapes.or(Block.box(2, 4, 3, 14, 16, 13), Block.box(0, 0, 0, 16, 4, 16));
        }
        return Block.box(2, 0, 3, 14, 16, 13);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(CHARGED, false).setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity entity, ItemStack itemStack) {
        level.setBlockAndUpdate(blockPos.offset(0, 1, 0),
                this.defaultBlockState()
                        .setValue(CHARGED, false)
                        .setValue(MONOLITH_PART, MonolithState.MIDDLE)
                        .setValue(FACING, blockState.getValue(FACING)));
        level.setBlockAndUpdate(blockPos.offset(0, 2, 0),
                this.defaultBlockState()
                        .setValue(CHARGED, false)
                        .setValue(MONOLITH_PART, MonolithState.TOP)
                        .setValue(FACING, blockState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(MONOLITH_PART, CHARGED, FACING);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        if (level.isClientSide()) {
            return createTickerHelper(p_153214_, GanjuBlockEntityTypes.MONOLITH.get(),
                    (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.clientTick(pLevel1, pPos, pState1));
        } else {
            return createTickerHelper(p_153214_, GanjuBlockEntityTypes.MONOLITH.get(),
                    (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new MonolithBlockEntity(p_153215_, p_153216_);
    }

    public enum MonolithState implements StringRepresentable {
        BASE("base"),
        MIDDLE("middle"),
        TOP("top");

        private final String name;

        MonolithState(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
