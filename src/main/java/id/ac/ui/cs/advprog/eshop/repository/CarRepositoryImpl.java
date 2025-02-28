package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepositoryImpl implements BaseModelRepository<Car> {
    private List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        UUID uuid = UUID.randomUUID();
        car.setCarId(uuid);
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findOne(UUID id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Car edit(UUID id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                // Update the existing car with the current information
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null;
    }

    public boolean delete(UUID id) {
        return carData.removeIf(car -> car.getCarId().equals(id));
    }
}
