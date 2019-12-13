package ua.edu.sumdu.ta.maximbova.pr4;

/**
 *
 * @Maxim Bova
 */
//An example of task class
public class Task {
    
    private String title;
    private boolean active;
    private int time;
    private int repeat;
    private int end;
    final private String example1 = "";
    final private String example2 = " ";
    
    //A constructor for one-off task
    public Task(String title, int time){
        if ((example1.equals(title)) || (example2.equals(title))){
            throw new IllegalArgumentException("The title should contain literals ");
        }
        if (title == null){
            throw new IllegalArgumentException("Null is a bad name for a task. Try something another ");
        }
        if (time < 0){
            throw new IllegalArgumentException("Please, enter a positive time number.");
        }
        this.title = title;
        this.time = time;
    }
    
    //A constructor for a reusable task
    public Task(String title, int start, int end, int repeat){
        if ((example1.equals(title)) || (example2.equals(title))){
            throw new IllegalArgumentException("The title should contain literals ");
        }
        if (title == null){
            throw new IllegalArgumentException("Null is a bad name for a task. Try something another ");
        }
        if (start < 0){
            throw new IllegalArgumentException("Please, enter a positive time number.");
        }
        if (end <= start){
            throw new IllegalArgumentException("Please, enter an end time bigger than the start time.");
        }
        if (repeat < 1){
            throw new IllegalArgumentException("Please, enter an interval time bigger than null.");
        }
        if (repeat > end - start){
            throw new IllegalArgumentException("Please, enter the interval time lesser than differece between the start time and the end time.");
        }
        this.title = title;
        this.time = start;
        this.end = end;
        this.repeat = repeat;
        this.active = false;
    }
    
    //A method for setting a class example's name
    public void setTitle(String title){
        if ((example1.equals(title)) || (example2.equals(title))){
            throw new IllegalArgumentException("The title should contain literals ");
        }
        if (title == null){
            throw new IllegalArgumentException("Null is a bad name for a task. Try something another ");
        }
        this.title = title;
    }
    
    //A method for getting a class example's name
    public String getTitle(){
        return title;
    }
    
    //A method for getting a task's status (active or not) 
    public boolean isActive(){
        return active;
    }
    
    // A method for setting a task's status
    public void setActive(boolean active){
        this.active = active;
    }
    
    //A method for setting a warning call's time for one-off task
    public void setTime(int time){
        if (time < 0){
            throw new IllegalArgumentException("Please, enter a positive time number.");
        }
        this.time = time;
        this.end = 0;
        this.repeat = 0;
    }
    
    //A method for setting a warning call's time for a reasuble task
    public void setTime(int start, int end, int repeat){
        
        if (start < 0){
            throw new IllegalArgumentException("Please, enter enter a positive start number.");
        }
        if (end <= start){
            throw new IllegalArgumentException("Please, enter an end number bigger than the start number.");
        }
        if (repeat < 1){
            throw new IllegalArgumentException("Please, enter an interval number bigger than null.");
        }
        if (repeat > end - start){
            throw new IllegalArgumentException("Please, enter an interval number lesser or equal than differece between the start number and the end number.");
        }
        
        this.time = start;
        this.end = end;
        this.repeat = repeat;
    }
    
    //A method for getting the time of first warning call
    public int getTime(){
        return time;
    }
    
    //A method for getting the time of the first warning call
    public int getStartTime(){
        return time;
    }
    
    //A method for getting the time of the last warning call
    public int getEndTime(){
        if(repeat > 0){
            return end;
        }
        else{
            return time;
        }
    }
    
    //A method for getting the interval time betwen calls
    public int getRepeatInterval(){
        return repeat;
    }
    
    //A method for gettinf the type of the task (one-off or reasuble)
    public boolean isRepeated(){
       return repeat > 0;
    }
    
    //A method for getting information about the task
    @Override
    public String toString(){
        if (active == false) {
            return "Task " + "\"" + title + "\"" + " is inactive";
        }
        if (repeat == 0) {
            return "Task " + "\"" + title + "\"" + " at " + time;
        }
        return "Task " + "\"" + title + "\"" + " from "  + time + " to " + end + " every " + repeat + " seconds";
    }
    
    // A method for getting amount of time to next call
    public int nextTimeAfter(int time){
        if (!isActive()){
            return -1;
        }
        if (!isRepeated()){
            if (time >= getEndTime()){
                return -1;
            }
            return getTime();
        }
       
        if ((getStartTime() + getRepeatInterval()) >= getEndTime()){
            return -1;
        }
        
        if (time + getRepeatInterval() > getEndTime()){
            return -1;
        }
        
        if (time < getStartTime()){
            return getStartTime();
        }
        int difference = time - getStartTime();
        int modul = difference % getRepeatInterval();
        return time + getRepeatInterval() - modul;
    }
    
    
}
