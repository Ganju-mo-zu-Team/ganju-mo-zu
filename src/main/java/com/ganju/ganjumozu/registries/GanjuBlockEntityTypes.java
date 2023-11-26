package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.registries.blockentities.MonolithBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GanjuBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Ganju.MODID);

    public static final RegistryObject<BlockEntityType<MonolithBlockEntity>> MONOLITH =
            BLOCK_ENTITIES.register("monolith", () ->
                    BlockEntityType.Builder.of(MonolithBlockEntity::new,
                            GanjuBlocks.MONOLITH.get()).build(null));
}
