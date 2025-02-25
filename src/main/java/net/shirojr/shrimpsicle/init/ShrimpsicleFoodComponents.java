package net.shirojr.shrimpsicle.init;

import net.minecraft.component.type.FoodComponent;

public interface ShrimpsicleFoodComponents {
    FoodComponent BANANA = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F).build();
    FoodComponent COCONUT = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F).build();
    FoodComponent PINEAPPLE = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F).build();


}
