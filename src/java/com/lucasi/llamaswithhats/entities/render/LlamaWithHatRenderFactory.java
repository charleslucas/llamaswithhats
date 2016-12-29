package com.lucasi.llamaswithhats.entities.render;

import com.lucasi.llamaswithhats.entities.EntityLlamaWithHat;

import net.minecraft.client.model.ModelLlama;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class LlamaWithHatRenderFactory implements IRenderFactory<EntityLlamaWithHat> {
	@Override
	public Render<? super EntityLlamaWithHat> createRenderFor(RenderManager manager) {
		return new LlamaWithHatRender(manager, new ModelLlama(/*scale*/ 0), 0.5F);
	}
}
