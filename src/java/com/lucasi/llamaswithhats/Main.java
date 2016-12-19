package com.lucasi.llamaswithhats;

import com.lucasi.llamaswithhats.items.ModItems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.modid, name = Main.modname, version = Main.modversion, acceptedMinecraftVersions = "[1.11]")
public class Main {

	    public static final String modid = ModInfo.MOD_ID;
	    public static final String modname = ModInfo.MOD_NAME;
	    public static final String modversion = ModInfo.MOD_VERSION;

	    @SidedProxy(clientSide = ModInfo.CLIENT_SIDE_PROXY_CLASS, serverSide = ModInfo.SERVER_SIDE_PROXY_CLASS)
	    public static CommonProxy proxy;

	    @Instance
	    public static Main instance = new Main();

	    @EventHandler
	    public void preInit(FMLPreInitializationEvent e) {
	    	System.out.println(modname + " is making hats for your llamas!");
	    	ModItems.init();  // This should be here, not in CommonProxy like shown in tutorial
	    }

	    @EventHandler
	    public void init(FMLInitializationEvent e) {

	    }

	    @EventHandler
	    public void postInit(FMLPostInitializationEvent e) {

	    }
}
