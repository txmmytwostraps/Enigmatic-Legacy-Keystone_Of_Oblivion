package net.keystoneofoblivion;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, KeystoneOfOblivion.MODID);

    /**
     * Per-player toggle for magnet ring effects. Transient (not serialized) to match the original,
     * which used TransientPlayerData — magnet effects default to enabled every session.
     */
    public static final Supplier<AttachmentType<Boolean>> MAGNET_DISABLED =
            ATTACHMENT_TYPES.register("magnet_disabled",
                    () -> AttachmentType.builder(() -> false).build());

    private ModAttachments() {}
}
