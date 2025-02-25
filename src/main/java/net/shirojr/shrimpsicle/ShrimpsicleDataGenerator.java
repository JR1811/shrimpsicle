package net.shirojr.shrimpsicle;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.shirojr.shrimpsicle.datagen.ShrimpsicleBlockLootTableProvider;
import net.shirojr.shrimpsicle.datagen.ShrimpsicleModelProvider;
import net.shirojr.shrimpsicle.datagen.ShrimpsicleTranslationProvider;

public class ShrimpsicleDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ShrimpsicleModelProvider::new);
		pack.addProvider(ShrimpsicleBlockLootTableProvider::new);
		pack.addProvider(ShrimpsicleTranslationProvider::new);
	}
}
