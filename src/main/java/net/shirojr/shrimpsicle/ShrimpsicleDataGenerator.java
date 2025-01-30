package net.shirojr.shrimpsicle;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.shirojr.shrimpsicle.datagen.ShrimpsicleModelProvider;

public class ShrimpsicleDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ShrimpsicleModelProvider::new);
	}
}
