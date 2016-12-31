package com.lucasi.llamaswithhats;

import com.lucasi.llamaswithhats.blocks.LWHBlocks;
import com.lucasi.llamaswithhats.client.LlamasWithHatsTab;
import com.lucasi.llamaswithhats.crafting.LWHCrafting;
import com.lucasi.llamaswithhats.entities.LWHEntities;
import com.lucasi.llamaswithhats.items.LWHItems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.modid, name = Main.modname, version = Main.modversion, acceptedMinecraftVersions = "[1.11]")
public class Main {

	    public static final String modid = ModInfo.MOD_ID;
	    public static final String modname = ModInfo.MOD_NAME;
	    public static final String modversion = ModInfo.MOD_VERSION;

	    public static final LlamasWithHatsTab creativeTab = new LlamasWithHatsTab();
	    
	    @SidedProxy(clientSide = ModInfo.CLIENT_SIDE_PROXY_CLASS, serverSide = ModInfo.SERVER_SIDE_PROXY_CLASS)
	    public static CommonProxy proxy;

	    @Mod.Instance(modid)
	    public static Main instance;

	    @Mod.EventHandler
	    public void preInit(FMLPreInitializationEvent e) {
	    	System.out.println(modname + " is making hats for your llamas!");

	    	// Items
	    	LWHItems.init();
	    	
	    	// Blocks
	    	LWHBlocks.init();
	    	
	    	// Entities
	    	LWHEntities.init();
	        LWHEntities.registerEntities(instance);
	        
	        proxy.registerEntityRenders();

	    }

	    @Mod.EventHandler
	    public void init(FMLInitializationEvent e) {
	        proxy.registerRenders();

	        // Make sure this happens after items and blocks are initialized
	        LWHCrafting.init();

	    }

	    @Mod.EventHandler
	    public void postInit(FMLPostInitializationEvent e) {

	    	//ModInfo.PrintEntityList();
	    	//ModInfo.PrintLWHEntityList();
	    	
	    }
}
