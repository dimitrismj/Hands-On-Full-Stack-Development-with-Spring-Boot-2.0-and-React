package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.repository.CarRepository;
import com.packt.cardatabase.repository.OwnerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Test
  public void saveOwner() {
    Owner owner = new Owner("John", "Johnson");
    entityManager.persistAndFlush(owner);
    Owner ownerById = ownerRepository.findById(owner.getOwnerid()).orElse(null);
    assertThat(ownerById).isNotNull();
    assertEquals("John", ownerById.getFirstname());
  }

  @Test
  public void deleteOwner() {
    Owner owner = new Owner("John", "Johnson");
    ownerRepository.deleteAll();
    assertThat(ownerRepository.findAll()).isEmpty();
  }
}
