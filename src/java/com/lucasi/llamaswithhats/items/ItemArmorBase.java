package com.lucasi.llamaswithhats.items;

import com.lucasi.llamaswithhats.Main;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor {

	 public ItemArmorBase(String unlocalizedName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
	        super(material, renderIndex, equipmentSlotIn);

		    setCreativeTab(Main.creativeTab);
			setUnlocalizedName(unlocalizedName);
			setRegistryName(unlocalizedName);
	    }

//	 @Override
//	 public ArmorMaterial setRepairItem(ItemStack stack)
//     {
//         return ModItems.ARMOR_WOOL;
//     }

//	 @Override
//	    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
//	    {
//	        if (this.getArmorMaterial() == ModItems.ARMOR_WOOL)
//	        {
//	            if (stack.getItem() == ModItems.woolHelmet)
//	            {
//	                return ModInfo.RESOURCE_PREFIX + "textures/models/armor/wool_armor_layer_1.png";
//	            }
//	            else if (stack.getItem() == ModItems.woolChestplate || stack.getItem() == ModItems.woolBoots)
//	            {
//	                return ModInfo.RESOURCE_PREFIX + "textures/models/armor/wool_armor_layer_1.png";
//	            }
//	            else if (stack.getItem() == ModItems.woolLeggings)
//	            {
//	                return ModInfo.RESOURCE_PREFIX + "textures/models/armor/wool_armor_layer_2.png";
//	            }
//	        }
//
//	        return null;
//	    }
	 
}
