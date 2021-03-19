package exe;

public class ex5 {

    public int pow(int elem, int power)
    {
        int value = elem;

        for(int i = 1; i < power; i++)
            value *= elem;

        return value;
    }
}
