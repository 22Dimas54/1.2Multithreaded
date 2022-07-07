package ru.netology.honeybadger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Manufacturer {
    private static final int AUTO_ASSEMBLY_TIME = 3000;
    private static final int TIME_BUYING_CAR = 3000;
    private CarDealership carDealership;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Condition condition = reentrantLock.newCondition();


    public Manufacturer(CarDealership carDealership) {
        this.carDealership = carDealership;
    }

    public void releaseCar() {
        try {
            reentrantLock.lock();
            System.out.printf("Производитель  %s собирает авто!\n", Thread.currentThread().getName());
            Thread.sleep(AUTO_ASSEMBLY_TIME);
            carDealership.getCars().add(new Car());
            System.out.printf("Производитель  %s выпустил 1 авто!\n", Thread.currentThread().getName());
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void sellCar() {
        try {
            reentrantLock.lock();
            System.out.printf("%s: зашел в автосалон!\n", Thread.currentThread().getName());
            while (carDealership.getCars().size() == 0) {
                System.out.printf("%s: Машин нет\n", Thread.currentThread().getName());
                condition.await();
            }
            Thread.sleep(TIME_BUYING_CAR);
            System.out.printf("%s: уехал на новеньком авто!\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
