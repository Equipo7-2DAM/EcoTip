package com.svalero.ecotip.repository;
import com.svalero.ecotip.model.Ecosistema;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcosistemaRepository extends CrudRepository<Ecosistema, Long>, JpaSpecificationExecutor<Ecosistema>{
    List<Ecosistema> findAll();
}
