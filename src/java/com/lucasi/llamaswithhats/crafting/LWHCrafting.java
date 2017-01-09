package com.lucasi.llamaswithhats.crafting;

import com.lucasi.llamaswithhats.blocks.LWHBlocks;
import com.lucasi.llamaswithhats.items.LWHItems;

import javafx.scene.paint.Color;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class LWHCrafting {

    public static void init() {
	   GameRegistry.addRecipe(new ItemStack(LWHBlocks.tutorialBlock), new Object[] {"##", "##", '#', LWHItems.tutorialItem});

	   // Wool Armor Recipes  (Not sure why I changed the first one to use an itemstack, must have been following a tutorial)
	   GameRegistry.addRecipe(new ItemStack(LWHItems.woolBoots), new Object[] {"   ", "# #", "# #", '#', new ItemStack(Blocks.WOOL, 1, 14)});
	   GameRegistry.addRecipe(new ItemStack(LWHItems.woolLeggings), new Object[] {"###", "# #", "# #", '#', Blocks.WOOL});
	   GameRegistry.addRecipe(new ItemStack(LWHItems.woolChestplate), new Object[] {"# #", "###", "###", '#', Blocks.WOOL});
	   GameRegistry.addRecipe(new ItemStack(LWHItems.woolHelmet), new Object[] {"###", "# #", '#', Blocks.WOOL});
	}
    
}
