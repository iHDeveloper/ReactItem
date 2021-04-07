# React Item `0.8`
[![](https://jitpack.io/v/iHDeveloper/ReactItem.svg)](https://jitpack.io/#iHDeveloper/ReactItem)
Spigot library to provide developers the ability to create custom items in Minecraft.

It customizes the items in Minecraft. By injecting states into the items tag.

Items should be registered in order to be identified from the vanilla items.
Each item has its own states and if the state has changed while firing those actions. The item will be re-rendered (Aka changed).

This will allow the plugin developer to add more customized items.
And, even prevent the vanilla items to avoid unexpected behaviours.

**Note:** This library has been tested on `1.8.x`

[Spigot](https://www.spigotmc.org/resources/lib-react-gui.91047/) - [JitPack](https://jitpack.io/#iHDeveloper/ReactItem/v0.8)

## Hot list
A cooler name for TODO list.
- [ ] Detect changes in the state container
- [ ] Events to trigger changes to the item
- [ ] Java Agent to insert hooks in the `CraftInventory`
- [ ] Fix unstackable items problem

## Getting Started
```java
class ExamplePlugin extends JavaPlugin {
    private ReactItemAPI reactItemAPI;

    public void onEnable() {
        reactItemAPI = (ReactItemAPI) getServer().getPluginManager().getPlugin("ReactItem");   
    }
}
```

## Examples
- [Forbidden Stick](https://github.com/iHDeveloper/ReactItem/blob/master/test/src/main/java/me/ihdeveloper/react/item/test/item/ForbiddenStick.java)

