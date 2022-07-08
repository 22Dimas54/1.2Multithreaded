package ru.netology.honeybadger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarDealership {
    private static final int TIME_BUYING_CAR = 3000;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Condition condition = reentrantLock.newCondition();
    private List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return this.cars;
    }

    public void sellCar() {
        try {
            reentrantLock.lock();
            Thread.currentThread().setName("Покупатель " + (int) (Math.random() * TIME_BUYING_CAR));
            System.out.printf("%s: зашел в автосалон!\n", Thread.currentThread().getName());
            while (cars.size() == 0) {
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

    public void movingInCarDealership() {
        reentrantLock.lock();
        condition.signal();
        reentrantLock.unlock();
    }
}
