//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twelvefold.better_combat.config.ModConfig;

@Mixin({ Item.class })
public abstract class MixinItem
{
    @Inject(method = { "canDisableShield" }, at = { @At("HEAD") }, cancellable = true, remap = false)
    private void inject_canDisableShield(final CallbackInfoReturnable<Boolean> ci) {
        if (ModConfig.modifyShield) {
            ci.setReturnValue(false);
        }
    }
}
