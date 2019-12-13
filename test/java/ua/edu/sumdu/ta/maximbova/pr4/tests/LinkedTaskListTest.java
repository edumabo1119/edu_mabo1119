package ua.edu.sumdu.ta.maximbova.pr4.tests;

import org.junit.Before;
import ua.edu.sumdu.ta.maximbova.pr4.*;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
