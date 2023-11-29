package com.ganju.ganjumozu.registries.blocks;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.capabilities.GanjuCapabilities;
import com.ganju.ganjumozu.capabilities.ILuoto;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MonolithBlock extends Block {
    public static final EnumProperty<MonolithState> MONOLITH_PART = EnumProperty.create("monolith_part", MonolithState.class);
    public static final BooleanProperty CHARGED = BooleanProperty.create("charged");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

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
        return super.getStateForPlacement(context)
                .setValue(CHARGED, false)
                .setValue(ACTIVATED, false)
                .setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity entity, ItemStack itemStack) {
        level.setBlockAndUpdate(blockPos.offset(0, 1, 0),
                this.defaultBlockState()
                        .setValue(CHARGED, false)
                        .setValue(ACTIVATED, false)
                        .setValue(MONOLITH_PART, MonolithState.MIDDLE)
                        .setValue(FACING, blockState.getValue(FACING)));
        level.setBlockAndUpdate(blockPos.offset(0, 2, 0),
                this.defaultBlockState()
                        .setValue(CHARGED, false)
                        .setValue(ACTIVATED, false)
                        .setValue(MONOLITH_PART, MonolithState.TOP)
                        .setValue(FACING, blockState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(MONOLITH_PART, CHARGED, FACING, ACTIVATED);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult p_60508_) {
        ILuoto playerLouto = player.getCapability(GanjuCapabilities.LUOTO).orElseThrow(NullPointerException::new);
        Ganju.LOGGER.info("Unlocked luoto: "+playerLouto.hasUnlockedLouto());
        // todo sync between client and server
        if (!playerLouto.hasUnlockedLouto()) {
            if (player.getItemInHand(interactionHand).is(Items.EMERALD) && hasUnchargedPart(blockState, level, blockPos)) {
                chargeObelisk(blockState, level, blockPos);
                if (!player.isCreative()) {
                    player.getItemInHand(interactionHand).shrink(1);
                }
                return InteractionResult.SUCCESS;
            } else if (!hasUnchargedPart(blockState, level, blockPos) && !isActivated(blockState, level, blockPos)) {
                activateObelisk(blockState, level, blockPos);
                if (!level.isClientSide()) {
                    level.explode(null, player.getX(), player.getY(), player.getZ(), 4.3F, Level.ExplosionInteraction.NONE);
                }
                player.displayClientMessage(Component.translatable("lore.ganju.unlocked_luoto")
                        .append(": ")
                        .append(Component.literal("Louto")
                                .withStyle(ChatFormatting.BLUE)), true);
                playerLouto.unlockLouto();
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    private static BlockPos getBaseBlockPos(BlockState originState, BlockPos originPos) {
        return switch (originState.getValue(MONOLITH_PART)) {
            case BASE -> originPos;
            case MIDDLE -> originPos.offset(0, -1, 0);
            case TOP -> originPos.offset(0, -2, 0);
        };
    }

    public static boolean isActivated(BlockState originState, Level level, BlockPos originPos) {
        BlockPos baseBlockPos = getBaseBlockPos(originState, originPos);
        return level.getBlockState(baseBlockPos.offset(0, 2, 0)).getValue(ACTIVATED);
    }

    private static boolean hasUnchargedPart(BlockState originState, Level level, BlockPos originPos) {
        BlockPos baseBlockPos = getBaseBlockPos(originState, originPos);
        for (int i = 0; i < 3; i++) {
            BlockPos offsetPos = baseBlockPos.offset(0, i, 0);
            try {
                if (!level.getBlockState(offsetPos).getValue(CHARGED)) {
                    return true;
                }
            } catch (Exception ignored) {
                Ganju.LOGGER.info("Failed to check if block is charged");
            }
        }
        return false;
    }

    public static void chargeObelisk(BlockState originState, Level level, BlockPos originPos) {
        BlockPos baseBlockPos = getBaseBlockPos(originState, originPos);
        for (int i = 0; i < 3; i++) {
            BlockPos offsetPos = baseBlockPos.offset(0, i, 0);
            if (!level.getBlockState(offsetPos).getValue(CHARGED)) {
                level.setBlockAndUpdate(offsetPos, level.getBlockState(offsetPos).setValue(CHARGED, true));
                break;
            }
        }
    }


    public static void activateObelisk(BlockState originState, Level level, BlockPos originPos) {
        BlockPos baseBlockPos = getBaseBlockPos(originState, originPos);
        for (int i = 0; i < 3; i++) {
            BlockPos offsetPos = baseBlockPos.offset(0, i, 0);
            if (!level.getBlockState(offsetPos).getValue(ACTIVATED)) {
                level.setBlockAndUpdate(offsetPos, level.getBlockState(offsetPos).setValue(ACTIVATED, true));
            }
        }
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(CHARGED)) {
            double d0 = (double) blockPos.getX() + 0.5D + (0.5D - randomSource.nextDouble());
            double d1 = (double) blockPos.getY() + 1.0D + (0.5D - randomSource.nextDouble());
            double d2 = (double) blockPos.getZ() + 0.5D + (0.5D - randomSource.nextDouble());
            double d3 = (double) randomSource.nextFloat() * 0.4D;
            level.addParticle(ParticleTypes.HAPPY_VILLAGER, d0, d1, d2, 0.0D, d3, 0.0D);
        }
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
