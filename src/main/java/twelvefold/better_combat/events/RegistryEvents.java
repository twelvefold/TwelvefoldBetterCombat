//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.events;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import twelvefold.better_combat.TFBetterCombat;
import twelvefold.better_combat.enchantments.EnchantmentCleaving;
import twelvefold.better_combat.misc.MiscUtils;

@Mod.EventBusSubscriber(modid = TFBetterCombat.MODID)
public final class RegistryEvents
{
    private RegistryEvents() {
        MiscUtils.assertFalse();
    }
    
    @SubscribeEvent
    public static void onEnchantmentRegistration(final RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().register(EnchantmentCleaving.CLEAVING);
    }
}
