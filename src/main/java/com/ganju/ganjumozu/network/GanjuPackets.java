package com.ganju.ganjumozu.network;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.network.packets.S2CLoutoSync;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class GanjuPackets {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Ganju.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(S2CLoutoSync.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CLoutoSync::new)
                .encoder(S2CLoutoSync::toBytes)
                .consumerMainThread(S2CLoutoSync::handle)
                .add();

        Ganju.LOGGER.info("registering packets");
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
        Ganju.LOGGER.info("sending to server");
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
        Ganju.LOGGER.info("sending to all clients");
    }
}
