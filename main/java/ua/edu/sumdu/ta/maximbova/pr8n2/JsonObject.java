package ua.edu.sumdu.ta.maximbova.pr8n2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @Maxim Bova
 */
    

public class JsonObject {
    private Breakfast_menu breakfast_menu;
 
    public class Breakfast_menu {
        ArrayList < Dish > food = new ArrayList <> ();
        ArrayList < Integer > numbers = new ArrayList <> ();
        
    }
    public Breakfast_menu getBreakfast_menu() {
        return breakfast_menu;
    }
    public void setBreakfast_menu(Breakfast_menu breakfast_menu) {
        this.breakfast_menu = breakfast_menu;
    }

    

    
}
