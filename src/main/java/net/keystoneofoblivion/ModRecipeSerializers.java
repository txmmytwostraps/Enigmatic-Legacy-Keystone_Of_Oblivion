package net.keystoneofoblivion;

import net.keystoneofoblivion.recipe.OblivionKeystoneCombineRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, KeystoneOfOblivion.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OblivionKeystoneCombineRecipe>> OBLIVION_KEYSTONE_COMBINE =
            RECIPE_SERIALIZERS.register(
                    "oblivion_keystone_combine",
                    () -> new RecipeSerializer<>(OblivionKeystoneCombineRecipe.CODEC, OblivionKeystoneCombineRecipe.STREAM_CODEC));

    private ModRecipeSerializers() {}
}
