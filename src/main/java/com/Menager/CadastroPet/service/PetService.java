package com.Menager.CadastroPet.service;

import com.Menager.CadastroPet.model.PetModel;
import com.Menager.CadastroPet.repository.PetRepository;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private  PetRepository petRepository;

    public PetModel cadastrarPet(PetModel petModel){
        return petRepository.save(petModel);
    }

    public List<PetModel> listarAnimais() {
        return petRepository.findAll();
    }

    public PetModel buscarPetPorId(Long id) {
        PetModel pet = petRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Id não encontrado"));
        return pet;
    }

    public PetModel atualizarPet(Long id, PetModel petAtualizado) {
         PetModel petDesatualizado = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        petDesatualizado.setNome(petAtualizado.getNome());
        petDesatualizado.setTipo(petAtualizado.getTipo());
        petDesatualizado.setChegada(petAtualizado.getChegada());
        petDesatualizado.setSaida(petAtualizado.getSaida());

        return petRepository.save(petDesatualizado);
    }

    public void deletarPet(Long id) {
        petRepository.deleteById(id);

    }



}
