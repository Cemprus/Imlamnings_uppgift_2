package Klasser;

import java.io.File;
import java.time.LocalDate;

public class Gym {
    private Customer[] customers;
    private Customer currentCustomer;
    enum State {IsCustomer, HasBeenCustomer, NeverBeenCustomer}
    private File saveFile;

    State hasCustomer(String name){
        try {
            Long number = Long.parseLong(name);
            for (Customer customer:this.customers){
                this.currentCustomer = customer;
                if (number.equals(customer.getNummer())){
                    if (customer.getYear().isAfter(LocalDate.now().minusYears(1))){
                        MinaMetoder.saveCustomer(saveFile, currentCustomer);
                        return State.IsCustomer;
                    }else {
                        return State.HasBeenCustomer;
                    }
                }
            }
        }catch (NumberFormatException e){
            for (Customer customer:this.customers){
                this.currentCustomer = customer;
                if (name.trim().toUpperCase().equals(customer.getName().trim().toUpperCase())){
                    if (customer.getYear().isAfter(LocalDate.now().minusYears(1))){
                        MinaMetoder.saveCustomer(saveFile, currentCustomer);
                        return State.IsCustomer;
                    } else {
                        return State.HasBeenCustomer;
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("Usar closed programm!");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return State.NeverBeenCustomer;
    }

    public String stateOfCustomer(String inData){
        String s = null;
        switch (hasCustomer(inData)) {
            case IsCustomer:
                s = String.format("%s är en nyvarande medlem", currentCustomer.getName());
                break;
            case HasBeenCustomer:
                s = String.format("%s är en före detta kund", currentCustomer.getName());
                break;
            case NeverBeenCustomer:
                s = String.format("%s har aldrig varit en kund", inData);
        }
        return s;
    }

    public Gym()  {
        this.customers = MinaMetoder.loadCustomers(MinaMetoder.SelectFile());
        this.saveFile = MinaMetoder.SaveFile();
    }
    Gym(Customer[] customers, File SaveFile){
        this.customers = customers;
        this.saveFile = SaveFile;
    }
}
