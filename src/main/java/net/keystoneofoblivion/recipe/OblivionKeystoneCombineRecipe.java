package net.keystoneofoblivion.recipe;

import net.keystoneofoblivion.Config;
import net.keystoneofoblivion.ModDataComponents;
import net.keystoneofoblivion.ModItems;
import net.keystoneofoblivion.ModRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OblivionKeystoneCombineRecipe extends CustomRecipe {
    public OblivionKeystoneCombineRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        List<ItemStack> nonKeystone = new ArrayList<>();
        ItemStack keystone = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.is(ModItems.OBLIVION_KEYSTONE.get())) {
                if (!keystone.isEmpty()) return false;
                keystone = stack;
            } else {
                nonKeystone.add(stack);
            }
        }

        if (keystone.isEmpty()) return false;

        List<Identifier> bound = keystone.getOrDefault(ModDataComponents.BOUND_ITEMS.get(), List.of());

        if (nonKeystone.size() == 1) {
            if (bound.size() >= Config.HARDCAP.get()) return false;
            Identifier otherId = BuiltInRegistries.ITEM.getKey(nonKeystone.get(0).getItem());
            return !bound.contains(otherId);
        }
        return nonKeystone.isEmpty();
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput input, HolderLookup.@NotNull Provider registries) {
        List<ItemStack> nonKeystone = new ArrayList<>();
        ItemStack keystone = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.is(ModItems.OBLIVION_KEYSTONE.get())) {
                if (!keystone.isEmpty()) return ItemStack.EMPTY;
                keystone = stack;
            } else {
                nonKeystone.add(stack);
            }
        }

        if (keystone.isEmpty()) return ItemStack.EMPTY;

        List<Identifier> bound = keystone.getOrDefault(ModDataComponents.BOUND_ITEMS.get(), List.of());

        if (nonKeystone.size() == 1) {
            if (bound.size() >= Config.HARDCAP.get()) return ItemStack.EMPTY;
            Identifier otherId = BuiltInRegistries.ITEM.getKey(nonKeystone.get(0).getItem());
            if (bound.contains(otherId)) return ItemStack.EMPTY;

            ItemStack result = keystone.copy();
            List<Identifier> newList = new ArrayList<>(bound);
            newList.add(otherId);
            result.set(ModDataComponents.BOUND_ITEMS.get(), List.copyOf(newList));
            return result;
        } else if (nonKeystone.isEmpty()) {
            ItemStack result = keystone.copy();
            result.remove(ModDataComponents.BOUND_ITEMS.get());
            return result;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(@NotNull CraftingInput input) {
        return NonNullList.withSize(input.size(), ItemStack.EMPTY);
    }

    @Override
    public @NotNull RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipeSerializers.OBLIVION_KEYSTONE_COMBINE.get();
    }
}
