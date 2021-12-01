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
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Adds a debug string for dynamic light sources tracking and updates.
 *
 * @author LambdAurora
 * @version 1.3.2
 * @since 1.3.2
 */
@Mixin(DebugScreenOverlay.class)
public class DebugHudMixin
{
    @Inject(method = "getLeftText", at = @At("RETURN"))
    private void onGetLeftText(CallbackInfoReturnable<List<String>> cir)
    {
        List<String> list = cir.getReturnValue();
        LambDynLights ldl = LambDynLights.get();
        StringBuilder builder = new StringBuilder("Dynamic Light Sources: ");
        builder.append(ldl.getLightSourcesCount())
                .append(" (U: ")
                .append(ldl.getLastUpdateCount());

        if (!ldl.config.getDynamicLightsMode().isEnabled()) {
            builder.append(" ; ");
            builder.append(ChatFormatting.RED);
            builder.append("Disabled");
            builder.append(ChatFormatting.RESET);
        }

        builder.append(')');
        list.add(builder.toString());
    }
}
