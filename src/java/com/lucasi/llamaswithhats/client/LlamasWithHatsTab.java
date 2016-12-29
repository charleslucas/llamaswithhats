package com.lucasi.llamaswithhats.client;

import com.lucasi.llamaswithhats.Main;
import com.lucasi.llamaswithhats.items.LWHItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class LlamasWithHatsTab extends CreativeTabs {

	public LlamasWithHatsTab() {
		super(Main.modid);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(LWHItems.tutorialItem);
	}

}
