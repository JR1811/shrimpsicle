package net.shirojr.shrimpsicle;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.shirojr.shrimpsicle.init.ShrimpsicleBlocks;
import net.shirojr.shrimpsicle.init.ShrimpsicleItemGroups;
import net.shirojr.shrimpsicle.init.ShrimpsicleItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shrimpsicle implements ModInitializer {
	public static final String MOD_ID = "shrimpsicle";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ShrimpsicleItems.initialize();
		ShrimpsicleBlocks.initialize();
		ShrimpsicleItemGroups.initialize();

		LOGGER.info("A Shrimp has invaded your files!");
	}

	public static Identifier getId(String name) {
		return Identifier.of(MOD_ID, name);
	}
}