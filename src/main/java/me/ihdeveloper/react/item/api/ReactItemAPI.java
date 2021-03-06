package me.ihdeveloper.react.item.api;

import org.bukkit.inventory.ItemStack;

/**
 * Represents an interface of the React Item functionality
 */
public interface ReactItemAPI {

    /**
     * Registers an item to the registry.
     * So, that it can be detected and listens to events
     *
     * @param item The instance of the item
     */
    public void registerItem(ReactItem item);

    /**
     * Returns the ID of the given item
     *
     * @param itemStack The item stack that holds the ID
     * @return Null if the ID is not present at all (aka Vanilla Item).
     */
    public String loadItem(ItemStack itemStack);

    /**
     * Returns information about the item from given ID
     *
     * @param id The id of the item
     */
    public ReactItemInfo getItemInfo(String id);

    /**
     * Checks if the item ID is in the registry or not.
     *
     * @param id The ID of the item
     * @return If item exists in the registry or not
     */
    public boolean isItemInRegistry(String id);

    /**
     * Re-renders the given item information
     *
     * @param itemStack The item that needs to be re-rendered
     * @return Null if the item information is unknown
     */
    public ItemStack updateItem(ItemStack itemStack);

    /**
     * Generates an item stack from react item
     * @param item The react item to generate the item stack from
     *
     * @return A generated item stack from react item
     */
    public ItemStack createItem(Class<? extends ReactItem> item);

    /**
     * Generates an item stack from react item
     * @param item The react item to generate the item stack from
     * @param amount The amount of the created item
     *
     * @return A generated item stack from react item
     */
    public ItemStack createItem(Class<? extends ReactItem> item, int amount);

    /**
     * Generates an item stack from id
     * @param id The id of the react item
     *
     * @return The generated item stack from ID
     */
    public ItemStack createItem(String id);

    /**
     * Generates an item stack from id
     * @param id The id of the react item
     * @param amount The amount of the created item
     *
     * @return The generated item stack from ID
     */
    public ItemStack createItem(String id, int amount);

}
