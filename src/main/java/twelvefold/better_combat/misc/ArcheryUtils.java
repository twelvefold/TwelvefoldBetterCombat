package twelvefold.better_combat.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ArcheryUtils {
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
