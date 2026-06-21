package com.Menager.CadastroPet.controller;

import com.Menager.CadastroPet.model.PetModel;
import com.Menager.CadastroPet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetModel cadastrarAnimal(@RequestBody PetModel petModel) {
        return petService.cadastrarPet(petModel);
    }

    @GetMapping
    public List<PetModel> listarAnimais() {
        return petService.listarAnimais();
    }

    @PutMapping("/{id}")
    public PetModel atualizarPet(@PathVariable Long id, @RequestBody PetModel petModel) {
        return petService.atualizarPet(id, petModel);
    }

    @DeleteMapping("/{id}")
    public void  deletarAnimal(@PathVariable Long id) {
        petService.deletarPet(id);
    }
}
