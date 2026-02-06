package com.svalero.ecotip.service;

import com.svalero.ecotip.dto.*;
import com.svalero.ecotip.exception.EcosistemaNotFoundException;
import com.svalero.ecotip.exception.UsuarioNotFoundException;
import com.svalero.ecotip.model.Ecosistema;
import com.svalero.ecotip.model.Usuario;
import com.svalero.ecotip.repository.EcosistemaRepository;
import com.svalero.ecotip.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDetailOutDto add(UsuarioInDto usuarioInDto){
        Usuario usuario = modelMapper.map(usuarioInDto, Usuario.class);

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

    public UsuarioDetailOutDto modify(long id, UsuarioInDto usuarioInDto) throws UsuarioNotFoundException {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);

        u.setNombre(usuarioInDto.getNombre());
        u.setApellidos(usuarioInDto.getApellidos());
        u.setDni(usuarioInDto.getDni());
        u.setFechaNacimiento(usuarioInDto.getFechaNacimiento());
        u.setEmail(usuarioInDto.getEmail());
        u.setColaborador(usuarioInDto.isColaborador());
        u.setIntencionesApadrinar(usuarioInDto.getIntencionesApadrinar());


        Usuario usuario = usuarioRepository.save(u);
        return modelMapper.map(usuario, UsuarioDetailOutDto.class);
    }




}
