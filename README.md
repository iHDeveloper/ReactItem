# React Item `0.8`
Spigot library to provide developers the ability to create custom items in Minecraft.

It customizes the items in Minecraft. By injecting states into the items tag.

Items should be registered in order to be identified from the vanilla items.
Each item has its own states and if the state has changed while firing those actions. The item will be re-rendered (Aka changed).

This will allow the plugin developer to add more customized items.
And, even prevent the vanilla items to avoid unexpected behaviours.

**Note:** This library has been tested on `1.8.x`

# Hot list
A cooler name for TODO list.
- [ ] Detect changes in the state container
- [ ] Events to trigger changes to the item
- [ ] Java Agent to insert hooks in the `CraftInventory`
- [ ] Fix unstackable items problem

# Getting Started
```java
class ExamplePlugin extends JavaPlugin {
    private ReactItemAPI reactItemAPI;

    public void onEnable() {
        reactItemAPI = (ReactItemAPI) getServer().getPluginManager().getPlugin("ReactItem");   
    }
}
```
