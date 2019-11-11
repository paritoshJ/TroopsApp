package com.social.troops.domain.models;

/**
 * The type Drawer item.
 */
public class DrawerItem {

    /**
     * The Id.
     */
    public int id;
    /**
     * The Title.
     */
    public String title;
    /**
     * The Drawable.
     */
    public int drawable;

    /**
     * Instantiates a new Drawer item.
     *
     * @param id       the id
     * @param title    the title
     * @param drawable the drawable
     */
    public DrawerItem(int id, String title, int drawable) {
        this.id = id;
        this.title = title;
        this.drawable = drawable;
    }

//    public DrawerItem(String title, int drawable) {
//        this.title = title;
//        this.drawable = drawable;
//    }
}
