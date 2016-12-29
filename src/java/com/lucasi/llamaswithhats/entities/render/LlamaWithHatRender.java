package com.lucasi.llamaswithhats.entities.render;

import com.lucasi.llamaswithhats.ModInfo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLlama;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Charley (copied from CorwinJV) on 12/28/2016.
 */
public class LlamaWithHatRender extends RenderLlama
{
	private static final ResourceLocation LLAMA_WITH_HAT_TEXTURES = new ResourceLocation(ModInfo.RESOURCE_PREFIX + "textures/entity/llama_with_hat.png");

	public LlamaWithHatRender(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn);
//		for(int i = layerRenderers.size() - 1; i >= 0; i--)
//		{
//			if(this.layerRenderers.get(i) instanceof LayerWolfCollar)
//			{
//				this.layerRenderers.remove(i);
//			}
//		}
	}

	protected ResourceLocation getEntityTexture(EntityLlama entity)
	{
		return LLAMA_WITH_HAT_TEXTURES;
	}
}
