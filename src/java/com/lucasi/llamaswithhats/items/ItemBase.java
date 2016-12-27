package com.lucasi.llamaswithhats.items;

import com.lucasi.llamaswithhats.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	protected String name;

	// Main Constructor
	public ItemBase(String unlocalizedName) {
		//super();

		this.name = unlocalizedName;
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
	    setCreativeTab(Main.creativeTab);
	    //setTextureName(ModInfo.MOD_ID + ":tutorialItem");  // Deprecated in 1.8 and later - need .json file
	}

	// Register the item textures for this model
	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	// Set which creative-mode tab this item should show up in
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
