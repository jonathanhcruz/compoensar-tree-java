package CustomSystem;

import java.util.ArrayList;

import CustomSystem.Input.SystemGetInput;
import CustomSystem.menu.MenuItems;


public class SystemProgram {
  private ArrayList<MenuItems> menuList;

  public SystemProgram(ArrayList<MenuItems> menuList) {
    this.menuList = menuList;

    menuList.add(new MenuItems("Exit", true,  () -> {
      System.out.println("Exiting the program. Goodbye!");
      System.exit(0);
    }));
  }

  public void run(){
    for (int i = 0; i < menuList.size(); i++) {
      MenuItems item = menuList.get(i);
      if (item.isActive()) {
        System.out.println((i + 1) + ". " + item.getItemName());
      }
    }

    final SystemGetInput systemGetInfo = new SystemGetInput("Enter the number of the action you want to perform");
    final String action = systemGetInfo.getResponse();

    for (int i = 0; i < menuList.size(); i++) {
      MenuItems item = menuList.get(i);

      if (item.isActive() && action.equals(String.valueOf(i + 1))) {
        System.out.println("//-----------------------//");
        item.executeAction();
        System.out.println("//-----------------------//");

        if (item.getItemName() != "Exit") {
          this.run();
        }
      }
    }

    this.run();
  }
}
