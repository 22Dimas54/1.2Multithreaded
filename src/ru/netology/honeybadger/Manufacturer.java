package ru.netology.honeybadger;

public class Manufacturer {
    private static final int AUTO_ASSEMBLY_TIME = 3000;

    private CarDealership carDealership;

    public Manufacturer(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public void releaseCar() {
        try {
            System.out.println("Производитель  BMW собирает авто!");
            Thread.sleep(AUTO_ASSEMBLY_TIME);
            carDealership.getCars().add(new Car());
            System.out.println("Производитель  BMW выпустил 1 авто!");
            carDealership.movingInCarDealership();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
