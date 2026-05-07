package ru.client.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "isInvisibleTo", at = @At("HEAD"), cancellable = true)
    private void showInvisibleEntities(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (player.getWorld().isClient) {
            cir.setReturnValue(false);
        }
    }
}