//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.misc;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

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

}
