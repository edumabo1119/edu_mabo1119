package ua.edu.sumdu.ta.maximbova.pr8n2;

/**
 *
 * @Maxim Bova
 */
public class Dish {
    private String name;
    private String description;
    private String price;
    private int calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    @Override
    public String toString(){
        return "You\'ll buy " + getName() + " for " + getPrice() +
                " dollars " + " and get " + getCalories() + " calories. " +
                getDescription();
    }
    
    
    
}
