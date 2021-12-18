package me.lambdaurora.lambdynlights.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import lombok.val;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class DynamicLightsConfig
{
    public static ForgeConfigSpec ConfigSpec;

    public static ForgeConfigSpec.ConfigValue<String> Quality;
    public static ForgeConfigSpec.ConfigValue<Boolean> EntityLighting;
    public static ForgeConfigSpec.ConfigValue<Boolean> TileEntityLighting;


    static
    {
        val builder = new ConfigBuilder("Dynamic Lights Settings");

        builder.Block("Lighting Settings", b -> {
            Quality = b.define("Quality Mode (OFF, SLOW, FAST, REALTIME)", "REALTIME");
            EntityLighting = b.define("Dynamic Entity Lighting", true);
            TileEntityLighting = b.define("Dynamic TileEntity Lighting", true);
        });

        ConfigSpec = builder.Save();
    }

    public static void loadConfig(Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
        ConfigSpec.setConfig(configData);
    }

}
