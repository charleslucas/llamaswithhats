package com.lucasi.llamaswithhats.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

    public static ItemBase tutorialItem;

    public static void init() {
    	// Original way (pre-1.8) as shown in tutorial
    	// GameRegistry.register(tutorialItem = new ModItem("item_tutorial"), "item_tutorial");
    	//tutorialItem = (ModItem) new ModItem("item_tutorial").setRegistryName(ModInfo.MOD_ID, "item_tutorial");
    	tutorialItem = register(new ItemBase("item_tutorial"));
    }
    
//    private static final void register(Item i) {
//    	// New way (1.9 and later) as shown here:  http://stackoverflow.com/questions/37284008/how-to-register-an-item-or-block-in-minecraft-1-9
//        //GameRegistry.register(i.setUnlocalizedName(i.getRegistryName().toString()));
//
//    	// http://www.minecraftforge.net/forum/index.php?topic=38187.0
//        GameRegistry.register(i.setRegistryName(ModInfo.MOD_ID, i.getUnlocalizedName()));
//    }
    
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
//		if (item instanceof ItemModelProvider) {
//			((ItemModelProvider)item).registerItemModel(item);
//		}
		
		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}

		return item;
	}

}
