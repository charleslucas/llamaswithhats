package com.lucasi.llamaswithhats.crafting;

import com.lucasi.llamaswithhats.blocks.LWHBlocks;
import com.lucasi.llamaswithhats.items.LWHItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class LWHCrafting {

    public static void init() {
	   GameRegistry.addRecipe(new ItemStack(LWHBlocks.tutorialBlock), new Object[] {"##", "##", '#', LWHItems.tutorialItem});
	}
    
}
