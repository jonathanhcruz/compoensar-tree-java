package CustomSystem.menu;

public class MenuItems {
    private String itemName;
    private Runnable action;
    private boolean isActive;

    public MenuItems(String itemName, boolean isActive, Runnable action) {
        this.itemName = itemName;
        this.isActive = isActive;
        this.action = action;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void executeAction() {
        if (action != null) {
            action.run();
        } else {
            System.out.println("No action defined for this menu item.");
        }
    }
}
