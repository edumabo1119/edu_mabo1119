package ua.edu.sumdu.ta.maximbova.pr3;

/**
 *
 * @Maxim Bova
 */
public abstract class AbstractTaskList {
    //the number of created lists
    static protected int listCounter;
    
    //the amount of tasks in the current list
    protected int size;
    
    public abstract void add(Task task);
    public abstract void remove(Task task);
    
    //give the number of tasls in the current list
    
   
    //the another version of method size
    public int size(){
        return size;
    }
    public abstract Task getTask(int index);
    public abstract Task[] incoming(int from, int to);
}
