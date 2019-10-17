package Klasser;

import javax.swing.*;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Gym {
    private Customer[] customers;
    private Customer currentCustomer;
    enum State {IsCustomer, HasBeenCustomer, NeverBeenCustomer}
    private File saveFile;



    public State stateOfCustomer(String name){
        while (name == null){
            MyMethods.exit();
            name = JOptionPane.showInputDialog("Customers name or personal number\nKundens namn eller personnummer");
        }

        try {
            Long number = Long.parseLong(name);
            for (Customer customer:this.customers){
                this.currentCustomer = customer;
                if (number.equals(customer.getNummer())){
                    if (customer.getYear().isAfter(LocalDate.now().minusYears(1))){
                        saveCustomer(saveFile, currentCustomer);
                        return State.IsCustomer;
                    }else {
                        return State.HasBeenCustomer;
                    }
                }
            }
        } catch (NumberFormatException e){
            for (Customer customer:this.customers){
                this.currentCustomer = customer;
                if (name.trim().toUpperCase().equals(customer.getName().trim().toUpperCase())){
                    if (customer.getYear().isAfter(LocalDate.now().minusYears(1))){
                        saveCustomer(saveFile, currentCustomer);
                        return State.IsCustomer;
                    } else {
                        return State.HasBeenCustomer;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Unknown error!");
            System.exit(-3);
        }
        currentCustomer.setName(name);
        currentCustomer.setYear(null);
        currentCustomer.setNummer(null);
        return State.NeverBeenCustomer;
    }



    public String message(State inData){
        switch (inData) {
            case IsCustomer:
                return String.format("%1$s är en nyvarande medlem!\n%1$s is an active member!", currentCustomer.getName());
            case HasBeenCustomer:
                return String.format("%1$s är en före detta kund!\n%1$s is an inactive member!", currentCustomer.getName());
            case NeverBeenCustomer:
                return String.format("%1$s har aldrig varit en kund!\n%1$s never has been a customer!", currentCustomer.getName());
        }
        return null;
    }



    private static int numberOfCustomers(File file){
        int number = 0;
        try (Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                number += 1;
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("File not found!");
            System.exit(0);
        }
        return number/2;
    }



    public static Customer[] loadCustomers(File file) {
        Customer[] customers = new Customer[numberOfCustomers(file)];

        try (Scanner sc = new Scanner(file)){
            for (int i = 0; i<customers.length;i++){
                String s = sc.nextLine();
                String[] s2 = s.split(",", 2);
                s = sc.nextLine();
                try{
                    customers[i] = new Customer(s2[1].trim(), Long.parseLong(s2[0].trim()), s);
                } catch (NumberFormatException e){
                    System.out.println("False data format in the file! It must be: \nyymmddxxxx, Fist_Name Last_Name\nyyyy-mm-dd");
                }
            }
        } catch (Exception e) {
            System.out.println("File not found!");
            System.exit(0);
        }

        return customers;
    }



    static void saveCustomer(File file, Customer customer) {
        StringBuilder s = new StringBuilder();
        try {
            if(file.createNewFile()){
                System.out.println("New saveFile Created Now");
                s.append(String.format("%-22s, %-15s, %-20s\n", "First Name  Last Name", "Personal Number", "Date of Visit"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        s.append(String.format("%-22s, %-15s, %-20s\n", customer.getName().trim(), customer.getNummer(), LocalDate.now().toString()));
        try (FileWriter fw = new FileWriter(file, true)){
            fw.write(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Gym(Customer[] customers, File SaveFile){
        this.customers = customers;
        this.saveFile = SaveFile;
    }
}
