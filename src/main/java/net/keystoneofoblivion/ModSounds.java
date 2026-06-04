package net.keystoneofoblivion;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, KeystoneOfOblivion.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> KEYSTONE_ACTIVATE = SOUNDS.register(
            "keystone_activate",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "keystone_activate")));

    public static final DeferredHolder<SoundEvent, SoundEvent> KEYSTONE_DEACTIVATE = SOUNDS.register(
            "keystone_deactivate",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "keystone_deactivate")));

    private ModSounds() {}
}
