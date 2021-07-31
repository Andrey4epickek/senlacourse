package com.library.ui.menu;


import java.util.Objects;

public class Builder {
    private static Builder instance;
    private Menu rootMenu;

    private Builder(){
        buildMenu();
    }

    public static Builder getInstance(){
        instance= Objects.requireNonNullElse(instance,new Builder());
        return instance;
    }

    public void buildMenu(){
        rootMenu=new MenuBuilderFactory().mainMenu(new Menu(),null);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}
