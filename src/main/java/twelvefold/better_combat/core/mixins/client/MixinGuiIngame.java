package twelvefold.better_combat.core.mixins.client;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import twelvefold.better_combat.config.ModConfig;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Redirect(method = "renderPlayerStats",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/EntityPlayer;getHealth()F"))
    private float redirect_renderPlayerStats(EntityPlayer instance)
    {
        final float originalHealth = instance.getHealth();
        if(!ModConfig.nonlinearHealthBar)
        {
            return originalHealth;
        }
        final float maxHealth = instance.getMaxHealth();
        final float Ln = (originalHealth - 1.0f) * ((originalHealth + 2.0f) / 2.0f) / ((maxHealth - 1.0f) * ((maxHealth + 2.0f) / 2.0f));
        final float moddedHealth = maxHealth * Math.min(1.0f, Ln);
        return (originalHealth <= 0.0f) ? originalHealth : Math.max(moddedHealth, Float.MIN_NORMAL);
    }
}
