package exe;
import java.util.Arrays;

public class ex2 {
    public void compare3(int a, int b, int c)
    {
        int []arr = {a, b, c};
        Arrays.sort(arr);

        String str = "";

        for(int i = 0; i < arr.length; i++)
        {
            if(i == 0)
                str += arr[i];
            else
                str += " < " + arr[i];
        }

        System.out.println(str);
    }
}
