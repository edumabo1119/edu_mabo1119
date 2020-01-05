package ua.edu.sumdu.ta.maximbova.pr6;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.*;
import javax.xml.bind.annotation.*;


/**
 *
 * @Maxim Bova
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Tasks")
public class ArrayTaskList extends AbstractTaskList {
    //the amount of positions to enlarge the list
    final static int EXPANSELIMIT = 10;
    //the prefix for tasks titles
    final static String TITLEPREFIX = "[EDUCTR][TA]";
    
    @XmlElement(name = "task")
    private Task [] list = new Task[10];
    
   
    /*
    @Override
    public ArrayTaskList clone(){
        Task[] copyList = new Task[size];
        for (int i = 0; i < size; i++){
            copyList[i] = (Task) list[i].clone();
        }
        return null;
    */
    @Override
    public Iterator iterator() {
        return new ArrayClassIterator();
    }
    
    
    private class ArrayClassIterator implements Iterator, Cloneable {
        int index;
        @Override
        public boolean hasNext(){
            if(index < size()){
                return true;
            }
            return false;
        }
        @Override
        public Task next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return list[index++];
        }
    }
    
    
    
    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList clon = (ArrayTaskList) super.clone();
        int i = 0;
        clon.list = clon.list.clone();
        for(Task task: clon){
            clon.changeTask(i++, (Task)task.clone());
        }
        return  clon;
    }
    
    
    /* the method for adding new tasks to the list
       increase the list length when there are no free spots
       the empty tasks are not allowed to be added
    */
    @Override
    public void add(Task task){
        if (size == list.length){
            Task [] bufferList = new Task[list.length + EXPANSELIMIT];
            for (int i = 0; i < list.length; i++){
                bufferList[i] = list[i];
            }
            list = bufferList;
        }
        
        if (task.getTime() >= 0 && task.getTitle() != null) {
            /*
            if(!task.getTitle().startsWith(TITLEPREFIX)){
               task.setTitle( TITLEPREFIX + task.getTitle()); 
            }
            */
            list[size] = task;
            //the counter to check out the spent space
            size ++;
        }
    }
    
    /*
    the method to get rid of all tasks that are equal to the given one
    the empty tasks are not allowed to be deleted
    */
    
    @Override
    public void remove(Task task){
        for(int i = 0; i < list.length; i++){
            if (task.equals(list[i])){
                if(list[i].getTime() >= 0 && list[i].getTitle() != null){
                    list[i] = null;
                    //the counter to check out the spent space
                    size--;
                    for (int y = i + 1; y < list.length-1; y++){
                        list[y-1] = list[y];
                    }
                }
            }
        }
    }
    
    //the method for getting the element by its index in the list
    @Override
    public Task getTask(int index) {
        if (index >= 0 && index < size()){
            return list[index];
        }
        throw new IllegalArgumentException("Index is out of range!");
    }
    
    @Override
    public void changeTask(int index, Task task) {
        if (index >= 0 && index < size()){
            list[index] = task;
        }
        else{
           throw new IllegalArgumentException("Index is out of range!"); 
        }
        
    }
    
    LinkedTaskList toLinked(){
        LinkedTaskList items = new LinkedTaskList();
        
        for(Task task: this){
            items.add(task);
        }
        return items;
    }
    
    
    /*
    The method for selection the tasks that fit to the gven time interval
    */
    
    @Override
    public Task[] incoming(int from, int to){
        if (from < 0 || to <= from){
            throw new IllegalArgumentException("Please, check out your interval");
        }
        int i = 0;
        Task [] selectedTasks = new Task[list.length];
        for(Task elem:list){
            if (elem == null){
                break;
            }
            if (!elem.isActive()){
                continue;
            }
            if(!elem.isRepeated()){
                if(from < elem.getTime() && to >= elem.getEndTime()){
                    selectedTasks[i] = elem;
                    i++;
                    continue;
                }       
            }
            if(elem.isRepeated()){
                int endTime = elem.getEndTime() - ((elem.getEndTime() - elem.getStartTime()) % elem.getRepeatInterval());
                
                if(from > endTime || to < elem.getStartTime()){
                    continue;
                }
                if(to >= elem.getStartTime() && from < elem.getStartTime()  || from < endTime && to >= endTime ){
                    selectedTasks[i] = elem;
                    i++;
                    continue;
                }
                if((elem.getRepeatInterval() - ((from - elem.getStartTime()) % elem.getRepeatInterval())) <= (to - from)){
                        selectedTasks[i] = elem;
                        i++;
                }
                
            }
        }
        Task [] pureSelectedTasks = new Task[i];
        
        for (int y = 0; y < i; y++){
            pureSelectedTasks [y] = selectedTasks[y];
        }
        return pureSelectedTasks;
    }
    public ArrayTaskList toArray(){
        return this;
    }
}
