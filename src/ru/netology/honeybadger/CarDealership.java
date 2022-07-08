package ru.netology.honeybadger;

import java.util.ArrayList;
import java.util.List;

public class CarDealership {
    //final Manufacturer manufacturer = new Manufacturer(this);
    private static final int TIME_BUYING_CAR = 2000;
    List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public synchronized void sellCar() {
        try {
            Thread.currentThread().setName("Покупатель " + (int) (Math.random() * TIME_BUYING_CAR));
            System.out.printf("%s: зашел в автосалон!\n", Thread.currentThread().getName());
            while (this.cars.size() == 0) {
                System.out.printf("%s: Машин нет\n", Thread.currentThread().getName());
                wait();
            }
            Thread.sleep(TIME_BUYING_CAR);
            System.out.printf("%s: уехал на новеньком авто!\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void movingInCarDealership() {
        notify();
    }
}
