package net.keystoneofoblivion.item;

import net.keystoneofoblivion.Config;
import net.keystoneofoblivion.ModAttachments;
import net.keystoneofoblivion.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

/**
 * Upgraded Magnet Ring: instantly collects nearby dropped items straight into the inventory.
 */
public class DislocationRing extends MagnetRing {

    public DislocationRing(Properties props) {
        super(props);
    }

    @Override
    protected double range() {
        return Config.DISLOCATION_RANGE.get();
    }

    @Override
    public void curioTick(SlotContext context, ItemStack stack) {
        LivingEntity entity = context.entity();
        if (entity == null || entity.isShiftKeyDown() || !(entity instanceof Player player)) {
            return;
        }
        if (entity.level().isClientSide || player.getData(ModAttachments.MAGNET_DISABLED)) {
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
            if (pulled > 512) {
                break;
            }
            if (MagnetHelper.canNotPickStack(player, item.getItem())) {
                continue;
            }
            item.setNoPickUpDelay();
            item.playerTouch(player);
            pulled++;
        }
    }

    @Override
    public boolean canEquip(SlotContext context, ItemStack stack) {
        return !MagnetHelper.hasCurio(context.entity(), this)
                && !MagnetHelper.hasCurio(context.entity(), ModItems.MAGNET_RING.get());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("tooltip.keystone_of_oblivion.void"));
        list.add(Component.translatable("tooltip.keystone_of_oblivion.dislocationRing1"));
        list.add(Component.translatable("tooltip.keystone_of_oblivion.dislocationRing2",
                Component.literal(String.format("%.0f", range())).withStyle(ChatFormatting.GOLD)));
        list.add(Component.translatable("tooltip.keystone_of_oblivion.dislocationRing3"));
    }
}
