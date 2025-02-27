package net.shirojr.shrimpsicle.block.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.shape.VoxelShape;
import net.shirojr.shrimpsicle.block.DecoBlock;

import java.util.List;

public class AnanasBlock extends DecoBlock {
    public AnanasBlock(Settings settings, VoxelShape shape) {
        super(settings, shape);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(Text.translatable("tooltip.shrimpsicle.ananas_1"));
        tooltip.add(Text.translatable("tooltip.shrimpsicle.ananas_2"));
        tooltip.add(Text.translatable("tooltip.shrimpsicle.ananas_3"));
    }
}
