//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.misc.MiscUtils;

@Mixin({ EntityPlayerMP.class })
public abstract class MixinPlayerMP extends EntityPlayer
{
    @Shadow
    private int respawnInvulnerabilityTicks;
    
    private MixinPlayerMP() {
        super(null, null);
        MiscUtils.assertFalse();
    }
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void inject_Init(final CallbackInfo ci) {
        if (ModConfig.disableInvul) {
            this.respawnInvulnerabilityTicks = -1;
        }
    }
}
