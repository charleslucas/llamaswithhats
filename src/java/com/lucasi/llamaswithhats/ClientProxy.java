package com.lucasi.llamaswithhats;

import com.lucasi.llamaswithhats.entities.LWHEntities;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Main.modid + ":" + id, "inventory"));
	}
	
    @Override
    public void registerRenders()
    {
        //ModBlocks.registerRenders();
        //ModItems.registerRenders();
    }

    @Override
    public void registerEntityRenders()
    {
        LWHEntities.registerEntityRenders();
    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
	
}
