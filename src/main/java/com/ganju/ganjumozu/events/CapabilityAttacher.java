package com.ganju.ganjumozu.events;

import com.ganju.ganjumozu.Ganju;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Ganju.MODID)
public class CapabilityAttacher {
    @SubscribeEvent
    public static void onCapabilityAttachLevel(AttachCapabilitiesEvent<Player> event) {
        // event.addCapability(null, null);
        Ganju.LOGGER.info("Attaching Louto to player");
    }
}
