package net.keystoneofoblivion.client;

import net.keystoneofoblivion.KeystoneOfOblivion;
import net.keystoneofoblivion.ModAttachments;
import net.keystoneofoblivion.ModItems;
import net.keystoneofoblivion.item.MagnetHelper;
import net.keystoneofoblivion.network.ToggleMagnetPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import top.theillusivec4.curios.api.client.ICuriosScreen;

/**
 * Inventory-screen button that toggles magnet ring effects. Visible only while a magnet/dislocation
 * ring is equipped in a Curios slot.
 */
@OnlyIn(Dist.CLIENT)
public class MagnetRingInventoryButton extends ImageButton {
    public static final WidgetSprites SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "button/magnet_button"),
            ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "button/magnet_button_off"),
            ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "button/magnet_button_highlighted"),
            ResourceLocation.fromNamespaceAndPath(KeystoneOfOblivion.MODID, "button/magnet_button_off_highlighted")
    );

    private final AbstractContainerScreen<?> parentGui;
    private boolean isRecipeBookVisible = false;

    public MagnetRingInventoryButton(AbstractContainerScreen<?> container, int x, int y, int width, int height, OnPress onPress) {
        super(x, y, width, height, SPRITES, onPress);
        this.parentGui = container;
    }

    public static MagnetRingInventoryButton getInstance(AbstractContainerScreen<?> gui, boolean isCreative) {
        MagnetRingInventoryButton button = new MagnetRingInventoryButton(gui, 0, 0, 20, 18, input -> {
            if (Minecraft.getInstance().player != null) {
                PacketDistributor.sendToServer(new ToggleMagnetPacket());
            }
        });
        Tuple<Integer, Integer> offsets = button.getOffsets(isCreative);
        button.setX(gui.getGuiLeft() + offsets.getA());
        button.setY(gui.getGuiTop() + offsets.getB());
        return button;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.active = true;
        if (this.parentGui instanceof InventoryScreen || this.parentGui instanceof ICuriosScreen) {
            boolean lastVisible = this.isRecipeBookVisible;
            this.isRecipeBookVisible = ((RecipeUpdateListener) this.parentGui).getRecipeBookComponent().isVisible();
            if (lastVisible != this.isRecipeBookVisible) {
                Tuple<Integer, Integer> offsets = this.getOffsets(false);
                this.setPosition(this.parentGui.getGuiLeft() + offsets.getA(), this.parentGui.getGuiTop() + offsets.getB());
            }
        } else if (this.parentGui instanceof CreativeModeInventoryScreen creative) {
            if (!creative.isInventoryOpen()) {
                this.active = false;
                return;
            }
        }

        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null
                && (MagnetHelper.hasCurio(player, ModItems.MAGNET_RING.get())
                || MagnetHelper.hasCurio(player, ModItems.DISLOCATION_RING.get()))) {
            boolean enabled = !player.getData(ModAttachments.MAGNET_DISABLED);
            ResourceLocation sprite = SPRITES.get(enabled, isHoveredOrFocused());
            graphics.blitSprite(sprite, this.getX(), this.getY(), this.width, this.height);
        } else {
            this.active = false;
        }
    }

    private Tuple<Integer, Integer> getOffsets(boolean creative) {
        int x = creative ? 147 : 127;
        int y = creative ? 5 : 61;
        return new Tuple<>(x, y);
    }
}
