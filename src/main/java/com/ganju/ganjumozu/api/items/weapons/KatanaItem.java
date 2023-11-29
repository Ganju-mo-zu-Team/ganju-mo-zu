package com.ganju.ganjumozu.api.items.weapons;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.world.item.WeaponItem;

import java.util.List;

public abstract class KatanaItem extends WeaponItem {
    private final String desc;
    private final Tier tier;
    public KatanaItem(Tier tier, float attackSpeed, String description, Properties properties) {
        super(tier, 0, attackSpeed, properties);
        desc = description;
        this.tier = tier;
    }

    @Override
    public Tier getTier() {
        return tier;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal(getTier().toString()));
    }
}
