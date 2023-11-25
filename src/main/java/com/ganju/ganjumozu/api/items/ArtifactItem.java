package com.ganju.ganjumozu.api.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArtifactItem extends Item {
    private final Component description;
    private final ArtifactRarity rarity;

    public ArtifactItem(Properties properties, ArtifactRarity rarity, MutableComponent description) {
        super(properties);
        this.description = description.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);
        this.rarity = rarity;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        tooltip.add(description);
        tooltip.add(MutableComponent.create(ComponentContents.EMPTY)
                .append(rarity.getName())
                .append(" ")
                .append(Component.literal("Artifact").withStyle(ChatFormatting.BLUE)));
    }
}
