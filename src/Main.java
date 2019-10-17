import Klasser.Gym;
import Klasser.MyMethods;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym(Gym.loadCustomers(MyMethods.selectFile(MyMethods.load)), MyMethods.selectFile(MyMethods.save));

        do {
            String str = JOptionPane.showInputDialog("Customers name or personal number\nKundens namn eller personnummer");
            str = gym.message(gym.stateOfCustomer(str));
            JOptionPane.showMessageDialog(null, str);
        }while (MyMethods.exit());
    }
}
