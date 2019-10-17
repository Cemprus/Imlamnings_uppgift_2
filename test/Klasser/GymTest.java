package Klasser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

class GymTest {

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