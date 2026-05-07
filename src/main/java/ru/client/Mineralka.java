package ru.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import ru.client.functions.NightEye;
import ru.client.functions.Reconnect;
import ru.client.functions.owoGui.MyFirstScreen;



public class Mineralka implements ModInitializer {

    private static KeyBinding openMenuKey;
    private boolean nightEyeEnabled = false;

    @Override
    public void onInitialize() {

        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "swat",          // перевод в lang файле, если хочешь
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,         // правый шифт
                "mineralka"               // категория в настройках управления
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                nightEyeEnabled = !nightEyeEnabled;

                if (client.player != null) {
                    client.player.sendMessage(Text.literal("NightEye: " + (nightEyeEnabled ? "ВКЛ" : "ВЫКЛ") + " TEST"), true);
                }

                if (!nightEyeEnabled) {
                    NightEye.Off(client);
                }
                if (nightEyeEnabled) {
                    NightEye.On(client);
                }
            }});
        //Reconnect.init();

        KeyBinding yourKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "css",          // перевод в lang файле, если хочешь
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,         // правый шифт
                "mineralka"               // категория в настройках управления
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (yourKeyBinding.wasPressed()) {
                client.setScreen(new MyFirstScreen());
            }
        });

    }
}