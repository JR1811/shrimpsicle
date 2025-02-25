package net.shirojr.shrimpsicle.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;

public class FoodDecoItem extends AliasedBlockItem {
    @Nullable
    private final TriConsumer<ItemStack, World, LivingEntity> finishUsingAction;

    public FoodDecoItem(Block block, Settings settings, @Nullable TriConsumer<ItemStack, World, LivingEntity> finishUsingAction) {
        super(block, settings);
        this.finishUsingAction = finishUsingAction;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient() && this.finishUsingAction != null) {
            this.finishUsingAction.accept(stack, world, user);
        }
        return super.finishUsing(stack, world, user);
    }
}
