# QuickScale Mod

![Mod Icon](src/main/resources/assets/quickscale/icon.png)

QuickScale 是一个基于 Fabric 的 Minecraft 模组，旨在通过可定制的互动范围和实体缩放来增强游戏体验。它提供了即时热键控制和动态配置，非常适合寻求精确控制游戏机制的建造者、测试人员和技术玩家。

## 功能特性

- **实体缩放**：动态调整玩家和实体的物理尺寸
    - 支持玩家和生物、掉落物等实体的独立缩放
    - 通过热键快速应用缩放设置
    - 可撤销缩放效果，恢复默认尺寸

- **互动范围调整**：
    - 自定义玩家与实体的最大互动距离（如骑马、拾取物品等）
    - 调整玩家放置/破坏方块的最大距离
    - 支持通过热键快速应用和撤销范围设置

- **配置系统**：
    - 使用 Cloth Config 提供直观的 GUI 配置界面
    - 支持实时保存和加载配置
    - 可启用/禁用指令执行反馈

## 安装指南

1. 确保已安装 [Fabric Loader](https://fabricmc.net/use/) 和 [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
2. 下载最新版本的 QuickScale mod
3. 将 mod 文件放入 `mods` 文件夹
4. 启动游戏并享受！

## 使用说明

### 热键绑定

| 功能 | 默认热键 |
|------|----------|
| 应用玩家缩放 | X |
| 应用实体缩放 | G |
| 应用方块互动距离 | M |
| 应用实体互动距离 | N |
| 撤销缩放 | Z |
| 撤销互动范围 | V |
| 打开配置界面 | C |

### 配置选项

- **缩放设置**
    - 玩家缩放倍率
    - 实体缩放倍率

- **互动范围**
    - 实体互动距离
    - 方块互动距离

- **其他设置**
    - 指令执行反馈

## 开发信息

### 技术栈

- Minecraft 1.21
- Fabric Loader
- Cloth Config API
- Mod Menu 集成

### 依赖项

```gradle
dependencies {
    modApi "me.shedaniel.cloth:cloth-config-fabric:15.0.140"
    modImplementation "net.fabricmc.fabric-api:fabric-api:0.102.0+1.21"
}
