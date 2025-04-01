//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.core;

import twelvefold.better_combat.TFBetterCombat;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import twelvefold.twelvefoldbooter.TwelvefoldRegistryAPI;

import java.io.File;
import java.util.Map;

public class TFBetterCombatPlugin implements IFMLLoadingPlugin
{
    public TFBetterCombatPlugin()
    {
        TwelvefoldRegistryAPI.enqueueEarlyMixin("mixins.tfbettercombat.early.json");
    }
    
    public String[] getASMTransformerClass() {
        return null;
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        TFBetterCombat.coremodLocation = (File) data.get("coremodLocation");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
