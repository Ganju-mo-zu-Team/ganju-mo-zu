package com.ganju.ganjumozu.registries.items;

import com.ganju.ganjumozu.registries.GanjuMenuTypes;
import com.ganju.ganjumozu.registries.screens.LetterMenu;
import com.ganju.ganjumozu.registries.screens.LetterScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LetterItem extends Item {
    public LetterItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.getBoolean("open")){
            tag.putBoolean("open", true);
            player.openMenu(new SimpleMenuProvider((id, inv, player1) -> new LetterMenu(id, inv, null, player), Component.literal("Hello")));
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.fail(itemStack);
    }

    public static float isOpen(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getBoolean("open") ? 1 : 0;
    }
}
