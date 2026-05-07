package ru.client.functions;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Reconnect {

    public static class ConnectionData {
        public static ServerInfo lastServer;
        public static ServerAddress lastAddress;

        public static void save(ServerInfo info, ServerAddress address) {
            lastServer = info;
            lastAddress = address;
        }
    }
    public static class Recon {
            public static void time(MinecraftClient client) {
                if (ConnectionData.lastServer == null) return;

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        client.execute(() -> {
                            ConnectScreen.connect(new TitleScreen(), client, ConnectionData.lastAddress, ConnectionData.lastServer, false, null);
                        });
                    }
                }, 1000);
            }
    }

    public static void init() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            ServerInfo info = client.getCurrentServerEntry();
            if (info != null) {
                ConnectionData.save(info, ServerAddress.parse(info.address));
            }
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            Recon.time(client);
        });
    }
}
