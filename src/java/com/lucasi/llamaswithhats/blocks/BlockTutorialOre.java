package com.lucasi.llamaswithhats.blocks;

import java.util.Random;

import com.lucasi.llamaswithhats.items.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockTutorialOre extends BlockBase {

	protected String name;
	
	//private Item drop;
	//private int meta;
	//private int least_quantity;
	//private int most_quantity;
	
	// Main constructor
	public BlockTutorialOre(String name) {
	   super(name, Material.ROCK);
       
	   setHardness(5f);                            // How long it takes to mine this
	   setResistance(5f);                          // Resistance against explosions
       //setHarvestLevel("pickaxe", 1);
    }
	
	// ------------------------------------------------------
	
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
    	System.out.println(name + " getItemDropped()");
	    return ModItems.tutorialItem;
	}

	//@Override
	//public int damageDropped(IBlockState blockstate) {
    //	System.out.println(name + " damageDropped()");
	//    return this.meta;
	//}

	//@Override
	//public int quantityDropped(IBlockState blockstate, int fortune, Random random) {
    //	System.out.println(name + " quantityDropped()");
	//    if (this.least_quantity >= this.most_quantity)
	//        return this.least_quantity;
	//    return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	//}
	
}
