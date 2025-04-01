//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.misc.MiscUtils;

@Mixin({ ItemPickaxe.class })
public abstract class MixinPickaxe extends ItemTool
{
    public MixinPickaxe() {
        super(null, null);
        MiscUtils.assertFalse();
    }
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void inject_Init(final CallbackInfo ci) {
        if (ModConfig.modifyStats) {
            this.attackDamage = this.toolMaterial.getAttackDamage() + 1.0f;
            this.attackSpeed = -2.0f;
        }
    }
}
