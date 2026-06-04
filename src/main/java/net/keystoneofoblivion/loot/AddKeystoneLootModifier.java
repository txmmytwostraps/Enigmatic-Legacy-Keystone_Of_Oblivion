package net.keystoneofoblivion.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.keystoneofoblivion.ModItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AddKeystoneLootModifier extends LootModifier {
    public static final MapCodec<AddKeystoneLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            codecStart(inst)
                    .and(Codec.intRange(0, 100)
                            .fieldOf("chance_percent")
                            .forGetter(m -> m.chancePercent))
                    .apply(inst, AddKeystoneLootModifier::new));

    private static final Set<Identifier> TARGET_TABLES = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "chests/nether_bridge"),
            Identifier.fromNamespaceAndPath("minecraft", "chests/bastion_treasure"),
            Identifier.fromNamespaceAndPath("minecraft", "chests/bastion_hoglin_stable"),
            Identifier.fromNamespaceAndPath("minecraft", "chests/bastion_bridge"),
            Identifier.fromNamespaceAndPath("minecraft", "chests/bastion_other")
    );

    private final int chancePercent;

    public AddKeystoneLootModifier(LootItemCondition[] conditions, int priority, int chancePercent) {
        super(conditions, priority);
        this.chancePercent = chancePercent;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Identifier tableId = context.getQueriedLootTableId();
        if (TARGET_TABLES.contains(tableId) && context.getRandom().nextInt(100) < chancePercent) {
            generatedLoot.add(new ItemStack(ModItems.OBLIVION_KEYSTONE.get()));
        }
        return generatedLoot;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
