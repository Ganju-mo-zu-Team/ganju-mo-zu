package com.ganju.ganjumozu.registries.screens;

import com.ganju.ganjumozu.registries.GanjuItems;
import com.ganju.ganjumozu.registries.items.LetterItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class LetterScreen extends AbstractContainerScreen<LetterMenu> {
    public LetterScreen(LetterMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, "Hello", 100, 100, 1);
    }

    @Override
    public void onClose() {
        super.onClose();
        Player player = Minecraft.getInstance().player;

        InteractionHand hand;
        if (player.getMainHandItem().getItem() instanceof LetterItem)
            hand = InteractionHand.MAIN_HAND;
        else if (player.getOffhandItem().getItem() instanceof LetterItem)
            hand = InteractionHand.OFF_HAND;
        else return;

        menu.onClose(hand);
    }
}
