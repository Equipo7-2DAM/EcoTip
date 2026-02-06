package com.svalero.ecotip.service;

import com.svalero.ecotip.dto.*;
import com.svalero.ecotip.exception.AnimalNotFoundException;
import com.svalero.ecotip.exception.EcosistemaNotFoundException;
import com.svalero.ecotip.model.Animal;
import com.svalero.ecotip.model.Ecosistema;
import com.svalero.ecotip.repository.AnimalRepository;
import com.svalero.ecotip.repository.EcosistemaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.spec.ECPoint;
import java.util.List;


@Service
public class EcosistemaService {

    @Autowired
    private EcosistemaRepository ecosistemaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EcosistemaDetailOutDto add(EcosistemaInDto ecosistemaInDto){
        Ecosistema ecosistema = modelMapper.map(ecosistemaInDto, Ecosistema.class);

        Ecosistema e = ecosistemaRepository.save(ecosistema);
        return modelMapper.map(e, EcosistemaDetailOutDto.class);
    }


    public void delete( long id ) throws EcosistemaNotFoundException {
        Ecosistema ecosistema = ecosistemaRepository.findById(id)
                .orElseThrow(EcosistemaNotFoundException::new);
        ecosistemaRepository.delete(ecosistema);
    }

    public List<EcosistemaOutDto> findAll(){

        List<Ecosistema> ecosistemaList = ecosistemaRepository.findAll();
        List<EcosistemaOutDto> all =  modelMapper.map(ecosistemaList, new TypeToken<List<EcosistemaOutDto>>() {}.getType());

        return all;
    }

    public EcosistemaDetailOutDto findById(long id) throws EcosistemaNotFoundException{
        return ecosistemaRepository.findById(id).map(ecosistema -> modelMapper.map(ecosistema, EcosistemaDetailOutDto.class))
                .orElseThrow(EcosistemaNotFoundException::new);
    }

    public EcosistemaDetailOutDto modify(long id, EcosistemaInDto ecosistemaInDto) throws EcosistemaNotFoundException {
        Ecosistema e = ecosistemaRepository.findById(id)
                .orElseThrow(EcosistemaNotFoundException::new);

        e.setNombre(ecosistemaInDto.getNombre());
        e.setDescripcion(ecosistemaInDto.getDescripcion());
        e.setTemperaturaMedia(ecosistemaInDto.getTemperaturaMedia());
        e.setActive(ecosistemaInDto.isActive());
        e.setCreatedAt(ecosistemaInDto.getCreatedAt());

        Ecosistema ecosistema = ecosistemaRepository.save(e);
        return modelMapper.map(ecosistema, EcosistemaDetailOutDto.class);
    }




}
