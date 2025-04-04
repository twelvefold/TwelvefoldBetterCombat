package twelvefold.better_combat.core.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityMob.class)
public class MixinMob {
    @Redirect(method = "attackEntityAsMob",at = @At(value = "INVOKE",target = "Lnet/minecraft/item/Item;canDisableShield(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/EntityLivingBase;)Z",remap = false))
    private boolean redirect_blockUsingShield(Item instance, ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker)
    {
        return false;
    }
}
