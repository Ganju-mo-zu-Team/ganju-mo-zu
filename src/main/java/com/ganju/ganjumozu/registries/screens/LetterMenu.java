package com.ganju.ganjumozu.registries.screens;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.network.GanjuPackets;
import com.ganju.ganjumozu.network.packets.C2SLetterNBTSync;
import com.ganju.ganjumozu.network.packets.S2CLoutoSync;
import com.ganju.ganjumozu.registries.GanjuMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class LetterMenu extends AbstractContainerMenu {
    private final Inventory inv;
    private final Player player;

    public LetterMenu(int containerId, Inventory inv, FriendlyByteBuf extraData, Player player) {
        super(GanjuMenuTypes.LETTER_MENU.get(), containerId);
        this.inv = inv;
        this.player = player;
    }


    public LetterMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, extraData, inv.player);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }

    public void onClose(InteractionHand interactionHand) {
    }
}
