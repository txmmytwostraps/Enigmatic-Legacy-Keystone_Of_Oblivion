package net.keystoneofoblivion.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class OblivionKeystoneCombineRecipeSerializer implements RecipeSerializer<OblivionKeystoneCombineRecipe> {
    public static final MapCodec<OblivionKeystoneCombineRecipe> CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(
                    CraftingBookCategory.CODEC
                            .fieldOf("category")
                            .orElse(CraftingBookCategory.MISC)
                            .forGetter(r -> r.category())
            ).apply(inst, OblivionKeystoneCombineRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, OblivionKeystoneCombineRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    CraftingBookCategory.STREAM_CODEC, r -> r.category(),
                    OblivionKeystoneCombineRecipe::new
            );

    @Override
    public MapCodec<OblivionKeystoneCombineRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, OblivionKeystoneCombineRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
