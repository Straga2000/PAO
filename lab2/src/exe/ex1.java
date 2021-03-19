package exe;

public class ex1 {

    public void div5not10(int n)
    {
        String str = "";
        ////ca numarul sa fie divizibil cu 5, dar nu cu 10 inseamna ca numarul sa nu fie divizibil cu 2
        ////numerele divizibile cu 5, dar nu cu doi sunt de forma 5 + 2 * 5 * k
        ///ceea ce se poate traduce ca in for ul de mai jos
        for(int i = 5; i < n; i += 10)
        {
            str += i + " ";
        }

        System.out.println(str);
    }
}
