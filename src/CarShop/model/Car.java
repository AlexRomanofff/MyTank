package CarShop.model;

import CarShop.Color;

public class Car {
    private Manufacturer manufacturer;
    private String model;
    private double price;
    private EngineKind engineKind;
    private int count;
    private Color color;
    private int carID;

    public Car () {

    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCarID() {
        return carID;
    }

    public EngineKind getEngineKind() {
        return engineKind;
    }

    public void setEngineKind(EngineKind engineKind) {
       this.engineKind = engineKind;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
