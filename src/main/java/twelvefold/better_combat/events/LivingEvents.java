//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package twelvefold.better_combat.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import twelvefold.better_combat.TFBetterCombat;
import twelvefold.better_combat.config.ModConfig;
import twelvefold.better_combat.misc.IEntityLiving;
import twelvefold.better_combat.misc.IShieldBreaker;
import twelvefold.better_combat.misc.MiscUtils;

@Mod.EventBusSubscriber(modid = TFBetterCombat.MODID)
public final class LivingEvents
{
    private LivingEvents() {
        MiscUtils.assertFalse();
    }
    
    @SubscribeEvent
    public static void onLivingDamage(final LivingHurtEvent event) {
        float amount = event.getAmount();
        final EntityLivingBase entity = event.getEntityLiving();
        final DamageSource source = event.getSource();
        if (entity.world.isRemote) {
            return;
        }
        if (ModConfig.modifyShield) {
            if (amount > 0.0f && MiscUtils.canBlockDamageSource(entity, source)) {
                if (entity instanceof IEntityLiving) {
                    ((IEntityLiving)entity).entity_damageShield(amount);
                }
                amount -= 5.0f;
                if (entity.getActiveItemStack().getSubCompound("BlockEntityTag") != null) {
                    amount -= 5.0f;
                }
                if (!source.isProjectile()) {
                    final Entity entity2 = source.getImmediateSource();
                    if (entity instanceof EntityPlayer && entity2 instanceof EntityLivingBase) {
                        final EntityPlayer player = (EntityPlayer)entity;
                        final EntityLivingBase enemy = (EntityLivingBase)entity2;
                        final ItemStack axe = enemy.getHeldItemMainhand();
                        if (axe.getItem() instanceof IShieldBreaker) {
                            final IShieldBreaker breaker = (IShieldBreaker)axe.getItem();
                            final int time = breaker.getShieldBreakTime(axe, entity.getActiveItemStack(), entity, enemy);
                            if (time > 0) {
                                player.getCooldownTracker().setCooldown(entity.getActiveItemStack().getItem(), time);
                                player.resetActiveHand();
                                player.world.setEntityState(player, (byte)30);
                            }
                        }
                    }
                }
                entity.world.setEntityState(entity, (byte)29);
            }
            if (amount <= 0.0f) {
                event.setCanceled(true);
            }
            else {
                event.setAmount(amount);
            }
        }
    }
    
    @SubscribeEvent
    public static void onArrowLoose(final ArrowLooseEvent event) {
        final ItemStack stack = event.getBow();
        final EntityPlayer entityplayer = event.getEntityPlayer();
        ItemStack itemstack = MiscUtils.findAmmo(entityplayer);
        final int i = event.getCharge();
        final World worldIn = entityplayer.world;
        if (worldIn.isRemote || !(stack.getItem() instanceof ItemBow)) {
            return;
        }
        if (ModConfig.modifyBow) {
            final boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }
                final float f = ItemBow.getArrowVelocity(i);
                if (f >= 0.1) {
                    final boolean flag2 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));
                    if (!worldIn.isRemote) {
                        final ItemArrow itemarrow = (ItemArrow)((itemstack.getItem() instanceof ItemArrow) ? itemstack.getItem() : Items.ARROW);
                        EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
                        entityarrow = ((ItemBow)stack.getItem()).customizeArrow(entityarrow);
                        float inaccuracy = 0.25f;
                        if (f == 1.0f) {
                            final int excessCharge = i - 20;
                            float f2 = excessCharge / 20.0f;
                            f2 = (f2 * f2 + f2 * 2.0f) / 3.0f;
                            inaccuracy += f2;
                        }
                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0f, f * 3.0f, inaccuracy);
                        final int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            entityarrow.setDamage(entityarrow.getDamage() + j * 0.5 + 0.5);
                        }
                        final int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            entityarrow.setKnockbackStrength(k);
                        }
                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            entityarrow.setFire(100);
                        }
                        stack.damageItem(1, entityplayer);
                        if (flag2 || (entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))) {
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }
                        worldIn.spawnEntity(entityarrow);
                    }
                    worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (TFBetterCombat.rand.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
                    if (!flag2 && !entityplayer.capabilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }
                    entityplayer.addStat(StatList.getObjectUseStats(stack.getItem()));
                }
            }
            event.setCharge(-1);
        }
    }
}
