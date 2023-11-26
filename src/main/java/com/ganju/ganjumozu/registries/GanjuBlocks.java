package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.registries.blocks.MonolithBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class GanjuBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ganju.MODID);
    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlockAndItem("example_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> MONOLITH = registerBlockAndItem("monolith",
            () -> new MonolithBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

    private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block) {
        RegistryObject<Block> toReturn = registerBlock(name, block);
        registerItemFromBlock(name, toReturn);
        return toReturn;
    }

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> void registerItemFromBlock(String name, RegistryObject<T> block) {
        GanjuItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
