package com.lucasi.llamaswithhats.blocks;

import com.lucasi.llamaswithhats.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static ModBlock tutorialBlock;
    public static ModOreBlock tutorialOreBlock;

    public static void init() {
        //GameRegistry.registerBlock(tutorialBlock = new BasicBlock("block_tutorial"), "block_tutorial");
    	//GameRegistry.registerBlock(tutorial_ore = new ModBlockOre("tutorial_ore", Material.rock, ModItems.tutorial_item, 2, 4), "tutorial_ore");
    	
    	tutorialBlock = register(new ModBlock("block_tutorial", Material.ROCK));
    	tutorialOreBlock = register(new ModOreBlock("block_tutorial_ore", Material.ROCK, ModItems.tutorialItem, 2, 4));
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

		if (block instanceof ModOreBlock) {
			((ModOreBlock)block).registerItemModel(itemBlock);
		}

		return block;
	}
}
