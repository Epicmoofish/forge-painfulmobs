package net.oceanic.impossibledifficulty.packagehandler;

import net.minecraft.network.PacketListener;
import net.oceanic.impossibledifficulty.packet.ClientAirPacket;
import net.oceanic.impossibledifficulty.packet.ClientNukeExplosionPacket;
import net.oceanic.impossibledifficulty.packet.ClientUpdateLastFoodPacket;

public interface ClientPacketHandlers extends PacketListener  {
    void handleExplosion(ClientNukeExplosionPacket p_131394_);

    void handleAirPacket(ClientAirPacket p_131396_);

    void handleLastFoodPacket(ClientUpdateLastFoodPacket p_131398_);
}
