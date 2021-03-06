package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CarRepository carRepository;

  @Test
  public void saveCar() {
    Owner owner = new Owner("John", "Johnson");
    Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000, owner);
    entityManager.persistAndFlush(owner);
    entityManager.persistAndFlush(car);

    Car carById = carRepository.findById(car.getId()).orElse(null);
    assertThat(carById).isNotNull();
    assertEquals("John", carById.getOwner().getFirstname());
  }

  @Test
  public void deleteCars() {
    entityManager.persistAndFlush(new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000));
    entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow", "BWS-3007", 2015, 24500));
    carRepository.deleteAll();
    assertThat(carRepository.findAll()).isEmpty();
    carRepository.deleteAll();
    assertThat(carRepository.findAll()).isEmpty();
  }
}
