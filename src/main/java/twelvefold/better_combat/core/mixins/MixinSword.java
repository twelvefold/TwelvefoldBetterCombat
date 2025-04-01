//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twelvefold.better_combat.config.ModConfig;

@Mixin({ ItemSword.class })
public class MixinSword extends Item
{
    @Shadow
    @Final
    @Mutable
    private float attackDamage;
    @Shadow
    @Final
    private Item.ToolMaterial material;
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void inject_Init(final CallbackInfo ci) {
        if (ModConfig.modifyStats) {
            this.attackDamage = 2.0f + this.material.getAttackDamage();
        }
    }
    
    @Inject(method = { "getItemAttributeModifiers" }, at = { @At("RETURN") }, cancellable = true)
    private void inject_getItemAttributeModifiers(final EntityEquipmentSlot equipmentSlot, final CallbackInfoReturnable<Multimap<String, AttributeModifier>> cir) {
        if (!ModConfig.modifyStats) {
            return;
        }
        final Multimap<String, AttributeModifier> multimap = cir.getReturnValue();
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(MixinSword.ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.0, 0));
        }
        cir.setReturnValue(multimap);
    }
}
