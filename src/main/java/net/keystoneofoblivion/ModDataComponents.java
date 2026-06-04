package net.keystoneofoblivion;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public final class ModDataComponents {
    public static final DeferredRegister.DataComponents COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, KeystoneOfOblivion.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<Identifier>>> BOUND_ITEMS =
            COMPONENTS.register("bound_items",
                    () -> DataComponentType.<List<Identifier>>builder()
                            .persistent(Codec.list(Identifier.CODEC))
                            .networkSynchronized(Identifier.STREAM_CODEC.apply(ByteBufCodecs.list()))
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CONSUMPTION_MODE =
            COMPONENTS.register("consumption_mode",
                    () -> DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.VAR_INT)
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> ACTIVE =
            COMPONENTS.register("active",
                    () -> DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)
                            .networkSynchronized(ByteBufCodecs.BOOL)
                            .build());

    private ModDataComponents() {}
}
