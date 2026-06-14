package net.keystoneofoblivion.network;

import net.keystoneofoblivion.KeystoneOfOblivion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = KeystoneOfOblivion.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModNetwork {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1.0").optional();
        registrar.playToServer(ToggleMagnetPacket.TYPE, ToggleMagnetPacket.STREAM_CODEC, ToggleMagnetPacket::handle);
        registrar.playToClient(MagnetSyncPacket.TYPE, MagnetSyncPacket.STREAM_CODEC, MagnetSyncPacket::handle);
    }

    private ModNetwork() {}
}
