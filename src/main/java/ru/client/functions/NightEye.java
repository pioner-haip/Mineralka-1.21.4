package ru.client.functions;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;


public class NightEye {
    public static void On(MinecraftClient client) {
            if (client.player != null) {
                client.player.addStatusEffect(
                        new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false, false)
                );
            }
    }
    public static void Off(MinecraftClient client) {
        if (client.player != null) {
            client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}