import Klasser.Gym;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Gym gym = new Gym();
        while(true){
            JOptionPane.showMessageDialog(null, gym.stateOfCustomer(JOptionPane.showInputDialog("Kundens Namn eller Personnummer")));
            if (JOptionPane.showConfirmDialog(null, "Next customer?", "Exit?", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }

    }
}
