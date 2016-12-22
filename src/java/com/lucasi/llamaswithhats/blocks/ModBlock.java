package com.lucasi.llamaswithhats.blocks;

import com.lucasi.llamaswithhats.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ModBlock extends Block {

	protected String name;
	
	// Main Constructor
    public ModBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        
        this.name = unlocalizedName;
      
        setUnlocalizedName(unlocalizedName);
        setRegistryName(unlocalizedName);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);  // Which creative item menu tab this shows up in
        setHardness(hardness);                         // How long it takes to mine this
        setResistance(resistance);                     // Resistance against explosions
    }

    public ModBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.ROCK, hardness, resistance);
    }

    public ModBlock(String unlocalizedName, Material material) {
        this(unlocalizedName, material, 2.0f, 10.0f);
    }
    
    public ModBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }

	public void registerItemModel(ItemBlock itemBlock) {
		Main.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	@Override
	public ModBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
