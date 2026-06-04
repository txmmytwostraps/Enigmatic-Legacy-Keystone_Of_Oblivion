package net.keystoneofoblivion;

import net.keystoneofoblivion.recipe.OblivionKeystoneCombineRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, KeystoneOfOblivion.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<OblivionKeystoneCombineRecipe>> OBLIVION_KEYSTONE_COMBINE =
            RECIPE_SERIALIZERS.register(
                    "oblivion_keystone_combine",
                    () -> new SimpleCraftingRecipeSerializer<>(OblivionKeystoneCombineRecipe::new));

    private ModRecipeSerializers() {}
}
