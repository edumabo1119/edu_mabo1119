package ua.edu.sumdu.ta.maximbova.pr8;

import java.io.IOException;
import javax.xml.bind.*;
import java.io.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilderFactory.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @Maxim Bova
 */
public class TaskXMLSerializer {
    
    public void save(AbstractTaskList list, String file) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
        
        
        
            Element root = document.createElement("tasks");
            document.appendChild(root);
        
            for(Task task: list){
                Element child = document.createElement("task");
                root.appendChild(child);
            
                Text text = document.createTextNode(task.getTitle());
                child.appendChild(text);
            
                child.setAttribute("active", String.valueOf(task.isActive()));
                child.setAttribute("time", String.valueOf(task.getTime()));
                child.setAttribute("start", String.valueOf(task.getStartTime()));
                child.setAttribute("end", String.valueOf(task.getEndTime()));
                child.setAttribute("repeat", String.valueOf(task.getRepeatInterval()));
                child.setAttribute("repeated", String.valueOf(task.isRepeated())); 
        }
        
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform (new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public AbstractTaskList load(String file){
        int end = 0;
        int start = 0;
        int time = 0;
        int repeat = 0;
        boolean active = false;
        boolean repeated = false;
        String title;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(file));
            
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getChildNodes();
            ArrayTaskList tasks = new ArrayTaskList();
            
            for(int i = 0; i < nodeList.getLength(); i++){
                if (nodeList.item(i) instanceof Element){
                    Task task = new Task();
                    title = nodeList.item(i).getTextContent();
                    task.setTitle(title);
                    
                    for(int x = 0; x < nodeList.item(i).getAttributes().getLength(); x++){
                        switch(nodeList.item(i).getAttributes().item(x).getNodeName()){
                            case "active":{
                                String buffer = nodeList.item(i).getAttributes().item(x).getTextContent();
                                active = Boolean.valueOf(buffer);
                                //System.out.println(active);
                                
                            }break;
                            case "end":{
                                String buffer = nodeList.item(i).getAttributes().item(x).getTextContent();
                                end = Integer.valueOf(buffer);
                                //System.out.println(end);
                                
                            }break;
                            case "repeat":{
                                String buffer = nodeList.item(i).getAttributes().item(x).getTextContent();
                                repeat = Integer.valueOf(buffer);
                                //System.out.println(repeat);
                                
                            }break;
                            case "repeated":{
                                String buffer = nodeList.item(i).getAttributes().item(x).getTextContent();
                                repeated = Boolean.valueOf(buffer);
                                //System.out.println(repeated);
                                
                            }break;
                            case "start":{
                                String buffer = nodeList.item(i).getAttributes().item(x).getTextContent();
                                start = Integer.valueOf(buffer);
                                //System.out.println(start);
                                
                            }break;
                            case "time":{
                                String buffer = nodeList.item(i).getAttributes().item(x).getTextContent();
                                time = Integer.valueOf(buffer);
                                //System.out.println(time);
                            }
                        }
                          
                    }
                    task.setActive(active);
                    
                    if (repeated == false){
                        task.setTime(time);
                    }
                    else{
                        task.setTime(start, end, repeat);
                    }
                    
                    //task.setTime(start, end, repeat);

                    tasks.add(task);
                    
                }
                
            }
            return tasks;
            
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        throw new NullPointerException("Something went wrong in the load function :-(");
    }
    
    
    /*
    public void save(AbstractTaskList list, String file){
        
        try{
            StringWriter writter = new StringWriter();
            
            JAXBContext jc = JAXBContext.newInstance(AbstractTaskList.class,
                    ArrayTaskList.class, Task.class);
            
            Marshaller ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            ms.marshal(list, writter);
            
            ms.marshal(list, System.out);
            ms.marshal(list, new File(file));
            
            
            String result = writter.toString();
            System.out.println(result);
            
        } catch(Exception ex){
            System.out.println(ex);
        }
        
    }
    
    public AbstractTaskList load(String file){
        try{
            JAXBContext jc = JAXBContext.newInstance(AbstractTaskList.class, ArrayTaskList.class, Task.class);
            Unmarshaller ums = jc.createUnmarshaller();
            ArrayTaskList tasks = (ArrayTaskList) ums.unmarshal(new File(file));
            
            return tasks;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        throw new IllegalArgumentException();
    }
     */
}
