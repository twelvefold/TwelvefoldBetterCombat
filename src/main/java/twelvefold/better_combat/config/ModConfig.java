//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.config;

import net.minecraftforge.common.config.*;
import twelvefold.better_combat.TFBetterCombat;

@Config(modid = TFBetterCombat.MODID)
public final class ModConfig
{
    public static boolean nonlinearHealthBar;
    public static boolean disableInvul;
    public static boolean modifyShield;
    public static boolean modifyStats;
    public static boolean modifyBow;
    
    private ModConfig() {
    }
    
    static {
        ModConfig.nonlinearHealthBar = true;
        ModConfig.disableInvul = true;
        ModConfig.modifyShield = true;
        ModConfig.modifyStats = true;
        ModConfig.modifyBow = true;
    }
}
