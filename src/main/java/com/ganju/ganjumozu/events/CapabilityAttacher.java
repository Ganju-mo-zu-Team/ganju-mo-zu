package com.ganju.ganjumozu.events;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.capabilities.LoutoProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Ganju.MODID)
public class CapabilityAttacher {
    @SubscribeEvent
    public static void onCapabilityAttachPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player){
            event.addCapability(LoutoProvider.IDENT, new LoutoProvider());
            Ganju.LOGGER.info("Attaching Louto to player");
        }
    }
}
