package net.shirojr.shrimpsicle.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.item.custom.DrinkDecoItem;
import net.shirojr.shrimpsicle.item.custom.FoodDecoItem;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public interface ShrimpsicleItems {
    List<ItemStack> ALL_ITEMS = new ArrayList<>();

    FoodDecoItem BANANA = registerItem("banana", new FoodDecoItem(ShrimpsicleBlocks.BANANA,
            new Item.Settings().food(ShrimpsicleFoodComponents.BANANA),
            null)
    );
    DrinkDecoItem COCONUT = registerItem("coconut", new DrinkDecoItem(ShrimpsicleBlocks.COCONUT,
            new Item.Settings().food(ShrimpsicleFoodComponents.COCONUT)));
    FoodDecoItem PINEAPPLE = registerItem("pineapple", new FoodDecoItem(ShrimpsicleBlocks.PINEAPPLE,
                    new Item.Settings().food(ShrimpsicleFoodComponents.PINEAPPLE),
                    (stack, world, entity) -> entity.damage(world.getDamageSources().cactus(), 2.0f)
            )
    );
    BlockItem BEACH_UMBRELLA = registerItem("beach_umbrella", new BlockItem(ShrimpsicleBlocks.BEACH_UMBRELLA,
            new Item.Settings()));


    private static <T extends Item> T registerItem(String name, T entry) {
        T registeredEntry = Registry.register(Registries.ITEM, Shrimpsicle.getId(name), entry);
        ALL_ITEMS.add(registeredEntry.getDefaultStack());
        return registeredEntry;
    }

    static void initialize() {
        // static initialisation
    }
}
