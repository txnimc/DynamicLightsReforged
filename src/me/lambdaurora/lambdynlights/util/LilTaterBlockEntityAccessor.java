/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambDynamicLights.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdynlights.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface LilTaterBlockEntityAccessor
{
    boolean lambdynlights_isEmpty();

    NonNullList<ItemStack> lambdynlights_getItems();
}
