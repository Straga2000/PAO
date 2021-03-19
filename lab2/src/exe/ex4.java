package exe;
import java.util.Scanner;

public class ex4 {
    public float arithmetic()
    {
        int counter  = 0, sum = 0;

        Scanner sc =new Scanner(System.in);
        int a  = sc.nextInt();

        while (a != -1)
        {
            sum += a;
            counter++;
            a = sc.nextInt();
        }

        return  (float) sum / counter;
    }
}
