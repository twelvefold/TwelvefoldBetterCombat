//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import twelvefold.better_combat.TFBetterCombat;
import twelvefold.better_combat.api.IShieldBreaker;

public class EnchantmentCleaving extends Enchantment
{
    public static Enchantment CLEAVING;
    
    private EnchantmentCleaving() {
        super(Enchantment.Rarity.COMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
        this.setName("cleaving");
        this.setRegistryName(TFBetterCombat.MODID, "cleaving");
    }
    
    public int getMinLevel() {
        return 1;
    }
    
    public int getMaxLevel() {
        return 3;
    }
    
    protected boolean canApplyTogether(final Enchantment ench) {
        return ench != Enchantments.SHARPNESS && ench != Enchantments.SMITE && ench != Enchantments.BANE_OF_ARTHROPODS;
    }
    
    public boolean canApply(final ItemStack stack) {
        return stack.getItem() instanceof IShieldBreaker;
    }
    
    public boolean canApplyAtEnchantingTable(final ItemStack stack) {
        return this.canApply(stack);
    }
    
    public float calcDamageByCreature(final int level, final EnumCreatureAttribute creatureType) {
        return 1.0f + level;
    }
    
    static {
        EnchantmentCleaving.CLEAVING = new EnchantmentCleaving();
    }
}
