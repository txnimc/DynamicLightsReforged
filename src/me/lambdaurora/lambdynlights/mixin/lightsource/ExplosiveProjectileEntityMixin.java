/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambDynamicLights.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdynlights.mixin.lightsource;

import me.lambdaurora.lambdynlights.DynamicLightSource;
import me.lambdaurora.lambdynlights.LambDynLights;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractHurtingProjectile.class)
public abstract class ExplosiveProjectileEntityMixin implements DynamicLightSource
{
    @Override
    public void dynamicLightTick()
    {
        if (!this.isDynamicLightEnabled())
            this.setDynamicLightEnabled(true);
    }

    @Override
    public int getLuminance()
    {
        if (LambDynLights.get().config.hasEntitiesLightSource())
            return 14;
        return 0;
    }
}
