package me.ihdeveloper.react.item;

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
     * Checks if the item name is in the registry or not.
     *
     * @param name The name of the item
     * @return If item exists in the registry or not
     */
    public boolean isItemInRegistry(String name);

}
