package ua.edu.sumdu.ta.maximbova.pr6;
import java.util.Iterator;
import java.util.*;
import javax.xml.bind.annotation.*;


/**
 *
 * 
 * @Maxim Bova
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Tasks")
public abstract class AbstractTaskList implements Cloneable, TaskIterable {
    //the number of created lists
    static protected int listCounter;
    
    //the amount of tasks in the current list
    @XmlElement
    protected int size;


    public int getSize() {
        return size;
    }
    
    
    public abstract void add(Task task);
    
    public abstract void remove(Task task);
    
    @Override
    public abstract Iterator iterator();
    
    public abstract void changeTask(int index, Task task);
    
    @Override
    public AbstractTaskList clone()throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();
    }
    

    
    //@Override
    //public abstract AbstractTaskList clone() throws CloneNotSupportedException;
    
    /*
    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        int i = 0;
        ArrayTaskList clon = (ArrayTaskList) super.clone();
        clon.list = clon.list.clone();
        for(Task task: clon){
            clon.changeTask(i++, (Task)task.clone());
        }
        return  clon;
    }
    */
   
    @Override
    public String toString(){
        String mid = ", ";
        String taskString = getTask(0).toString();
        for(int i = 1; i < size(); i++){
            taskString = taskString + mid + getTask(i).toString();
        }
        return "\"TaskListType[" + taskString + "]\"";
    }
    
    @Override
    public int hashCode(){
        int hashCode = 0;
        for(Task task: this){
            hashCode = hashCode + task.getTime();
        }
        return hashCode / this.size();
    }
    
    @Override
    public boolean equals(Object tasks){
        if (tasks == null){
            return false;
        }
        if (getClass() != tasks.getClass()) {
            return false;
        }
        
        AbstractTaskList list = (AbstractTaskList) tasks;
        if(size() != list.size()){
            return false;
        }
        for(int i = 0; i < size(); i++){
            if(!getTask(i).equals(list.getTask(i))){
                return false;
            }
        }
        return true;
    }
    
   
    //give the number of tasks in the current list
    public int size(){
        return size;
    }
    public abstract Task getTask(int index);
    public abstract Task[] incoming(int from, int to);
}
