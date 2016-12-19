package com.lucasi.llamaswithhats;

import java.util.Iterator;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.FMLLog;

public class ModInfo {
	 public static final String MOD_ID = "llamaswithhats";
	 public static final String MOD_NAME = "Llamas with Hats";
	 public static final String MOD_VERSION = "1.11-0.2.0";
	 public static final String RESOURCE_PREFIX = MOD_ID + ":";

	 public static final String GUI_FACTORY_CLASS = "com.lucasi.llamaswithhats.gui.GuiFactoryMT";
	 public static final String CLIENT_SIDE_PROXY_CLASS = "com.lucasi.llamaswithhats.ClientProxy";
	 public static final String SERVER_SIDE_PROXY_CLASS = "com.lucasi.llamaswithhats.ServerProxy";

	 public static void PrintEntityList()
	 {
	     Iterator entityNameItr = EntityList.getEntityNameList().iterator();
	     FMLLog.log(ModInfo.MOD_NAME, Level.INFO, "-Printing Registered Entity Classes-");
	     while(entityNameItr.hasNext())
	     {
	         String key = (String)entityNameItr.next();
	         FMLLog.log(ModInfo.MOD_NAME, Level.INFO, String.format("     %s", key));
	     }
	 }
	   
}
