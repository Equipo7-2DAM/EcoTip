package com.svalero.ecotip.repository;

import com.svalero.ecotip.dto.AnimalOutDto;
import com.svalero.ecotip.model.Animal;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

    List<Animal> findAll();
}
