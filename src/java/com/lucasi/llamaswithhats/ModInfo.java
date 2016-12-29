package com.lucasi.llamaswithhats;

import java.util.Iterator;

import org.apache.logging.log4j.Level;

import com.lucasi.llamaswithhats.entities.LWHEntities;

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

	//public static void PrintEntityList()
	//{
	//	Iterator<?> mcEntityNameItr = EntityList.getEntityNameList().iterator();
	//	FMLLog.log(ModInfo.MOD_NAME, Level.INFO, "-Printing Registered MC Entity Classes-");
	//	System.out.println("-Printing Registered MC Entity Classes-");
	//	while(mcEntityNameItr.hasNext())
	//	{
	//		String key = (String)mcEntityNameItr.next();
	//		FMLLog.log(ModInfo.MOD_NAME, Level.INFO, String.format("     %s", key));
	//		System.out.println(String.format("     %s", key));
	//	}
	//}

	//public static void PrintLWHEntityList()
	//{
	//	Iterator lwhEntityNameItr = LWHEntities.lwhEntities.values().iterator();
	//	FMLLog.log(ModInfo.MOD_NAME, Level.INFO, "-Printing Registered LWH Entity Classes-");
	//	System.out.println("-Printing Registered LWH Entity Classes-");
	//	while(lwhEntityNameItr.hasNext())
	//	{
	//		String key = (String)lwhEntityNameItr.next();
	//		FMLLog.log(ModInfo.MOD_NAME, Level.INFO, String.format("     %s", key));
	//		System.out.println(String.format("     %s", key));
	//	}
	//}

}
