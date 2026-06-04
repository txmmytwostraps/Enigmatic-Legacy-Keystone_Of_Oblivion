package net.keystoneofoblivion.item;

import net.keystoneofoblivion.Config;
import net.keystoneofoblivion.ModDataComponents;
import net.keystoneofoblivion.ModItems;
import net.keystoneofoblivion.ModSounds;
import net.keystoneofoblivion.client.KeystoneClientHooks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class OblivionKeystoneItem extends Item {
    private static final Random RANDOM = new Random();

    public OblivionKeystoneItem(Properties props) {
        super(props);
    }

    private static List<ResourceLocation> boundItems(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.BOUND_ITEMS.get(), List.of());
    }

    private static int consumptionMode(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.CONSUMPTION_MODE.get(), 0);
    }

    private static boolean isActive(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.ACTIVE.get(), true);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            addLore(list, "tooltip.keystone_of_oblivion.voidStone1");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone2");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone3");
            addLore(list, "tooltip.keystone_of_oblivion.void");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone4");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone5");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone6");
            addLore(list, "tooltip.keystone_of_oblivion.void");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone7");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone8");
            addLore(list, "tooltip.keystone_of_oblivion.void");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone9");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone10");
            addLore(list, "tooltip.keystone_of_oblivion.void");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone11");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone12");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone13");
            addLore(list, "tooltip.keystone_of_oblivion.void");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone14");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone15");
            addLore(list, "tooltip.keystone_of_oblivion.voidStone16");
        } else if (Screen.hasControlDown()) {
            addLore(list, "tooltip.keystone_of_oblivion.voidStoneCtrlList");

            List<ResourceLocation> bound = boundItems(stack);
            int softcap = Config.SOFTCAP.get();

            if (bound.size() <= softcap) {
                for (ResourceLocation id : bound) {
                    appendBoundLine(list, id);
                }
            } else {
                for (int i = 0; i < softcap; i++) {
                    ResourceLocation random = bound.get(RANDOM.nextInt(bound.size()));
                    appendBoundLine(list, random);
                }
            }
        } else {
            addLore(list, "tooltip.keystone_of_oblivion.holdShift");
            addLore(list, "tooltip.keystone_of_oblivion.voidStoneHoldCtrl");
        }

        addLore(list, "tooltip.keystone_of_oblivion.void");

        MutableComponent mode;
        if (isActive(stack)) {
            mode = Component.translatable("tooltip.keystone_of_oblivion.voidStoneMode" + consumptionMode(stack));
        } else {
            mode = Component.translatable("tooltip.keystone_of_oblivion.voidStoneModeInactive");
        }
        list.add(Component.translatable("tooltip.keystone_of_oblivion.voidStoneModeDesc", mode.getString()));
    }

    private static void addLore(List<Component> list, String key) {
        list.add(Component.translatable(key));
    }

    private static Item resolveItem(ResourceLocation id) {
        return BuiltInRegistries.ITEM.get(id).map(Holder::value).orElse(net.minecraft.world.item.Items.AIR);
    }

    private static void appendBoundLine(List<Component> list, ResourceLocation id) {
        Item item = resolveItem(id);
        if (item != net.minecraft.world.item.Items.AIR) {
            ItemStack displayStack = new ItemStack(item);
            list.add(Component.literal(" - " + displayStack.getHoverName().getString()).withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, @NotNull ItemStack other, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (action == ClickAction.PRIMARY || !slot.mayPlace(stack) || !slot.mayPickup(player) || other.isEmpty()) {
            return false;
        }
        other.setCount(0);
        if (player.level().isClientSide) {
            player.level().playSound(player, player.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS,
                    0.25F, 1.2F + (float) Math.random() * 0.4F);
        }
        return true;
    }

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack stack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (action == ClickAction.PRIMARY || !slot.mayPlace(stack) || !slot.mayPickup(player) || !slot.hasItem()) {
            return false;
        }
        slot.set(ItemStack.EMPTY);
        if (player.level().isClientSide) {
            player.level().playSound(player, player.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS,
                    0.25F, 1.2F + (float) Math.random() * 0.4F);
        }
        return true;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int mode = consumptionMode(stack);

        if (player.isShiftKeyDown()) {
            boolean newActive = !isActive(stack);
            stack.set(ModDataComponents.ACTIVE.get(), newActive);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, newActive);
            world.playSound(null, player.blockPosition(),
                    newActive ? ModSounds.KEYSTONE_ACTIVATE.get() : ModSounds.KEYSTONE_DEACTIVATE.get(),
                    SoundSource.PLAYERS,
                    (float) (0.8F + (Math.random() * 0.2F)),
                    (float) (0.8F + (Math.random() * 0.2F)));
        } else {
            int next = (mode >= 0 && mode < 2) ? mode + 1 : 0;
            stack.set(ModDataComponents.CONSUMPTION_MODE.get(), next);
            world.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS,
                    1.0F, (float) (0.8F + (Math.random() * 0.2F)));
        }

        if (world.isClientSide) {
            KeystoneClientHooks.showModeOverlay(buildModeLine(stack));
        }

        player.swing(hand);
        return InteractionResult.SUCCESS;
    }

    private static Component buildModeLine(ItemStack stack) {
        MutableComponent mode = isActive(stack)
                ? Component.translatable("tooltip.keystone_of_oblivion.voidStoneMode" + consumptionMode(stack))
                : Component.translatable("tooltip.keystone_of_oblivion.voidStoneModeInactive");
        return Component.translatable("tooltip.keystone_of_oblivion.voidStoneModeDesc", mode.getString());
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof Player player) || entity.tickCount % 4 != 0) {
            return;
        }
        if (!isActive(stack)) {
            return;
        }
        List<ResourceLocation> bound = boundItems(stack);
        if (bound.isEmpty()) {
            return;
        }
        consumeStuff(player, bound, consumptionMode(stack));
    }

    public static void consumeStuff(Player player, List<ResourceLocation> list, int mode) {
        Inventory inv = player.getInventory();
        Map<Integer, ItemStack> stackMap = new HashMap<>();
        int filledStacks = 0;

        for (int slot = 0; slot < inv.items.size(); slot++) {
            ItemStack s = inv.items.get(slot);
            if (!s.isEmpty()) {
                filledStacks += 1;
                if (s.getItem() != ModItems.OBLIVION_KEYSTONE.get()) {
                    stackMap.put(slot, s);
                }
            }
        }

        if (stackMap.isEmpty()) {
            return;
        }

        if (mode == 0) {
            for (ResourceLocation rl : list) {
                Item bound = resolveItem(rl);
                for (int slot : stackMap.keySet()) {
                    if (stackMap.get(slot).getItem() == bound) {
                        inv.setItem(slot, ItemStack.EMPTY);
                    }
                }
            }
        } else if (mode == 1) {
            for (ResourceLocation rl : list) {
                Item bound = resolveItem(rl);
                Map<Integer, ItemStack> localStackMap = new HashMap<>(stackMap);
                Multimap<Integer, Integer> stackSizeMultimap = ArrayListMultimap.create();

                localStackMap.entrySet().removeIf(e -> e.getValue().getItem() != bound);

                for (int slot : localStackMap.keySet()) {
                    stackSizeMultimap.put(localStackMap.get(slot).getCount(), slot);
                }

                int keepCount = (inv.offhand.get(0).getItem() == bound) ? 0 : 1;

                while (localStackMap.size() > keepCount) {
                    int smallestStackSize = Collections.min(stackSizeMultimap.keySet());
                    Collection<Integer> smallestStacks = stackSizeMultimap.get(smallestStackSize);
                    int slotWithSmallestStack = Collections.max(smallestStacks);

                    inv.setItem(slotWithSmallestStack, ItemStack.EMPTY);
                    stackSizeMultimap.remove(smallestStackSize, slotWithSmallestStack);
                    localStackMap.remove(slotWithSmallestStack);
                }
            }
        } else if (mode == 2) {
            if (filledStacks >= inv.items.size()) {
                for (ResourceLocation rl : list) {
                    Item bound = resolveItem(rl);
                    Map<Integer, ItemStack> localStackMap = new HashMap<>(stackMap);
                    Multimap<Integer, Integer> stackSizeMultimap = ArrayListMultimap.create();

                    localStackMap.entrySet().removeIf(e -> e.getValue().getItem() != bound);

                    for (int slot : localStackMap.keySet()) {
                        stackSizeMultimap.put(localStackMap.get(slot).getCount(), slot);
                    }

                    if (!localStackMap.isEmpty()) {
                        int smallestStackSize = Collections.min(stackSizeMultimap.keySet());
                        Collection<Integer> smallestStacks = stackSizeMultimap.get(smallestStackSize);
                        int slotWithSmallestStack = Collections.max(smallestStacks);

                        inv.setItem(slotWithSmallestStack, ItemStack.EMPTY);
                        return;
                    }
                }
            }
        }
    }
}
