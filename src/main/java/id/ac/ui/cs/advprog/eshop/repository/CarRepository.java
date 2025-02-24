package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;

public interface CarRepository {
    Car create(Car car);
    Iterator<Car> findAll();
    Car findOne(String id);
    Car edit(String id, Car updatedCar);
    boolean delete(String id);
}