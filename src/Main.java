import Klasser.Gym;
import Klasser.MinaMetoder;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym(MinaMetoder.loadCustomers(MinaMetoder.selectLoadFile()), MinaMetoder.selectSaveFile());
        do {
            String name = JOptionPane.showInputDialog("Customers name or personal number\nKundens namn eller personnummer");
            JOptionPane.showMessageDialog(null, gym.stateOfCustomer(name));
        }while (MinaMetoder.exit());
    }
}
