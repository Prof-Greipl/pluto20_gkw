package de.landshut.pluto20_gkw.test;

import java.util.ArrayList;
import java.util.List;

import de.landshut.pluto20_gkw.model.Post;

public class PostTestData {

    public static List<Post> createTestdata(){
        String text ="Lore ipsum dolor sit amet ...";
        long time = new java.util.Date().getTime();

        List<Post> testList = new ArrayList<Post>();

        testList.add( new Post("1", "Author 1", "Title 1", text, time, "x"));
        time += 10000;

        testList.add( new Post("2", "Author 2", "Title 2", text, time, "x"));
        time += 10000;

        testList.add( new Post("3", "Author 3", "Title 3", text, time, "x"));
        time += 10000;


        return testList;
    }
}
