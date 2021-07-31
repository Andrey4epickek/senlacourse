package com.library.ui.menu;

import java.util.Objects;

public class Navigator {

    private static Navigator instance;
    private Menu currentMenu;

    private Navigator() {
        this.currentMenu=Builder.getInstance().getRootMenu();
    }

    public static Navigator getInstance() {
        instance= Objects.requireNonNullElse(instance,new Navigator());
        return instance;
    }

    public void printMenu(){
        System.out.println(currentMenu.getName());
        if (!currentMenu.getMenuItems().isEmpty()) {
            int pos=0;
            for(MenuItem item:currentMenu.getMenuItems()){
                pos++;
                if(item!=null){
                    System.out.println(pos+" - "+item.getTitle());
                }
            }
        }
    }

    public void navigate(int index){
        if(index<=currentMenu.getMenuItems().size()&&index>0){
            MenuItem item=currentMenu.getMenuItems().get(index-1);
            if(item.getNextMenu()!=null){
                currentMenu=item.getNextMenu();
            }
            if(item.getAction()!=null){
                item.doAction();
            }
        }
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
