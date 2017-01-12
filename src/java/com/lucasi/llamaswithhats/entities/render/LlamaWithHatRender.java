package com.lucasi.llamaswithhats.entities.render;

import com.lucasi.llamaswithhats.ModInfo;
import com.lucasi.llamaswithhats.entities.EntityLlamaWithHat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLlama;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Charley (copied from CorwinJV) on 12/28/2016.
 */
@SideOnly(Side.CLIENT)
public class LlamaWithHatRender extends RenderLlama
{
	private static final ResourceLocation LLAMA_WITH_HAT_BASIC_TEXTURES = new ResourceLocation(ModInfo.RESOURCE_PREFIX + "textures/entity/llama_with_hat.png");
	private static final ResourceLocation LLAMA_WITH_HAT_PAUL_TEXTURES = new ResourceLocation(ModInfo.RESOURCE_PREFIX + "textures/entity/llama_with_hat_paul.png");
	private static final ResourceLocation LLAMA_WITH_HAT_CARL_TEXTURES = new ResourceLocation(ModInfo.RESOURCE_PREFIX + "textures/entity/llama_with_hat_carl.png");

	public LlamaWithHatRender(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn);
		
// Perhaps we can use this to make a hat/helmet layer?
//		for(int i = layerRenderers.size() - 1; i >= 0; i--)
//		{
//			if(this.layerRenderers.get(i) instanceof LayerWolfCollar)
//			{
//				this.layerRenderers.remove(i);
//			}
//		}
	}

	protected ResourceLocation getEntityTexture(EntityLlamaWithHat entity)
	{
		return (entity.GetType() == 2) ? LLAMA_WITH_HAT_CARL_TEXTURES : (entity.GetType() == 1) ? LLAMA_WITH_HAT_PAUL_TEXTURES : LLAMA_WITH_HAT_BASIC_TEXTURES;
	}
}
