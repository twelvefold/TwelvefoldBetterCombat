//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.enchantments.EnchantmentCleaving;
import twelvefold.better_combat.misc.IShieldBreaker;
import twelvefold.better_combat.misc.MiscUtils;

import java.util.Set;

@Mixin({ ItemAxe.class })
public abstract class MixinAxe extends ItemTool implements IShieldBreaker
{
    private MixinAxe() {
        super(null, (Set)null);
        MiscUtils.assertFalse();
    }
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void inject_Init(final CallbackInfo ci) {
        if (ModConfig.modifyStats) {
            this.attackDamage = 3.0f + this.toolMaterial.getAttackDamage();
            this.attackSpeed = -2.0f;
        }
    }
    
    public int getShieldBreakTime(final ItemStack stack, final ItemStack shield, final EntityLivingBase entity, final EntityLivingBase attacker) {
        final int cleavingLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentCleaving.CLEAVING, stack);
        return 32 + 10 * cleavingLevel;
    }
}
