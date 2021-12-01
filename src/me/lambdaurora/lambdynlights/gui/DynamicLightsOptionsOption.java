/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambDynamicLights.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdynlights.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public final class DynamicLightsOptionsOption extends Option {
    private static final String KEY = "lambdynlights.menu.title";
    private final Component text;

    private final Screen parent;

    public DynamicLightsOptionsOption(Screen parent) {
        super(KEY);
        this.text = new TranslatableComponent(KEY);
        this.parent = parent;
    }

    @Override
    public AbstractWidget createButton(Options options, int x, int y, int width) {
        return new Button(x, y, width, 20, this.text, btn -> Minecraft.getInstance().setScreen(new SettingsScreen(this.parent)));
    }
}
