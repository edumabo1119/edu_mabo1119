package ua.edu.sumdu.ta.maximbova.pr3;

/**
 *
 * @Maxim Bova
 */
public class ArrayTaskList extends AbstractTaskList{
    //the amount of positions to enlarge the list
    final int EXPANSELIMIT = 10;
    
    //the prefix for tasks titles
    final String TITLEPREFIX = "[EDUCTR][TA]";
    
    private Task [] list = new Task[10];
   
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
            task.setTitle( TITLEPREFIX + task.getTitle());
            list[size] = task;
            //the counter to check out the spent space
            size ++;
        }
    }
    
    /*
    the method to get rid of all tasks that are equal to the given one
    the empty taks are not allowed to be deleted
    */
    
    @Override
    public void remove(Task task){
        for(int i = 0; i < list.length; i++){
            if (task == list[i]){
                if(list[i].getTime() >= 0 && list[i].getTitle() != null){
                    list[i] = null;
                    //the counter to check out the spent space
                    size --;
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
    /*
    The method for selection the tasks that fit to the geven time interval
    */
    
    @Override
    public Task[] incoming(int from, int to){
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
                }       
            }
            else if(from > elem.getStartTime() && from <= elem.getEndTime() || to <= elem.getEndTime() && to > elem.getStartTime()){
                    selectedTasks[i] = elem;
                    i++;
            }
        }
        Task [] pureSelectedTasks = new Task[i];
        
        for (int y = 0; y < i; y++){
            pureSelectedTasks [y] = selectedTasks[y];
        }
        return pureSelectedTasks;
    }
}
