package net.shirojr.shrimpsicle.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.shirojr.shrimpsicle.Shrimpsicle;

@SuppressWarnings("SameParameterValue")
public interface ShrimpsicleItemGroups {
    RegistryKey<ItemGroup> SHRIMPSICLE_GROUP_KEY = registerItemGroup("shrimpsicle", FabricItemGroup.builder()
            .icon(ShrimpsicleItems.COCONUT::getDefaultStack)
            .displayName(Text.translatable("shrimpsicle.itemGroup.shrimpsicle"))
            .build());


    private static RegistryKey<ItemGroup> registerItemGroup(String name, ItemGroup group) {
        Registry.register(Registries.ITEM_GROUP, Shrimpsicle.getId(name), group);
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), Shrimpsicle.getId(name));
    }

    static void initialize() {
        // static initialisation
        ItemGroupEvents.modifyEntriesEvent(SHRIMPSICLE_GROUP_KEY).register(entries -> entries.addAll(ShrimpsicleItems.ALL_ITEMS));
    }
}
