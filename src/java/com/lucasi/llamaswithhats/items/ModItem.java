package com.lucasi.llamaswithhats.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItem extends Item {
	
	public ModItem(String unlocalizedName) {
		super();

		this.setUnlocalizedName(unlocalizedName);
	    this.setCreativeTab(CreativeTabs.MATERIALS);
	}

}
