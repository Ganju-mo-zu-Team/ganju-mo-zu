package com.ganju.ganjumozu.network.packets;

import com.ganju.ganjumozu.capabilities.GanjuCapabilities;
import com.ganju.ganjumozu.capabilities.ILuoto;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CLoutoSync {

    public S2CLoutoSync() {
    }

    public S2CLoutoSync(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayer serverPlayer = supplier.get().getSender();
            LocalPlayer clientPlayer = Minecraft.getInstance().player;

            clientPlayer.getCapability(GanjuCapabilities.LUOTO).ifPresent((louto) -> {
                ILuoto serverLouto = serverPlayer.getCapability(GanjuCapabilities.LUOTO).orElseThrow(NullPointerException::new);
                louto.setLuotoStored(serverLouto.getLuotoStored());
                louto.setLuotoCapacity(serverLouto.getLoutoCapacity());
                if (serverLouto.hasUnlockedLouto()) {
                    louto.unlockLouto();
                }
            });
        });
        return true;
    }
}
