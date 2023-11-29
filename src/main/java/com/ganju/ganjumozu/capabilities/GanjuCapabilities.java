package com.ganju.ganjumozu.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class GanjuCapabilities {
    public static final Capability<ILuoto> LUOTO = CapabilityManager.get(new CapabilityToken<>() {
    });
}
