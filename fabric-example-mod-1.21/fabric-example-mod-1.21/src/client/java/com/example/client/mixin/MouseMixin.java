package com.example.client.mixin;

import com.example.client.FrictionConfig;
import net.minecraft.client.Minecraft; // In Mojang Mappings oft Minecraft statt MinecraftClient
import net.minecraft.client.MouseHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MouseHandler.class)
public class MouseMixin {
    // In Mojang Mappings heißt das Feld meistens 'minecraft' und die Klasse 'Minecraft'
    @Shadow @Final private Minecraft minecraft;

    // Falls 'updateMouse' rot ist, heißt die Methode in Mojang Mappings 'turnPlayer'
    // Wir versuchen es mit 'turnPlayer', das ist die gängige Methode für 1.21
    @ModifyVariable(method = "turnPlayer", at = @At("STORE"), ordinal = 2)
    private double applyFriction(double d) {
        // In Mojang Mappings heißt das Ziel 'hitResult'
        if (FrictionConfig.enabled && minecraft.hitResult != null && minecraft.hitResult.getType() == HitResult.Type.ENTITY) {
            if (((EntityHitResult)minecraft.hitResult).getEntity() instanceof Player) {
                return d * FrictionConfig.frictionFactor;
            }
        }
        return d;
    }
}