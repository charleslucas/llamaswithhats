package com.lucasi.llamaswithhats.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static ModBlock tutorialBlock;

    public static void init() {
        //GameRegistry.registerBlock(tutorialBlock = new BasicBlock("tutorial_block"), "tutorial_block");
    	
    	tutorialBlock = register(new ModBlock("tutorialBlock", Material.ROCK));
    }

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if (block instanceof ModBlock) {
			((ModBlock)block).registerItemModel(itemBlock);
		}

		return block;
	}

}
