package Klasser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class MinaMetoderTest {

    @Test
    void testingLoadCustomers() {
        Customer[] customers = MinaMetoder.loadCustomers(new File("customers.txt"));
        Customer[] expected = new Customer[]{
                new Customer("Alhambra Aromes", 7603021234L, "2018-07-01"),
                new Customer("Diamanda Djedi", 7608021234L, "2019-01-30"),
                new Customer("Ida Idylle", 2222222222L, "2017-03-07"),
                new Customer("Nahema Ninsson", 7805211234L, "2019-01-04")
        };

        Assert.assertEquals(customers.length, 14);
        Assert.assertEquals(expected[0].getName(), customers[0].getName());
        Assert.assertEquals(expected[0].getNummer(), customers[0].getNummer());
        Assert.assertEquals(expected[0].getYear(), customers[0].getYear());
        Assert.assertEquals(expected[1].getName(), customers[3].getName());
        Assert.assertEquals(expected[1].getNummer(), customers[3].getNummer());
        Assert.assertEquals(expected[1].getYear(), customers[3].getYear());
        Assert.assertEquals(expected[2].getName(), customers[8].getName());
        Assert.assertEquals(expected[2].getNummer(), customers[8].getNummer());
        Assert.assertEquals(expected[2].getYear(), customers[8].getYear());
        Assert.assertEquals(expected[3].getName(), customers[13].getName());
        Assert.assertEquals(expected[3].getNummer(), customers[13].getNummer());
        Assert.assertEquals(expected[3].getYear(), customers[13].getYear());
    }

    @Test
    void testingSaveCustomer() throws IOException {
        Customer customer = new Customer("Nahema Ninsson", 7805211234L, "2019-01-04");
        File file = new File("Test.txt");
        MinaMetoder.saveCustomer(file, customer);

        Scanner sc = new Scanner(file);
        StringBuilder s = new StringBuilder();
        while (sc.hasNextLine()){
            s.append(sc.nextLine()).append("\n");
        }
        String expected = "First_Name Last_Name,      Personal_Number,     Last_Date_of_Visit\n" +
                "Nahema Ninsson,            7805211234,          2019-10-15\n";

        Assert.assertEquals(expected, s.toString());
        sc.close();
        file.delete();
    }
}