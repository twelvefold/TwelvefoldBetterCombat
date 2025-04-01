//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.misc;

import net.minecraft.item.*;

public final class HoeHelper
{
    private static final float[] attackSpeeds;
    private static final float[] attackDamage;
    
    private HoeHelper() {
    }
    
    public static float getAttackSpeed(final Item.ToolMaterial material) {
        return HoeHelper.attackSpeeds[material.ordinal()];
    }
    
    public static float getAttackDamage(final Item.ToolMaterial material) {
        return HoeHelper.attackDamage[material.ordinal()];
    }
    
    static {
        attackSpeeds = new float[] { 2.0f, 2.5f, 3.0f, 3.5f, 3.5f };
        attackDamage = new float[] { 1.0f, 1.0f, 2.0f, 2.0f, 1.0f };
    }
}
