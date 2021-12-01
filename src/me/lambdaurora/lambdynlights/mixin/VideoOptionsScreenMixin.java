/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambDynamicLights.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdynlights.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import me.lambdaurora.lambdynlights.gui.DynamicLightsOptionsOption;
import me.lambdaurora.spruceui.Tooltip;
import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoSettingsScreen.class)
public class VideoOptionsScreenMixin extends OptionsSubScreen {
    @Shadow
    private OptionsList list;

    @Unique
    private Option lambdynlights$option;

    public VideoOptionsScreenMixin(Screen parent, Options gameOptions, Component title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstruct(Screen parent, Options gameOptions, CallbackInfo ci) {
        this.lambdynlights$option = new DynamicLightsOptionsOption(this);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.list.addBig(this.lambdynlights$option);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(PoseStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        Tooltip.renderAll(this, matrices);
    }
}
