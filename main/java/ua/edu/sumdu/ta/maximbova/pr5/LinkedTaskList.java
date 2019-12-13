package ua.edu.sumdu.ta.maximbova.pr5;

/**
 *
 * @Maxim Bova
 */
public class LinkedTaskList extends AbstractTaskList{
    private Element firstElement;
    private Element lastElement;
    
    // class for creating elements of the task list
    private class Element {
        Element next;
        Task value;
        
        Element(Task task){
            this.value = task;
        }
    
    }
    // the method for adding new tasks to the list
    @Override
    public void add(Task task){
        Element bufferElement = new Element(task);
        
        if(task == null){
            throw new IllegalArgumentException();
        }
        
        if (firstElement == null && lastElement == null){
            firstElement = bufferElement;
            lastElement = bufferElement;
            size ++;
        }
        else {
            lastElement.next = bufferElement;
            lastElement = bufferElement;
            size ++;
        }
    }
    
    //the method to get rid of all tasks that are equal to the given one
    @Override
    public void remove(Task task){
        if(task == null){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < size(); i++){
            oneoffRemove(task);
        }
    }
    
    // the method to get rid of the first task that is equal to the given one
    private void oneoffRemove(Task task){
        if (size == 0){
            throw new IllegalArgumentException ("There is no task in the list");
        }
        if (size == 1){
            if (lastElement.value == task){
                firstElement = null;
                lastElement = null;
                size--;
            }
            return;
        }
        if (firstElement.value == task){
            firstElement = firstElement.next;
            size --;
            return;
        }
        Element slide = firstElement;
        while(slide.next != null){
            if(slide.next.value == task){
                if(lastElement == slide.next){
                    lastElement = slide;
                }
                slide.next = slide.next.next;
                size --;
                return;
            }
            slide = slide.next;
        }      
        
    }
    
    //the method for getting the element by its index in the list
    @Override
    public Task getTask(int index){
        if(index >= size || index < 0){
            throw new IllegalArgumentException("The number is too big or too small. The list contains only " + size + " positions");
        }
        if (index == 0){
            return firstElement.value;
        }
        if (index == size - 1){
            return lastElement.value;
        }
        Element buffer = firstElement.next;
        for (int i = 1; i < index; i++){
            buffer = buffer.next;
        }
        return buffer.value;
    }
    
    //The method for selection the tasks that is fit to the given time interval
    
    public Task[] incoming1(int from, int to){
        Element buffer = firstElement;
        int y = 0;
        
        for (int i = 0; i < size; i++){
            if(from < buffer.value.getTime() && to >= buffer.value.getEndTime()){
                    y++;
            }
            i++;
        }
        
        Task [] selectedTasks = new Task[y];
        Element buffer2 = firstElement;
            
            for (int count = 0; count < selectedTasks.length; count++){
                if(from < buffer2.value.getTime() && to >= buffer2.value.getEndTime()){
                    selectedTasks[count] = buffer2.value;
                }
                buffer2 = buffer2.next;
            }
        return selectedTasks;
    }
    
    @Override
    public Task[] incoming(int from, int to){
        if (from < 0 || to <= from){
            throw new IllegalArgumentException("Please, check out your interval");
        }
        int listLength = 0;
        Task [] selectedTasks = new Task[size];
        
        for (int i = 0; i < size; i++){
            Task elem = getTask(i);
            if (!elem.isActive()){
                continue;
            }
            if(!elem.isRepeated()){
                if(from < elem.getTime() && to >= elem.getEndTime()){
                    selectedTasks[listLength] = elem;
                    listLength++;
                    continue;
                }       
            }
            if(elem.isRepeated()){
                int endTime = elem.getEndTime() - ((elem.getEndTime() - elem.getStartTime()) % elem.getRepeatInterval());
                
                if(from > endTime || to < elem.getStartTime()){
                    continue;
                }
                if(to >= elem.getStartTime() && from < elem.getStartTime()  || from < endTime && to >= endTime ){
                    selectedTasks[listLength] = elem;
                    listLength++;
                    continue;
                }
                if((elem.getRepeatInterval() - ((from - elem.getStartTime()) % elem.getRepeatInterval())) <= (to - from)){
                    selectedTasks[listLength] = elem;
                    listLength++;
                }
                
            }
            
        }
        
        Task [] pureSelectedTasks = new Task[listLength];
        
        for (int y = 0; y < listLength; y++){
            pureSelectedTasks[y] = selectedTasks[y];
        }
        return pureSelectedTasks;
    }
    
}
