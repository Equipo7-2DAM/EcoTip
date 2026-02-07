package com.svalero.ecotip.service;

import com.svalero.ecotip.dto.*;
import com.svalero.ecotip.exception.AnimalNotFoundException;
import com.svalero.ecotip.exception.EcosistemaNotFoundException;
import com.svalero.ecotip.exception.UsuarioNotFoundException;
import com.svalero.ecotip.model.Animal;
import com.svalero.ecotip.model.Ecosistema;
import com.svalero.ecotip.model.Usuario;
import com.svalero.ecotip.repository.AnimalRepository;
import com.svalero.ecotip.repository.EcosistemaRepository;
import com.svalero.ecotip.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDetailOutDto add(UsuarioInDto dto) throws AnimalNotFoundException {

        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(AnimalNotFoundException::new);

        Usuario usuario = modelMapper.map(dto, Usuario.class);

        usuario.getAnimales().add(animal);
        animal.getUsuarios().add(usuario);

        Usuario u = usuarioRepository.save(usuario);

        return modelMapper.map(u, UsuarioDetailOutDto.class);
    }



    public void delete( long id ) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        usuarioRepository.delete(usuario);
    }

    public List<UsuarioOutDto> findAll(){

        List<Usuario> usuarioList = usuarioRepository.findAll();
        List<UsuarioOutDto> all =  modelMapper.map(usuarioList, new TypeToken<List<UsuarioOutDto>>() {}.getType());

        return all;
    }

    public UsuarioDetailOutDto findById(long id) throws UsuarioNotFoundException{
        return usuarioRepository.findById(id).map(usuario -> modelMapper.map(usuario, UsuarioDetailOutDto.class))
                .orElseThrow(UsuarioNotFoundException::new);
    }

    public UsuarioDetailOutDto modify(long id, UsuarioInDto usuarioInDto) throws UsuarioNotFoundException, AnimalNotFoundException {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        Animal a = animalRepository.findById(usuarioInDto.getAnimalId()).orElseThrow(AnimalNotFoundException::new);

        u.setNombre(usuarioInDto.getNombre());
        u.setApellidos(usuarioInDto.getApellidos());
        u.setDni(usuarioInDto.getDni());
        u.setFechaNacimiento(usuarioInDto.getFechaNacimiento());
        u.setEmail(usuarioInDto.getEmail());
        u.setColaborador(usuarioInDto.isColaborador());
        u.setDonativo(usuarioInDto.getDonativo());

        if (!u.getAnimales().contains(a)) {
            u.getAnimales().add(a);
            a.getUsuarios().add(u);
        }


        Usuario usuario = usuarioRepository.save(u);
        return modelMapper.map(usuario, UsuarioDetailOutDto.class);
    }




}
