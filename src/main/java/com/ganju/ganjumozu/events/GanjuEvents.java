package com.ganju.ganjumozu.events;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.client.hud.GoldCurseOverlay;
import com.ganju.ganjumozu.registries.GanjuItems;
import com.ganju.ganjumozu.registries.GanjuMenuTypes;
import com.ganju.ganjumozu.registries.items.LetterItem;
import com.ganju.ganjumozu.registries.screens.LetterScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class GanjuEvents {
    @Mod.EventBusSubscriber(modid = Ganju.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientBus {
        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("gold_curse", GoldCurseOverlay.HUD_GOLD_CURSE);
        }

        @SubscribeEvent
        public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemProperties.register(GanjuItems.LETTER.get(), new ResourceLocation(Ganju.MODID, "open"),
                        (stack, level, living, id) -> LetterItem.isOpen(stack));
            });
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(GanjuMenuTypes.LETTER_MENU.get(), LetterScreen::new);
        }
    }

    @Mod.EventBusSubscriber(modid = Ganju.MODID)
    public static class Server {
    }
}
