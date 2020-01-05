package ua.edu.sumdu.ta.maximbova.pr6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @Maxim Bova
 */
public class MainClass {
    public static void main (String [] args){
        
      
        Task task0 = new Task("The first", 10);
        Task task1 = new Task("The second", 15);
        Task task2 = new Task("The third", 20);
        Task task3 = new Task("The fourth", 25);
        Task task4 = new Task("The fifth", 50, 100, 10);
        Task task5 = new Task("The sixth", 100, 200, 25);
        
        Task[] elements = {task0, task1, task2, task3, task4, task5 };
        
        LinkedTaskList linkedList = new LinkedTaskList();
        
        for(Task task: elements){
            linkedList.add(task);
        }
        
        
        try {
            LinkedTaskList nextLinkedList = (LinkedTaskList) linkedList.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        for (int i = 0; i < nextLinkedList.size(); i++){
            System.out.println(linkedList.getTask(i).hashCode() + " : " +
                    nextLinkedList.getTask(i).hashCode());
        }
        
        for (int i = 0; i < nextLinkedList.size(); i++){
            System.out.println(linkedList.getTask(i).
                    equals(nextLinkedList.getTask(i)));
        }
        linkedList.remove(task2);
        System.out.println(linkedList.size() + " : " + nextLinkedList.size());
        
        System.out.println(linkedList.compareLists(nextLinkedList));
        System.out.println(nextLinkedList.compareLists(linkedList));
        */
        
        

        AbstractTaskList list1 = new ArrayTaskList();
        AbstractTaskList list2 = new ArrayTaskList();
        AbstractTaskList list3 = new ArrayTaskList();
        for (Task task : elements) {
            list1.add(task);
            list2.add(task);
        }
        
    }    
    
    
}
