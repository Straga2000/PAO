package exe;
import java.util.Arrays;

public class ex3 {
    public boolean isPrime(int n) {

        if(n == 0 || n == 1)
            return false;

        if(n == 2)
            return true;

        if(n % 2 == 0)
            return false;

        for(int i = 3; i * i <= n; i += 2)
            if(n % i == 0)
                return false;

        return true;
    }

    public void allPrimes(int limit)
    {
        int dim = (limit - 3) / 2 + 1;
        boolean []result = new boolean[dim];

        for(int i = 0; i < dim; i++)
            result[i] = true;

        ///numarului k -> 2k + 3 deoarece excludem toate numerele pare
        for(int i = 0; i < dim; i++)
        {
            if(result[i])
            {
                int value=  2 * i + 3;
                if(isPrime(value))
                {
                    ///daca valoarea e prima, atunci ramane true
                    ///toti multiplii imparii trebuie initializati cu false
                    for(int j = 3;; j += 2)
                    {
                        int newPosition = (j * value - 3) / 2;

                        if(newPosition >= dim)
                            break;

                        result[newPosition] = false;
                    }
                }
            }
        }

        String response = "2" ;
        for(int i = 0; i < dim; i++)
            response += (result[i] ? " " + (2 * i + 3) : "");

        System.out.println(response);
    }
}
