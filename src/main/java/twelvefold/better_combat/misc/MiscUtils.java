//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.misc;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import java.util.*;

public final class MiscUtils
{
    private MiscUtils() {
        assertFalse();
    }
    
    public static void assertFalse() {
        assert false;
    }
    
    public static boolean canBlockDamageSource(final EntityLivingBase entityIn, final DamageSource damageSourceIn) {
        if (!damageSourceIn.isUnblockable() && entityIn.isActiveItemStackBlocking()) {
            final Vec3d vec3d = damageSourceIn.getDamageLocation();
            if (vec3d != null) {
                final Vec3d vec3d2 = entityIn.getLook(1.0f);
                Vec3d vec3d3 = vec3d.subtractReverse(new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ)).normalize();
                vec3d3 = new Vec3d(vec3d3.x, 0.0, vec3d3.z);
                return vec3d3.dotProduct(vec3d2) < 0.0;
            }
        }
        return false;
    }
    
    public static ItemStack findAmmo(final EntityPlayer player) {
        if (isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        if (isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            final ItemStack itemstack = player.inventory.getStackInSlot(i);
            if (isArrow(itemstack)) {
                return itemstack;
            }
        }
        return ItemStack.EMPTY;
    }
    
    public static boolean isArrow(final ItemStack stack) {
        return stack.getItem() instanceof ItemArrow;
    }
}
