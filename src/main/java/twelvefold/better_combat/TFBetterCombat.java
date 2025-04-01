//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat;

import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.util.Random;

@Mod(modid = TFBetterCombat.MODID, useMetadata = true, acceptedMinecraftVersions = "[1.12.2]",dependencies = "required-after:twelvefoldbooter")
public class TFBetterCombat
{
    public static final String MODID = "tfbettercombat";
    public static final Random rand;
    public static File coremodLocation;

    static {
        rand = new Random();
    }
}
