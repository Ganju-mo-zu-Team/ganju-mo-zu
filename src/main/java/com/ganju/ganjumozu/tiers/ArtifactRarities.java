package com.ganju.ganjumozu.tiers;

import com.ganju.ganjumozu.api.items.ArtifactRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum ArtifactRarities implements ArtifactRarity {
    COMMON("Common", ChatFormatting.WHITE),
    UNCOMMON("Uncommon", ChatFormatting.GREEN),
    RARE("Rare", ChatFormatting.BLUE),
    EPIC("Epic", ChatFormatting.LIGHT_PURPLE),
    LEGENDARY("Legendary", ChatFormatting.GOLD);

    private final Component name;

    ArtifactRarities(String name, ChatFormatting color) {
        this.name = Component.literal(name).withStyle(color);
    }

    @Override
    public Component getName() {
        return name;
    }
}
