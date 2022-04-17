package net.oceanic.impossibledifficulty.packagehandler;

import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.realms.DisconnectedRealmsScreen;
import net.minecraft.realms.RealmsScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.oceanic.impossibledifficulty.packet.ClientAirPacket;
import net.oceanic.impossibledifficulty.packet.ClientNukeExplosionPacket;
import net.oceanic.impossibledifficulty.packet.ClientUpdateLastFoodPacket;

@OnlyIn(Dist.CLIENT)
public class ClientGamePacketHandlers implements ClientPacketHandlers{
    @Override
    public void handleExplosion(ClientNukeExplosionPacket p_131394_) {

    }

    @Override
    public void handleAirPacket(ClientAirPacket p_131396_) {

    }

    @Override
    public void handleLastFoodPacket(ClientUpdateLastFoodPacket p_131398_) {

    }

    @Override
    public void onDisconnect(Component p_130552_) {
        this.minecraft.clearLevel();
        this.telemetryManager.onDisconnect();
        if (this.callbackScreen != null) {
            if (this.callbackScreen instanceof RealmsScreen) {
                this.minecraft.setScreen(new DisconnectedRealmsScreen(this.callbackScreen, GENERIC_DISCONNECT_MESSAGE, p_104954_));
            } else {
                this.minecraft.setScreen(new DisconnectedScreen(this.callbackScreen, GENERIC_DISCONNECT_MESSAGE, p_104954_));
            }
        } else {
            this.minecraft.setScreen(new DisconnectedScreen(new JoinMultiplayerScreen(new TitleScreen()), GENERIC_DISCONNECT_MESSAGE, p_104954_));
        }

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
