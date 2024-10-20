package com.ganju.ganjumozu.client.hud;

import com.ganju.ganjumozu.registries.GanjuEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GoldCurseOverlay {
    public static final IGuiOverlay HUD_GOLD_CURSE = ((gui, guiGraphics, partialTick, width, height) -> {
        int x = width / 2;
        int y = height / 2;
        ResourceLocation location = new ResourceLocation("textures/misc/powder_snow_outline.png");
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player.hasEffect(GanjuEffects.GOLDEN_CURSE.get())) {
            guiGraphics.blit(location, 0, 0, -90, 0.0F, 0.0F, width, height, height, width);
        }
    });
}
