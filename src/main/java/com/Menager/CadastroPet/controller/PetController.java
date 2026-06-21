package com.Menager.CadastroPet.controller;

import com.Menager.CadastroPet.model.PetModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class PetController {

    @PostMapping
    public PetModel cadastrarAnimal(@RequestBody PetModel petModel) {
        return petModel;
    }

    @GetMapping
    public String listarAnimais() {
        return "Lista";
    }

    @PutMapping("/{id}")
    public PetModel atualizarPet(@PathVariable Long id, @RequestBody PetModel petModel) {
        return  petModel;
    }

    @DeleteMapping("/{id}")
    public void  deletarAnimal(@PathVariable Long id) {
        System.out.println("Animal deletado");
    }
}
