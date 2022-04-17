package net.oceanic.impossibledifficulty.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.oceanic.impossibledifficulty.explosions.NukeExplosion;
import net.oceanic.impossibledifficulty.interfaces.PlayerEatingGettingMixin;
import net.oceanic.impossibledifficulty.interfaces.PlayerGettingMixin;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;

import java.util.List;
import java.util.UUID;

public class ClientPacketHandler {
    public static boolean handleLastFood(String lastFood, UUID uuid) {
        if (Minecraft.getInstance().level.getPlayerByUUID(uuid) !=null) {
            ((PlayerEatingGettingMixin)(Minecraft.getInstance().level.getPlayerByUUID(uuid))).setLastFood(lastFood);
            return true;
        }
        return false;
    }
    public static boolean handleExplosion(double x, double y, double z, float power, List<BlockPos> toBlow) {
        NukeExplosion explosion = new NukeExplosion(Minecraft.getInstance().level, (Entity)null, x, y, z, power, toBlow);
        explosion.finalizeExplosion(true);
        return true;
    }
    public static boolean handleAirPacket(int air, UUID uuid, boolean isAquatic) {
        if (Minecraft.getInstance().level.getPlayerByUUID(uuid) !=null) {
            Minecraft.getInstance().level.getPlayerByUUID(uuid).setAirSupply(air);
            if (Minecraft.getInstance().level.getPlayerByUUID(uuid) instanceof LocalPlayer) {
                Player player = Minecraft.getInstance().level.getPlayerByUUID(uuid);
                LocalPlayer localplayer = (LocalPlayer) player;
                PlayerGettingMixin playermixin = (PlayerGettingMixin)localplayer;
                playermixin.setIsAquatic(isAquatic);
                return true;
            }
        }
        return false;
    }
}
