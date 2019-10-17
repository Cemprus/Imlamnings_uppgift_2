package Klasser;

import javax.swing.*;
import java.io.File;

public class MyMethods {
    private final static JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
    public final static int load = 0;
    public final static int save = 1;

    public static boolean exit(){
        if (JOptionPane.showConfirmDialog(null, "Close program?", "Exit?", JOptionPane.YES_NO_OPTION) != JOptionPane.NO_OPTION){
            System.out.println("User closed program!");
            System.exit(0);
        }
        return true;
    }

    public static File selectFile(int i){
        int result;
        if(i == 0){
            result = fc.showOpenDialog(null);
            while (result != JFileChooser.APPROVE_OPTION){
                exit();
                result = fc.showOpenDialog(null);
            }
            System.out.println("Selected load file " + fc.getSelectedFile());
        }else if(i == 1){
            fc.setSelectedFile(new File("ForTrainer.txt"));
            result = fc.showSaveDialog(null);
            while (result != JFileChooser.APPROVE_OPTION){
                exit();
                result = fc.showSaveDialog(null);
            }
            System.out.println("Selected save file " + fc.getSelectedFile());
        }
        return fc.getSelectedFile();
    }
}