import libUtilities.Simple;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TransferQueue;

public class newMian {
    public static void main(String[] args) throws IllegalAccessException {
        //String a = "test";
        //int []a= {10, 20, 30};
//        ArrayList<Integer> a = new ArrayList<>();
//        a.add(10);
//        a.add(20);
//        System.out.println(((Object) a).getClass().getSimpleName());
        Simple s = new Simple("da", 1);
        Field[] fieldsName = Simple.class.getDeclaredFields();
        for(Field f : fieldsName)
        {
            f.setAccessible(true);
            System.out.println(f.getName() + " " + f.get(s));
        }
    }
}
