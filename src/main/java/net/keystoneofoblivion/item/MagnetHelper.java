package net.keystoneofoblivion.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Small helpers ported minimally from Enigmatic Legacy's handler, supporting the magnet rings.
 */
public final class MagnetHelper {

    /** True if the entity currently has the given item equipped in any Curios slot. */
    public static boolean hasCurio(LivingEntity entity, ItemLike itemLike) {
        if (entity == null) {
            return false;
        }
        Item item = itemLike.asItem();
        return CuriosApi.getCuriosInventory(entity)
                .map(handler -> handler.findFirstCurio(item).isPresent())
                .orElse(false);
    }

    /** True if the stack could NOT be picked up by the player (no free slot and no mergeable stack). */
    public static boolean canNotPickStack(Player player, ItemStack stack) {
        if (player.getInventory().getFreeSlot() >= 0) {
            return false;
        }
        List<ItemStack> all = new ArrayList<>();
        all.addAll(player.getInventory().items);
        all.addAll(player.getInventory().offhand);
        for (ItemStack invStack : all) {
            if (canMergeStacks(invStack, stack, player.getInventory().getMaxStackSize())) {
                return false;
            }
        }
        return true;
    }

    private static boolean canMergeStacks(ItemStack existing, ItemStack incoming, int maxStackSize) {
        return !existing.isEmpty()
                && ItemStack.isSameItemSameComponents(existing, incoming)
                && existing.isStackable()
                && existing.getCount() < Math.min(existing.getMaxStackSize(), maxStackSize);
    }

    private MagnetHelper() {}
}
