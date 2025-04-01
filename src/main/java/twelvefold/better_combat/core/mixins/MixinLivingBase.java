//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.misc.IEntityLiving;
import twelvefold.better_combat.misc.MiscUtils;

@Mixin({ EntityLivingBase.class })
public abstract class MixinLivingBase extends Entity implements IEntityLiving
{
    @Shadow
    protected float lastDamage;
    
    private MixinLivingBase() {
        super(null);
        MiscUtils.assertFalse();
    }

    
    @Shadow
    protected abstract void damageShield(final float p0);

    
    @Inject(at = { @At("HEAD") }, method = { "canBlockDamageSource" }, cancellable = true)
    private void inject_canBlockDamageSource(final CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
    
    public void resetHurtResistant() {
        this.hurtResistantTime = 0;
        this.lastDamage = 0.0f;
    }
    
    public void entity_damageShield(final float damage) {
        this.damageShield(damage);
    }
}
