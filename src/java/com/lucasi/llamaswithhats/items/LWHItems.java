package com.lucasi.llamaswithhats.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class LWHItems {
	protected static int[] woolArmorDamageReductionArray = {1,3,2,1};  // Same as leather
	protected static int   woolArmorDurability = 4;                      // Slightly less than leather
	protected static int   woolArmorEnchantability = 20;                 // More than leather
    protected static int   woolArmorToughness = 0;                       // Same as leather (and everything except diamond)

    // toughness is new - means increased armor defense point reductions from strong attacks
    // Diamond armor has toughness 2, all other armor has toughness 0. A full set of diamond armor, therefore gives a toughness attribute of 8.
    //public static ArmorMaterial addArmorMaterial(String name, String textureName, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness)
    public static ArmorMaterial ARMOR_WOOL = EnumHelper.addArmorMaterial("WOOL", "llamaswithhats:wool_armor", woolArmorDurability, woolArmorDamageReductionArray, woolArmorEnchantability, null, woolArmorToughness);
    //ARMOR_WOOL.repairMaterial = new ItemStack(Blocks.WOOL);
    
    // Mod Items
    public static ItemBase tutorialItem;
    
    // Mod Armor
    public static ItemArmorBase woolHelmet;
    public static ItemArmorBase woolChestplate;
    public static ItemArmorBase woolLeggings;
    public static ItemArmorBase woolBoots;
    
    public static void init() {
    	// Original way (pre-1.8) as shown in tutorial
    	// GameRegistry.register(tutorialItem = new ModItem("item_tutorial"), "item_tutorial");
    	//tutorialItem = (ModItem) new ModItem("item_tutorial").setRegistryName(ModInfo.MOD_ID, "item_tutorial");
    	tutorialItem   = register(new ItemBase("item_tutorial"));
    	
    	//                                      textures/items JSON Name    Material   RenderIndex   Eq Slot
    	woolHelmet     = register(new ItemArmorBase("wool_helmet",       ARMOR_WOOL,      1,     EntityEquipmentSlot.HEAD));
    	woolChestplate = register(new ItemArmorBase("wool_chestplate",   ARMOR_WOOL,      1,     EntityEquipmentSlot.CHEST));
    	woolLeggings   = register(new ItemArmorBase("wool_leggings",     ARMOR_WOOL,      2,     EntityEquipmentSlot.LEGS));
    	woolBoots      = register(new ItemArmorBase("wool_boots",        ARMOR_WOOL,      1,     EntityEquipmentSlot.FEET));
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

		if (item instanceof ItemArmorBase) {
			((ItemArmorBase)item).registerItemModel();
		}

		return item;
	}

}
