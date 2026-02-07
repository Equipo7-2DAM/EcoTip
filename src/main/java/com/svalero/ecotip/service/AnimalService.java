package com.svalero.ecotip.service;

import com.svalero.ecotip.dto.AnimalDetailOutDto;
import com.svalero.ecotip.dto.AnimalInDto;
import com.svalero.ecotip.dto.AnimalOutDto;
import com.svalero.ecotip.dto.EcosistemaOutDto;
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

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private EcosistemaRepository ecosistemaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public AnimalDetailOutDto add(AnimalInDto animalInDto) throws EcosistemaNotFoundException {
        Ecosistema e = ecosistemaRepository.findById(animalInDto.getEcosistemaId()).orElseThrow(EcosistemaNotFoundException::new);
        Animal animal = modelMapper.map(animalInDto, Animal.class);
        animal.setEcosistema(e);


        Animal a = animalRepository.save(animal);
        return modelMapper.map(a, AnimalDetailOutDto.class);
    }


    public void delete( long id ) throws AnimalNotFoundException {
        Animal a = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        animalRepository.delete(a);
    }

    public List<AnimalOutDto> findAll(){

        List<Animal> animalList = animalRepository.findAll();
        List<AnimalOutDto> all =  modelMapper.map(animalList, new TypeToken<List<AnimalOutDto>>() {}.getType());

        return all;
    }

    public AnimalDetailOutDto findById(long id) throws AnimalNotFoundException{
        return animalRepository.findById(id).map(animal -> modelMapper.map(animal, AnimalDetailOutDto.class))
                .orElseThrow(AnimalNotFoundException::new);
    }

    public AnimalDetailOutDto modify(long id, AnimalInDto animalInDto) throws AnimalNotFoundException, EcosistemaNotFoundException {
        Animal a = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        Ecosistema e = ecosistemaRepository.findById(animalInDto.getEcosistemaId()).orElseThrow(EcosistemaNotFoundException::new);
        a.setNombre(animalInDto.getNombre());
        a.setEspecie(animalInDto.getEspecie());
        a.setPeso(animalInDto.getPeso());
        a.setEnPeligro(animalInDto.isEnPeligro());
        a.setFechaAvistamiento(animalInDto.getFechaAvistamiento());
        a.setApadrinado(animalInDto.isApadrinado());
        a.setEcosistema(e);

        Animal animal = animalRepository.save(a);
        return modelMapper.map(animal, AnimalDetailOutDto.class);
    }




}
