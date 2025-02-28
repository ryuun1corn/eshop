package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.BaseModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements BaseModelService<Car> {
    @Autowired
    private BaseModelRepository<Car> carRepository;

    @Override
    public Car create(Car car) {
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findOne(UUID carId) {
        return carRepository.findOne(carId);
    }

    @Override
    public Car edit(UUID carId, Car car) {
        return carRepository.edit(carId, car);
    }

    @Override
    public boolean delete(UUID carId) {
        return carRepository.delete(carId);
    }
}
