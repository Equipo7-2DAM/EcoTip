package com.svalero.ecotip.repository;

import com.svalero.ecotip.model.Usuario;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
    List<Usuario> findAll();
}