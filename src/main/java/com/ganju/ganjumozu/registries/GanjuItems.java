package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.api.items.ArtifactItem;
import com.ganju.ganjumozu.registries.blocks.MonolithBlock;
import com.ganju.ganjumozu.registries.items.*;
import com.ganju.ganjumozu.tiers.ArtifactRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GanjuItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ganju.MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
    public static final RegistryObject<Item> ARTIFACT = ITEMS.register("artifact", () ->
            new ArtifactItem(new Item.Properties(), ArtifactRarities.RARE, Component.literal("A long forgotten artifact...")));
    public static final RegistryObject<Item> BAMBOOTANA = ITEMS.register("bambootana", () ->
            new BambootanaItem(new Item.Properties()));
    public static final RegistryObject<Item> TRAINEE_STAFF = ITEMS.register("trainee_staff", () ->
            new TraineeStaffItem(new Item.Properties()));
    public static final RegistryObject<Item> CRACKED_POT = ITEMS.register("cracked_pot", () ->
            new CrackedPotItem(new Item.Properties()));
    public static final RegistryObject<Item> LETTER = ITEMS.register("letter", () ->
            new LetterItem(new Item.Properties()));
    public static final RegistryObject<Item> MONOLITH = ITEMS.register("monolith",
            () -> new MonolithItem(GanjuBlocks.MONOLITH.get(), new Item.Properties()));
}
