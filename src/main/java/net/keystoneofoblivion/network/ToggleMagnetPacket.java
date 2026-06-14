package net.keystoneofoblivion.network;

import net.keystoneofoblivion.KeystoneOfOblivion;
import net.keystoneofoblivion.ModAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Serverbound: the inventory magnet button was clicked. Flips the player's magnet toggle and
 * syncs the new value back to the client.
 */
public record ToggleMagnetPacket() implements CustomPacketPayload {
    public static final Type<ToggleMagnetPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "toggle_magnet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleMagnetPacket> STREAM_CODEC =
            StreamCodec.unit(new ToggleMagnetPacket());

    public static void handle(ToggleMagnetPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                boolean disabled = !player.getData(ModAttachments.MAGNET_DISABLED);
                player.setData(ModAttachments.MAGNET_DISABLED, disabled);
                PacketDistributor.sendToPlayer(player, new MagnetSyncPacket(disabled));
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
