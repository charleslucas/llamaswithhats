package com.lucasi.llamaswithhats.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LWHBlocks {

    public static BlockBase tutorialBlock;
    public static BlockTutorialOre tutorialOreBlock;

    public static void init() {
        //GameRegistry.registerBlock(tutorialBlock = new BasicBlock("block_tutorial"), "block_tutorial");
    	//GameRegistry.registerBlock(tutorial_ore = new ModBlockOre("tutorial_ore", Material.rock, ModItems.tutorial_item, 2, 4), "tutorial_ore");
    	
    	tutorialBlock = register(new BlockBase("block_tutorial", Material.ROCK));
    	tutorialOreBlock = register(new BlockTutorialOre("block_tutorial_ore"));
    }

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if (block instanceof BlockBase) {
			((BlockBase)block).registerItemModel(itemBlock);
		}

		return block;
	}
}
