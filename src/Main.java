import Klasser.Gym;
import Klasser.MyMethods;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym(Gym.loadCustomers(MyMethods.selectFile(MyMethods.load)), MyMethods.selectFile(MyMethods.save));
        do {
            String name = JOptionPane.showInputDialog("Customers name or personal number\nKundens namn eller personnummer");
            System.out.println(name);
            JOptionPane.showMessageDialog(null, gym.stateOfCustomer(name));
        }while (MyMethods.exit());
    }
}
