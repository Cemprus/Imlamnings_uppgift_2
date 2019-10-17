package Klasser;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class MinaMetoder {
    private final static JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));

    public static boolean exit(){
        if (JOptionPane.showConfirmDialog(null, "Close program?", "Exit?", JOptionPane.YES_NO_OPTION) != JOptionPane.NO_OPTION){
            System.out.println("User closed program!");
            System.exit(0);
        }
        return true;
    }

    public static File selectLoadFile(){
        int result = fc.showOpenDialog(null);
        while (result != JFileChooser.APPROVE_OPTION){
            exit();
            result = fc.showOpenDialog(null);
        }
        System.out.println(fc.getSelectedFile());
        return fc.getSelectedFile();
    }

    public static File selectSaveFile(){
        fc.setSelectedFile(new File("ForTrainer.txt"));
        int result = fc.showSaveDialog(null);
        while (result != JFileChooser.APPROVE_OPTION){
            exit();
            result = fc.showSaveDialog(null);
        }
        System.out.println(fc.getSelectedFile());
        return fc.getSelectedFile();
    }

    public static Customer[] loadCustomers(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file!");
            System.exit(0);
        }
        int rows = 0;
        while(sc.hasNext()){
            rows += 1;
            sc.nextLine();
        }
        Customer[] customers = new Customer[rows/2];
        sc.close();
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file!");
            System.exit(0);
        }
        for (int i = 0; i<rows/2;i++){
            String s = sc.nextLine();
            String[] s2 = s.split(",", 2);
            s = sc.nextLine();
            try{
                customers[i] = new Customer(s2[1].trim(), Long.parseLong(s2[0].trim()), s);
            } catch (NumberFormatException e){
                System.out.println("False data format in the file! It must be: \nyymmddxxxx, Fist_Name Last_Name\nyyyy-mm-dd");
            }
        }
        sc.close();
        return customers;
    }
    static void saveCustomer(File file, Customer customer) {
        StringBuilder s = new StringBuilder();
        try {
            if(file.createNewFile()){
                System.out.println("New saveFile Created Now");
                s.append(String.format("%1s, %20s, %22s", "First_Name Last_Name", "Personal_Number", "Last_Date_of_Visit")).append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        s.append(String.format("%1s, %21s, %19s", customer.getName().trim(), customer.getNummer(), LocalDate.now().toString())).append("\n");
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(s.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}