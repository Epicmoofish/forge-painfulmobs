package net.oceanic.impossibledifficulty.packet;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("impossibledifficulty", "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private PacketHandler() {
    }

    public static void init() {
        int index = 0;
        INSTANCE.messageBuilder(ClientUpdateLastFoodPacket.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ClientUpdateLastFoodPacket::encode).decoder(ClientUpdateLastFoodPacket::new)
                .consumer(ClientUpdateLastFoodPacket::handle).add();
        INSTANCE.messageBuilder(ClientNukeExplosionPacket.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ClientNukeExplosionPacket::encode).decoder(ClientNukeExplosionPacket::new)
                .consumer(ClientNukeExplosionPacket::handle).add();
        INSTANCE.messageBuilder(ClientAirPacket.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ClientAirPacket::encode).decoder(ClientAirPacket::new)
                .consumer(ClientAirPacket::handle).add();
    }
}