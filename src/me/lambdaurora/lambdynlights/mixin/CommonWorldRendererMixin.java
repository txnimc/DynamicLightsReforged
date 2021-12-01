/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambDynamicLights.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdynlights.mixin;

import me.lambdaurora.lambdynlights.LambDynLights;
import me.lambdaurora.lambdynlights.LambDynLightsCompat;
import me.lambdaurora.lambdynlights.accessor.WorldRendererAccessor;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LevelRenderer.class, priority = 900)
public abstract class CommonWorldRendererMixin implements WorldRendererAccessor
{
    @Invoker("scheduleChunkRender")
    @Override
    public abstract void lambdynlights_scheduleChunkRebuild(int x, int y, int z, boolean important);

    @Inject(
            method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void onGetLightmapCoordinates(BlockAndTintGetter world, BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir)
    {
        if (!world.getBlockState(pos).isSolidRender(world, pos) && LambDynLights.get().config.getDynamicLightsMode().isEnabled())
            cir.setReturnValue(LambDynLights.get().getLightmapWithDynamicLight(pos, cir.getReturnValue()));
    }
}
