package com.ganju.ganjumozu.registries.items;

import com.ganju.ganjumozu.registries.GanjuItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.List;

public class CrackedPotItem extends Item {
    public CrackedPotItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.playSound(SoundEvents.GLASS_BREAK, 1, 1);
        itemStack.getOrCreateTag().putBoolean("shouldBreak", true);
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(itemStack, level, entity, p_41407_, p_41408_);
        if (itemStack.getOrCreateTag().getBoolean("shouldBreak")) {
            itemStack.shrink(1);
            if (entity instanceof Player player) {
                ItemHandlerHelper.giveItemToPlayer(player, GanjuItems.LETTER.get().getDefaultInstance());
                itemStack.getOrCreateTag().putBoolean("shouldBreak", false);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        tooltip.add(Component.translatable("desc.cracked_pot.0").withStyle(ChatFormatting.GRAY));
    }
}
