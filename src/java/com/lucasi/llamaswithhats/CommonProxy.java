package com.lucasi.llamaswithhats;

import com.lucasi.llamaswithhats.crafting.LWHCrafting;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }

	// Overridden in ClientProxy
	public void registerItemRenderer(Item item, int meta, String id) {
	}
	
	// Overridden in ClientProxy
    public void registerRenders() {

    }

	// Overridden in ClientProxy
    public void registerEntityRenders() {

    }

}
