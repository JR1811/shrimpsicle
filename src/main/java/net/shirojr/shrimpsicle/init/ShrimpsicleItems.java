package net.shirojr.shrimpsicle.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.item.custom.DrinkDecoItem;
import net.shirojr.shrimpsicle.item.custom.FoodDecoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@SuppressWarnings("unused")
public interface ShrimpsicleItems {
    List<ItemStack> ALL_ITEMS = new ArrayList<>();

    FoodDecoItem BANANA = registerItem("banana", (settings, key) ->
            new FoodDecoItem(ShrimpsicleBlocks.BANANA, settings.food(ShrimpsicleFoodComponents.BANANA), null)
    );

    DrinkDecoItem COCONUT = registerItem("coconut", (settings, key) ->
            new DrinkDecoItem(ShrimpsicleBlocks.COCONUT, settings.food(ShrimpsicleFoodComponents.COCONUT))
    );

    FoodDecoItem PINEAPPLE = registerItem("pineapple", (settings, itemRegistryKey) ->
            new FoodDecoItem(ShrimpsicleBlocks.PINEAPPLE, settings.food(ShrimpsicleFoodComponents.PINEAPPLE),
                    (stack, world, entity) -> {
                        if (!(world instanceof ServerWorld serverWorld)) return;
                        entity.damage(serverWorld, world.getDamageSources().cactus(), 2.0f);
                    }
            )
    );

    BlockItem BEACH_UMBRELLA = registerItem("beach_umbrella", (settings, itemRegistryKey) ->
            new BlockItem(ShrimpsicleBlocks.BEACH_UMBRELLA, settings.food(ShrimpsicleFoodComponents.PINEAPPLE))
    );


    private static <T extends Item> T registerItem(String name, BiFunction<Item.Settings, RegistryKey<Item>, T> itemBuilder) {
        Identifier identifier = Shrimpsicle.getId(name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, identifier);
        Item.Settings settings = new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key);

        T registeredEntry = Registry.register(Registries.ITEM, identifier, itemBuilder.apply(settings, key));
        ALL_ITEMS.add(registeredEntry.getDefaultStack());
        return registeredEntry;
    }

    static void initialize() {
        // static initialisation
    }
}
