package problems;
import java.util.Random;
import java.util.Scanner;

public class ex1 {

    public static void main(String[] args) {
        ex1 Ex1 =  new ex1();
        System.out.println("The sum is " + Ex1.sum2D(30));
    }

    public int sum2D(int bound)
    {
        int dimX, dimY;

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        dimX = sc.nextInt();
        dimY = sc.nextInt();

        int [][]matrix = new int[dimX][dimY];

        for(int i = 0; i < dimX; i++)
        {
            String response = "";
            for (int j = 0; j < dimY; j++)
            {
                matrix[i][j] = rand.nextInt(bound);
                response += matrix[i][j] + " ";
            }
            System.out.println(response);
        }
        int sum = 0;

        for(int i = 0; i < dimX; i++)
            for(int j = 0; j < dimY; j++)
                sum += matrix[i][j];

        return sum;
    }

}
