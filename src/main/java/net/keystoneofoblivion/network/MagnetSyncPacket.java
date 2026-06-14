package net.keystoneofoblivion.network;

import net.keystoneofoblivion.KeystoneOfOblivion;
import net.keystoneofoblivion.ModAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Clientbound: updates the local player's magnet toggle so the inventory button shows the right state.
 */
public record MagnetSyncPacket(boolean disabled) implements CustomPacketPayload {
    public static final Type<MagnetSyncPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "magnet_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MagnetSyncPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.BOOL, MagnetSyncPacket::disabled, MagnetSyncPacket::new);

    public static void handle(MagnetSyncPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player != null) {
                player.setData(ModAttachments.MAGNET_DISABLED, packet.disabled());
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
