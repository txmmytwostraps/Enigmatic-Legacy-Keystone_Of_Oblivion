package net.keystoneofoblivion.item;

import net.keystoneofoblivion.Config;
import net.keystoneofoblivion.ModAttachments;
import net.keystoneofoblivion.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

/**
 * Curio ring that attracts nearby dropped items toward the wearer.
 */
public class MagnetRing extends Item implements ICurioItem {

    public MagnetRing(Properties props) {
        super(props);
    }

    /** Attraction radius, in blocks. Overridden by the upgraded ring. */
    protected double range() {
        return Config.MAGNET_RANGE.get();
    }

    @Override
    public void curioTick(SlotContext context, ItemStack stack) {
        LivingEntity entity = context.entity();
        if (entity == null || entity.isShiftKeyDown() || !(entity instanceof Player player)) {
            return;
        }
        if (player.getData(ModAttachments.MAGNET_DISABLED)) {
            return;
        }

        double x = entity.getX();
        double y = entity.getY() + 0.75;
        double z = entity.getZ();
        double r = range();
        List<ItemEntity> items = entity.level().getEntitiesOfClass(ItemEntity.class,
                new AABB(x - r, y - r, z - r, x + r, y + r, z + r));
        int pulled = 0;
        for (ItemEntity item : items) {
            if (!canPullItem(item)) {
                continue;
            }
            if (pulled > 200) {
                break;
            }
            if (MagnetHelper.canNotPickStack(player, item.getItem())) {
                continue;
            }
            item.setNoPickUpDelay();
            Vec3 delta = item.position().subtract(x, y, z);
            if (delta.length() > 1.0) {
                delta = delta.normalize();
            }
            item.setDeltaMovement(delta.scale(-1.2));
            item.hasImpulse = true;
            pulled++;
        }
    }

    @Override
    public boolean canEquip(SlotContext context, ItemStack stack) {
        return !MagnetHelper.hasCurio(context.entity(), this)
                && !MagnetHelper.hasCurio(context.entity(), ModItems.DISLOCATION_RING.get());
    }

    protected boolean canPullItem(ItemEntity item) {
        ItemStack stack = item.getItem();
        return item.isAlive() && !stack.isEmpty() && !item.getPersistentData().getBoolean("PreventRemoteMovement");
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("tooltip.keystone_of_oblivion.void"));
        list.add(Component.translatable("tooltip.keystone_of_oblivion.magnetRing1",
                Component.literal(String.format("%.0f", range())).withStyle(ChatFormatting.GOLD)));
        list.add(Component.translatable("tooltip.keystone_of_oblivion.magnetRing2"));
    }
}
