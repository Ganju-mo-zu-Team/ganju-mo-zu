package com.ganju.ganjumozu.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SLetterNBTSync {
    // mainhand = 0
    // offhand = 1
    private final int hand;

    public C2SLetterNBTSync(int hand) {
        this.hand = hand;
    }

    public C2SLetterNBTSync(FriendlyByteBuf buf) {
        this.hand = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(hand);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            LocalPlayer localPlayer = Minecraft.getInstance().player;

            ItemStack letterItemServer;
            ItemStack letterItemClient;
            if (hand == 0) {
                letterItemServer = player.getMainHandItem();
                letterItemClient = localPlayer.getMainHandItem();
            }
            else
                letterItemServer = player.getOffhandItem();
            letterItemClient = localPlayer.getOffhandItem();

            letterItemServer.getOrCreateTag().putBoolean("open", false);
            letterItemClient.getOrCreateTag().putBoolean("open", false);
        });
        return true;
    }
}
