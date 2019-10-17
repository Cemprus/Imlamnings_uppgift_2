import Klasser.Gym;
import Klasser.MyMethods;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym(Gym.loadCustomers(MyMethods.selectFile(MyMethods.load)), MyMethods.selectFile(MyMethods.save));

        do {
            String s = JOptionPane.showInputDialog("Customers name or personal number\nKundens namn eller personnummer");
            s = gym.stateOfCustomer(gym.hasCustomer(s));
            JOptionPane.showMessageDialog(null, s);
        }while (MyMethods.exit());
    }
}
