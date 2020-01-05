package ua.edu.sumdu.ta.maximbova.pr7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static HashMap positions;
    List <String> keyList;
    List <String> valueList;


    @BeforeClass
    public static void beforeClassMethod(){
        Searcher searcher = new Searcher();
        positions = searcher.Search();
    }

    @Test
    public void checkAmount(){
        Assert.assertEquals(3, positions.size());
    }

    @Test
    public void checkPointException(){
        Assert.assertNotNull(positions);
    }

    @Test
    public void checkPositions(){
        String [] jobs = {"Data Center Engineer", "Site Reliability Engineer (DevOps)",
                "SAN Storage Engineer"};
        keyList = new ArrayList(positions.keySet());
        String[] staff = new String [keyList.size()];
        keyList.toArray(staff);

        Assert.assertArrayEquals(jobs, staff);

    }

    @Test
    public void checkCities(){
        String [] towns = {"Cincinnati, USA", "Waltham, USA",
                "Cincinnati, USA"};
        valueList = new ArrayList(positions.values());
        String[] cities = new String [valueList.size()];
        valueList.toArray(cities);

        Assert.assertArrayEquals(towns, cities);

    }

}
