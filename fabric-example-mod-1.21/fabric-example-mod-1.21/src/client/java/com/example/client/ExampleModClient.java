package com.example.client;

import com.example.client.FrictionConfig;
import com.example.client.FrictionSettingsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {


		KeyMapping toggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.friction.toggle",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_H,
				KeyMapping.Category.MISC
		));

		KeyMapping configKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.friction.menu",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_K,
				KeyMapping.Category.MISC
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) return;


			while (toggleKey.consumeClick()) {
				FrictionConfig.enabled = !FrictionConfig.enabled;
				client.player.displayClientMessage(Component.literal("Friction: " + (FrictionConfig.enabled ? "§aAN" : "§cAUS")), true);
			}

			while (configKey.consumeClick()) {
				client.setScreen(new FrictionSettingsScreen());
			}
		});
	}
}