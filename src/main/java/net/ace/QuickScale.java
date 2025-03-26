package net.ace;

import me.shedaniel.autoconfig.AutoConfig;
import net.ace.Config.KeyHandler;
import net.ace.Config.ModConfig;
import net.fabricmc.api.ModInitializer;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class QuickScale implements ModInitializer {
	public static final String MOD_ID = "quickscale";

	@Override
	public void onInitialize() {
		// 注册 Cloth Config
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

		// 初始化快捷键监听
		KeyHandler.init();
	}
}