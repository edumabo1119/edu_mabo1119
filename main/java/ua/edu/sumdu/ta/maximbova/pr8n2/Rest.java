package ua.edu.sumdu.ta.maximbova.pr8n2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;
import static javafx.scene.input.KeyCode.T;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;





/**
 *
 * @Maxim Bova
 */
public class Rest {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   
    
    public void httpClient(String path, String pathToWrite){
        try(
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(new HttpGet(path));
        ){
            HttpEntity entity = response.getEntity();
            
            if (entity != null){
                String data = IOUtils.toString(entity.getContent());
                
                File file = new File(pathToWrite);
                PrintWriter pw = new PrintWriter(file);
                pw.println(data);
                pw.close();
                
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public List<Dish> xmlReader(String file){
        
        try{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        

        NodeList foodNodeList = document.getElementsByTagName("food");
        
        List<Dish> dishes = new ArrayList<> ();
        
        for(int i = 0; i < foodNodeList.getLength(); i++){
            if(foodNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element foodElement = (Element) foodNodeList.item(i);
                Dish dish = new Dish();
                
                NodeList fieldNodeList = foodElement.getChildNodes();
                for(int y = 0; y < fieldNodeList.getLength(); y++){
                    if(fieldNodeList.item(y).getNodeType() == Node.ELEMENT_NODE){
                        Element fieldElement = (Element) fieldNodeList.item(y);
                        switch(fieldElement.getNodeName()){
                            case "name": {
                                dish.setName(fieldElement.getTextContent());
                            }break;
                            case "description": {
                                dish.setDescription(fieldElement.getTextContent());
                            }break;
                            case "price": {
                                String sPrice = fieldElement.getTextContent().substring(1);
                                dish.setPrice(sPrice);
                            }break;
                            case "calories": {
                                dish.setCalories(Integer.valueOf(fieldElement.getTextContent()));
                            }
                        }
                    }
                }
                dishes.add(dish);
            }
        }
        return dishes;
        
        }catch(Exception e){
            System.out.println(e);
        }
        throw new RuntimeException("Something went wrong in xmlReader function");

    }

    public JsonObject.Breakfast_menu jsonReader(String address){
        try{
            
        File file = new File(address);
        Scanner scanner = new Scanner(file);
        
        String text = "";
        while(scanner.hasNextLine()){
            text = text + scanner.nextLine();
        }
         
        //Gson gson = new GsonBuilder().create();
        
        Gson gson = new Gson();

        JsonObject data =  gson.fromJson(text, JsonObject.class);
        
        return data.getBreakfast_menu();
        
        } catch(Exception ex){
            System.out.println(ex);
        }
        
        throw new RuntimeException("Something went wrong in the jsonReader");
    }
       
     
    
}
