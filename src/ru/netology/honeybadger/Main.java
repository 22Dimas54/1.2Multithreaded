package ru.netology.honeybadger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final CarDealership carDealership = new CarDealership();
    private static final Manufacturer manufacturer = new Manufacturer(carDealership);

    private static final int COUNT = 10;

    public static void main(String[] args) {
        addTasksInExecutorService();
        executorService.shutdown();
    }

    private static void addTasksInExecutorService() {
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(carDealership::sellCar);
            executorService.submit(manufacturer::releaseCar);
        }
    }
}