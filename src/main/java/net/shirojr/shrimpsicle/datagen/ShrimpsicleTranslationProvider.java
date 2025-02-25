package net.shirojr.shrimpsicle.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.init.ShrimpsicleBlocks;
import net.shirojr.shrimpsicle.init.ShrimpsicleItemGroups;
import net.shirojr.shrimpsicle.init.ShrimpsicleItems;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ShrimpsicleTranslationProvider extends FabricLanguageProvider {
    public ShrimpsicleTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder builder) {
        builder.add(ShrimpsicleItemGroups.SHRIMPSICLE_GROUP_KEY, "Shrimpsicle");
        ShrimpsicleBlocks.ALL_BLOCKS.forEach(block ->
                builder.add(block, cleanString(Registries.BLOCK.getId(block), false))
        );
        builder.add(ShrimpsicleItems.BANANA, cleanString(Registries.ITEM.getId(ShrimpsicleItems.BANANA), false));
        builder.add(ShrimpsicleItems.COCONUT, cleanString(Registries.ITEM.getId(ShrimpsicleItems.COCONUT), false));
        builder.add(ShrimpsicleItems.PINEAPPLE, cleanString(Registries.ITEM.getId(ShrimpsicleItems.PINEAPPLE), false));

        try {
            Path existingFilePath = dataOutput.getModContainer()
                    .findPath("assets/%s/lang/en_us.existing.json".formatted(Shrimpsicle.MOD_ID)).get();
            builder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }

    public static String cleanString(Identifier identifier, boolean reverse) {
        List<String> input = List.of(identifier.getPath().split("/"));
        List<String> words = Arrays.asList(input.getLast().split("_"));
        if (reverse) Collections.reverse(words);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            char capitalized = Character.toUpperCase(word.charAt(0));
            output.append(capitalized).append(word.substring(1));
            if (i < words.size() - 1) {
                output.append(" ");
            }
        }
        return output.toString();
    }
}
