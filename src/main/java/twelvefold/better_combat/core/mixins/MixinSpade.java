//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.misc.MiscUtils;

@Mixin({ ItemSpade.class })
public abstract class MixinSpade extends ItemTool
{
    private MixinSpade() {
        super(null, null);
        MiscUtils.assertFalse();
    }
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void inject_Init(final CallbackInfo ci) {
        if (ModConfig.modifyStats) {
            this.attackDamage = this.toolMaterial.getAttackDamage();
            this.attackSpeed = -2.0f;
        }
    }
}
