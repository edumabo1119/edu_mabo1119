package ua.edu.sumdu.ta.maximbova.pr8;
import java.util.Iterator;


/**
 *
 * @Maxim Bova
 */

public interface TaskIterable extends Iterable<Task>{
    @Override
    abstract Iterator iterator();
}
