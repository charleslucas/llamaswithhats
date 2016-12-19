package com.lucasi.llamaswithhats.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItem extends Item {
	
	protected String name;
	
	public ModItem(String unlocalizedName) {
		super();

		this.name = unlocalizedName;
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
	    setCreativeTab(CreativeTabs.MATERIALS);
	}

	@Override
	public ModItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
