package net.ace.Config;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

public class KeyHandler {
    private static KeyBinding applyPlayerScaleKey;
    private static KeyBinding applyEntityScaleKey;
    private static KeyBinding undoScaleKey;
    private static KeyBinding openConfigKey;

    public static void init() {
        // 应用玩家缩放
        applyPlayerScaleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quickscale.apply_scale",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                "category.quickscale"
        ));
        //应用实体缩放
        applyEntityScaleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quickscale.apply_entity_scale",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.quickscale"
        ));
        //撤销缩放
        undoScaleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quickscale.undo_scale",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.quickscale"
        ));

        // 打开配置界面
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quickscale.open_config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.quickscale"
        ));

        // 监听按键事件
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (applyPlayerScaleKey.wasPressed()) {
                applyPlayerScale(client);
            }
            if (applyEntityScaleKey.wasPressed()){
                applyEntityScale(client);
            }
            if (undoScaleKey.wasPressed()) {
                undoScale(client);
            }
            if (openConfigKey.wasPressed()) {
                openConfigScreen(client);
            }
        });
    }

    private static void applyPlayerScale(MinecraftClient client) {
        if (client.player == null) return;

        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        String command = String.format(
                config.playerCommandTemplate,
                client.player.getName().getString(),
                config.scaleValue
        );

        // 1.21 中发送指令的方式
        client.getNetworkHandler().sendChatCommand(command);
    }
    private static void  applyEntityScale(MinecraftClient client){
        if (client.player == null || client.world == null) return;

        // 获取准星对准的实体
        HitResult hit = client.crosshairTarget;
        if (hit.getType() != HitResult.Type.ENTITY) return;

        Entity entity = ((EntityHitResult) hit).getEntity();
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        // 发送实体缩放指令
        String command = String.format(
                config.entityCommandTemplate,
                entity.getUuidAsString(), // 实体 UUID
                config.entityScaleValue
        );
        client.getNetworkHandler().sendChatCommand(command);
    }
    private static void undoScale(MinecraftClient client) {
        if (client.player == null) return;

        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        // 发送撤销
        String playerCommand = String.format(
                config.playerCommandTemplate,
                client.player.getName().getString(),
                config.defaultScaleValue
        );
        client.getNetworkHandler().sendChatCommand(playerCommand);

        // 如果有对准实体，同时撤销实体缩放
        HitResult hit = client.crosshairTarget;
        if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) hit).getEntity();
            String entityCommand = String.format(
                    config.entityCommandTemplate,
                    entity.getUuidAsString(),
                    config.defaultScaleValue
            );
            client.getNetworkHandler().sendChatCommand(entityCommand);
        }
    }
    private static void openConfigScreen(MinecraftClient client) {
        client.setScreen(
                AutoConfig.getConfigScreen(ModConfig.class, client.currentScreen).get()
        );
    }
}