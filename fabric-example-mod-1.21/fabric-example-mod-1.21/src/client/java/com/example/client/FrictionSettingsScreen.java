package com.example.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

public class FrictionSettingsScreen extends Screen {
    public FrictionSettingsScreen() {
        super(Component.literal("Friction Settings"));
    }

    @Override
    protected void init() {
        //  Slider
        this.addRenderableWidget(new AbstractSliderButton(this.width / 2 - 100, this.height / 2 - 20, 200, 20, Component.literal("Friction"), (double) FrictionConfig.frictionFactor) {
            {
                this.updateMessage();
            }

            @Override
            protected void updateMessage() {
                this.setMessage(Component.literal("Speed auf Gegner: " + (int)(FrictionConfig.frictionFactor * 100) + "%"));
            }

            @Override
            protected void applyValue() {
                FrictionConfig.frictionFactor = (float) this.value;
            }
        });

        //  Button
        this.addRenderableWidget(Button.builder(Component.literal("Fertig"), button -> this.onClose())
                .bounds(this.width / 2 - 100, this.height / 2 + 20, 200, 20)
                .build());//dude what are u searching for ?
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // In 1.21 nutzt man renderTransparentBackground für das normale Menü-Verwischen
        this.renderTransparentBackground(guiGraphics);


        // guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}