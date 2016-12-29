package com.lucasi.llamaswithhats.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lucasi.llamaswithhats.ModInfo;
import com.lucasi.llamaswithhats.entities.render.LlamaWithHatRenderFactory;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class LWHEntities {

	private static int ENTITY_ID = 0;
	
	public static final String LLAMA_WITH_HAT = "llama_with_hat";
	
	public static Map<String,Class<? extends Entity>> lwhEntities = Collections.emptyMap();
	
	public static void init() {
		// Every entity in Llamas with Hats has an ID (local to this mod)
		lwhEntities = new HashMap<String,Class<? extends Entity>>();
		lwhEntities.put(LLAMA_WITH_HAT, EntityLlamaWithHat.class);
	}
	
	 public static void registerEntities(Object modObject)
	    {
	        for (String key : lwhEntities.keySet())
	        {
	            Class<? extends Entity> entityClass = lwhEntities.get(key);
	            if(entityClass != null)
	            {
	    	    	System.out.println("Llamas with Hats - Registering entity " + key);
	                EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.RESOURCE_PREFIX + key), entityClass, ModInfo.RESOURCE_PREFIX + key, ENTITY_ID++, modObject, 80, 3, false);
	                // Registers a spawn egg for the specified entity class.
	                // Once registered mob eggs can be created by using minecraft:spawn_egg with NBT entry
	                //   'entity_name' with value of the name this class is registered in the classToStringMapping with.
	                // give @p spawn_egg 1 0 {EntityTag:{id:llamaswithhats:llama_with_hat}}
	                EntityRegistry.registerEgg(new ResourceLocation(ModInfo.RESOURCE_PREFIX + key), 1, 2);
	            }
	        }
	    }

	    public static void registerEntityRenders()
	    {
	        RenderingRegistry.registerEntityRenderingHandler(EntityLlamaWithHat.class, new LlamaWithHatRenderFactory());
	    }
}
