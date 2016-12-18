package com.lucasi.llamaswithhats;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {

	    public static final String MODID = "llamaswithhats";
	    public static final String MODNAME = "Llamas with Hats";
	    public static final String VERSION = "1.0.0";

	    @SidedProxy(clientSide="com.lucasi.llamaswithhats.ClientProxy", serverSide="com.lucasi.llamaswithhats.ServerProxy")
	    public static CommonProxy proxy;

	    @Instance
	    public static Main instance = new Main();


	    @EventHandler
	    public void preInit(FMLPreInitializationEvent e) {

	    }

	    @EventHandler
	    public void init(FMLInitializationEvent e) {

	    }

	    @EventHandler
	    public void postInit(FMLPostInitializationEvent e) {

	    }
}
