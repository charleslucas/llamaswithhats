package com.lucasi.llamaswithhats.items;

import com.lucasi.llamaswithhats.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor {

	protected String name;

	// Main Constructor
	public ItemArmorBase(String unlocalizedName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
		super(material, renderIndex, equipmentSlotIn);

		this.name = unlocalizedName;

		setCreativeTab(Main.creativeTab);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
	}

	// Register the item textures for this model
	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	// Set which creative-mode tab this item should show up in
	public ItemArmorBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	//	 @Override
	//	 public ArmorMaterial setRepairItem(ItemStack stack)
	//     {
	//         return ModItems.ARMOR_WOOL;
	//     }

	//	 @Override
	//	    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
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
