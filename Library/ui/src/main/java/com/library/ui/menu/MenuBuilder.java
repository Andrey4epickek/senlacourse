package com.library.ui.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {
    private final List<MenuItem> items;
    private final Menu menu;

    public MenuBuilder(Menu menu) {
        this.menu=menu;
        this.items= new ArrayList<>();
    }

    public MenuBuilder addItem(MenuItem item){
        items.add(item);
        return this;
    }

    public Menu build(String name){
        menu.setMenuItems(items);
        menu.setName(name);
        return menu;
    }
}

