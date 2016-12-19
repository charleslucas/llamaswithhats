package com.lucasi.llamaswithhats.items;

import com.lucasi.llamaswithhats.ModInfo;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

    public static ModItem tutorialItem;

    public static void createItems() {
    	// Original way (pre-1.8) as shown in tutorial
    	// GameRegistry.register(tutorialItem = new ModItem("tutorial_item"), "tutorial_item");
    	//tutorialItem = (ModItem) new ModItem("tutorial_item").setRegistryName(ModInfo.MOD_ID, "tutorial_item");
    	tutorialItem = new ModItem("tutorial_item");
    	
    	register(tutorialItem);
    }
    
    private static final void register(Item i) {
    	// New way (1.9 and later) as shown here:  http://stackoverflow.com/questions/37284008/how-to-register-an-item-or-block-in-minecraft-1-9
        //GameRegistry.register(i.setUnlocalizedName(i.getRegistryName().toString()));

    	// http://www.minecraftforge.net/forum/index.php?topic=38187.0
        GameRegistry.register(i.setRegistryName(ModInfo.MOD_ID, i.getUnlocalizedName()));
    }
}
