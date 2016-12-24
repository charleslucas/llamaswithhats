package com.lucasi.llamaswithhats.blocks;

import java.util.Random;

import com.lucasi.llamaswithhats.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ModOreBlock extends Block {

	protected String name;
	
	private Item drop;
	private int meta;
	private int least_quantity;
	private int most_quantity;
	
	// Main constructor
	public ModOreBlock(String unlocalizedName, Material mat, Item drop, int meta, int least_quantity, int most_quantity) {
	   super(mat);
       
       this.name = unlocalizedName;

       this.drop = drop;
       this.meta = meta;
       this.least_quantity = least_quantity;
       this.most_quantity = most_quantity;

	   setUnlocalizedName(unlocalizedName);
       setRegistryName(unlocalizedName);

	   setCreativeTab(CreativeTabs.BUILDING_BLOCKS);  // Which creative item menu tab this shows up in
	   setHardness(10.0f);                            // How long it takes to mine this
	   setResistance(15.0f);                          // Resistance against explosions
       setHarvestLevel("pickaxe", 1);
    }
	
	// Constructor
	public ModOreBlock(String unlocalizedName, Material mat, Item drop, int least_quantity, int most_quantity) {
	    this(unlocalizedName, mat, drop, 0, least_quantity, most_quantity);
	}

	// Constructor
	public ModOreBlock(String unlocalizedName, Material mat, Item drop) {
	    this(unlocalizedName, mat, drop, 1, 1);
	}
	
	public void registerItemModel(ItemBlock itemBlock) {
		Main.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	@Override
	public ModOreBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	// ------------------------------------------------------
	
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
    	System.out.println(name + " getItemDropped()");
	    return this.drop;
	}

	@Override
	public int damageDropped(IBlockState blockstate) {
    	System.out.println(name + " damageDropped()");
	    return this.meta;
	}

	@Override
	public int quantityDropped(IBlockState blockstate, int fortune, Random random) {
    	System.out.println(name + " quantityDropped()");
	    if (this.least_quantity >= this.most_quantity)
	        return this.least_quantity;
	    return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}
	
}
