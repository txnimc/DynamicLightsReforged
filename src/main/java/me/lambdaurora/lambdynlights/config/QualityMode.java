package me.lambdaurora.lambdynlights.config;

import me.jellysquid.mods.sodium.client.gui.options.TextProvider;


public enum QualityMode implements TextProvider
{
    OFF("Off"),
    SLOW("Slow"),
    FAST("Fast"),
    REALTIME("Realtime");

    private final String name;

    private QualityMode(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return this.name;
    }
}