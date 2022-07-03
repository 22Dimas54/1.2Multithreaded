package ru.netology.honeybadger;

import java.util.ArrayList;
import java.util.List;

public class CarDealership {
    final Manufacturer manufacturer = new Manufacturer(this);
    List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void sellCar() {
        manufacturer.sellCar();
    }

    public void releaseCar() {
        manufacturer.releaseCar();
    }
}
