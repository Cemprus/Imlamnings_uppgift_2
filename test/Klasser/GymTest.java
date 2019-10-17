package Klasser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class GymTest {

    @Test
    void testingLoadCustomers() {
        Customer[] customers = Gym.loadCustomers(new File("customers.txt"));
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
    void testingSaveCustomer() throws FileNotFoundException {
        Customer customer = new Customer("Nahema Ninsson", 7805211234L, "2019-01-04");
        Customer customer1 = new Customer("Ruscemp", 1111111111L, "2018-12-15");
        Customer customer2 = new Customer("Jaanek", 9906061479L, "2019-10-10");
        File file = new File("Test.txt");
        file.deleteOnExit();
        Gym.saveCustomer(file, customer);
        Gym.saveCustomer(file, customer1);
        Gym.saveCustomer(file, customer2);

        Scanner sc = new Scanner(file);
        StringBuilder s = new StringBuilder();
        while (sc.hasNext()){
            s.append(sc.nextLine()).append("\n");
        }
        sc.close();
        String expected = "First_Name Last_Name,      Personal_Number,     Last_Date_of_Visit\n" +
                "Nahema Ninsson,            7805211234,          "+ LocalDate.now().toString() +"\n" +
                "Ruscemp,            1111111111,          "+ LocalDate.now().toString() +"\n" +
                "Jaanek,            9906061479,          "+ LocalDate.now().toString() +"\n";

        Assert.assertEquals(expected, s.toString());
    }

    @Test
    void testingHasCustomer() throws IOException {
        Customer[] customers = new Customer[] {
                new Customer("Greger Ganache", 7608021234L, "2019-03-23"),
                new Customer("Ida Idylle", 7911061234L, "2017-03-07")
        };

        File f = File.createTempFile("temp", null);
        f.deleteOnExit();

        Gym gym = new Gym(customers, f);

        Assert.assertEquals(gym.hasCustomer("Greger Ganache"), Gym.State.IsCustomer);
        Assert.assertEquals(gym.hasCustomer("7608021234"), Gym.State.IsCustomer);
        Assert.assertEquals(gym.hasCustomer("Ida Idylle"), Gym.State.HasBeenCustomer);
        Assert.assertEquals(gym.hasCustomer("7911061234"), Gym.State.HasBeenCustomer);
        Assert.assertEquals(gym.hasCustomer("Ruscemp"), Gym.State.NeverBeenCustomer);
        Assert.assertEquals(gym.hasCustomer("123456789000"), Gym.State.NeverBeenCustomer);
    }

    @Test
    void testingStateOfCustomer() throws IOException {
        Customer[] customers = new Customer[] {
                new Customer("Greger Ganache", 7608021234L, LocalDate.now().minusDays(1).toString()),
                new Customer("Ida Idylle", 7911061234L, LocalDate.now().minusYears(3).toString())
        };

        File f = File.createTempFile("temp", null);
        f.deleteOnExit();

        Gym gym = new Gym(customers, f);

        Assert.assertEquals("Greger Ganache är en nyvarande medlem", gym.stateOfCustomer("Greger Ganache"));
        Assert.assertEquals("Greger Ganache är en nyvarande medlem", gym.stateOfCustomer("7608021234"));
        Assert.assertEquals("Ida Idylle är en före detta kund", gym.stateOfCustomer("Ida Idylle"));
        Assert.assertEquals("Ida Idylle är en före detta kund", gym.stateOfCustomer("7911061234"));
        Assert.assertEquals("Ruscemp har aldrig varit en kund", gym.stateOfCustomer("Ruscemp"));
        Assert.assertEquals("123456789000 har aldrig varit en kund", gym.stateOfCustomer("123456789000"));
    }
}