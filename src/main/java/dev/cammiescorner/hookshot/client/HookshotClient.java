package dev.cammiescorner.hookshot.client;

import dev.cammiescorner.hookshot.Hookshot;
import dev.cammiescorner.hookshot.client.entity.renderer.HookshotEntityRenderer;
import dev.cammiescorner.hookshot.core.registry.ModEntities;
import dev.cammiescorner.hookshot.core.util.PlayerProperties;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HookshotClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		EntityRendererRegistry.INSTANCE.register(ModEntities.HOOKSHOT_ENTITY, (dispatcher, context) -> new HookshotEntityRenderer(dispatcher));

		FabricModelPredicateProviderRegistry.register(new Identifier(Hookshot.MOD_ID, "has_hook"), (stack, world, entity) ->
		{
			if(entity instanceof PlayerEntity)
			{
				if(((PlayerProperties) entity).hasHook())
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}

			return 0;
		});
	}
}
