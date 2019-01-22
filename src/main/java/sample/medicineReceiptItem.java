package sample;

import javafx.beans.property.SimpleStringProperty;

public class medicineReceiptItem {

    private SimpleStringProperty name;
    private SimpleStringProperty dosage;
    private SimpleStringProperty days;

    public medicineReceiptItem(){

    }

    public medicineReceiptItem(String name, String dosage, String days){

        this.name = new SimpleStringProperty(name);
        this.dosage = new SimpleStringProperty(dosage);
        this.days = new SimpleStringProperty(days);

    }

    public String getName(){
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }

    public String getDosage(){
        return dosage.get();
    }
    public void setDosage(String dosage){
        this.dosage.set(dosage);
    }

    public String getDays(){
        return days.get();
    }
    public void setDays(String days){
        this.days.set(days);
    }
}
