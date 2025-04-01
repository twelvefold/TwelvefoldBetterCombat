//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core.mixins;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.misc.HoeHelper;

@Mixin({ ItemHoe.class })
public abstract class MixinHoe extends Item
{
    @Shadow
    @Final
    @Mutable
    private float speed;
    @Shadow
    protected Item.ToolMaterial toolMaterial;
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void inject_Init(final CallbackInfo ci) {
        if (!ModConfig.modifyStats) {
            return;
        }
        if (this.toolMaterial.ordinal() <= Item.ToolMaterial.GOLD.ordinal()) {
            this.speed = HoeHelper.getAttackSpeed(this.toolMaterial);
        }
    }
    
    @Inject(method = { "getItemAttributeModifiers" }, at = { @At("RETURN") }, cancellable = true)
    private void inject_getItemAttributeModifiers(final EntityEquipmentSlot equipmentSlot, final CallbackInfoReturnable<Multimap<String, AttributeModifier>> cir) {
        if (!ModConfig.modifyStats) {
            return;
        }
        final Multimap<String, AttributeModifier> multimap = cir.getReturnValue();
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND && this.toolMaterial.ordinal() <= Item.ToolMaterial.GOLD.ordinal()) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(MixinHoe.ATTACK_DAMAGE_MODIFIER, "Weapon modifier", HoeHelper.getAttackDamage(this.toolMaterial), 0));
        }
        cir.setReturnValue(multimap);
    }
}
