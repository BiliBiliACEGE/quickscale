package net.ace.Config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.ace.QuickScale;

@Config(name = QuickScale.MOD_ID)
public class ModConfig implements ConfigData {
    // 默认缩放值
    @ConfigEntry.Gui.Excluded
    public int defaultScaleValue = 1;
    // 玩家缩放配置
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int scaleValue = 1;

    // 实体缩放配置
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int entityScaleValue = 1;

    @ConfigEntry.Gui.Excluded
    public String playerCommandTemplate = "attribute %s minecraft:generic.scale base set %d";

    @ConfigEntry.Gui.Excluded
    public String entityCommandTemplate = "attribute %s minecraft:generic.scale base set %d";
}