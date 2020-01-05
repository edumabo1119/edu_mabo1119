package ua.edu.sumdu.ta.maximbova.pr8n2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @Maxim Bova
 */
public class MainClass {
    public static void main (String [] args){
        
        
        Rest rest = new Rest();
        
        final String PATHTOXML = "http://www.mocky.io/v2/5bebe91f3300008500fbc0e3";
        final String PATHTOJSON = "http://www.mocky.io/v2/5bed52fd3300004c00a2959d";
        
        final String FILETOPARSEJSON = "D:\\Soft\\fileToWrite.json";
        final String FILETOPARSEXML = "D:\\Soft\\fileToWriteXml.xml";
        
        rest.httpClient(PATHTOXML, FILETOPARSEXML);
        rest.httpClient(PATHTOJSON, FILETOPARSEJSON);
                
        //List<Dish> dishes = rest.xmlReader(FILETOPARSEXML);
        
        
        JsonObject.Breakfast_menu data = rest.jsonReader(FILETOPARSEJSON);
        List<Dish> dishes = data.food;
        
        List<Integer> numbers = data.numbers;
        int num = numbers.get(0);
        
        for (Integer number: numbers){
            if (num < number){
                num = number;
            }
        }
        System.out.println("The biggest number from numbers is " + num);
        
        
        
        for(Dish dish: dishes){
            System.out.print(dish.getName() + " ");
        }
        System.out.println();
        
        
        for(Dish dish: dishes){
            if (dish.getCalories() < 700){
                System.out.print(dish.getName() + " price: " + dish.getPrice() + " ");
                
            }
        }
        System.out.println();
        
        Dish dish = dishes.get(0);
        for(int x = 0; x < dishes.size(); x++){
            if(dishes.get(x).getCalories() > dish.getCalories()){
                dish = dishes.get(x);
            }
        }
        System.out.println(dish.getName() + ": " + dish.getCalories() + " calories");
        
        
        
        
        
        
    }
}
