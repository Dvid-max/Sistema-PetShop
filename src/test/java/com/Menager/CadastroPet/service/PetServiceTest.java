package com.Menager.CadastroPet.service;

import com.Menager.CadastroPet.model.PetModel;
import com.Menager.CadastroPet.repository.PetRepository;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any; // 1. IMPORTAÇÃO CORRIGIDA (Mockito)
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    private PetModel pet;
    private PetModel pet2;


    @BeforeEach
    void setUp() {
        pet = new PetModel (
                1L,
                "CACHORRO",
                "Spike",
                1,
                LocalDateTime.of(2026, 6, 24, 15, 30),
                LocalDateTime.of(2026, 6, 24, 18, 0)
        );

        pet2 =new PetModel(
                2L,
                "GATP",
                "Mimi",
                2,
                LocalDateTime.of(2026, 6, 24, 15, 30),
                LocalDateTime.of(2026, 6, 24, 18, 0)

        );
    }

    @Test
    void deveCadastrarPetComSucesso(){
        when(petRepository.save(any(PetModel.class))).thenReturn(pet);

        PetModel resultado = petService.cadastrarPet(pet);

        assertEquals("Spike", resultado.getNome());
        verify(petRepository).save(any(PetModel.class));
    }

    @Test
    void deveRetornarListaDeAnimais() {
      when(petRepository.findAll()).thenReturn(List.of(pet, pet2));

      List<PetModel> resultado = petService.listarAnimais();

      assertEquals(2, resultado.size());
      verify(petRepository).findAll();

    }

    @Test
    void deveRetornarUmaListaVaziaQuandoNãoHouverAnimais() {
        List<PetModel> animais;
        when(petRepository.findAll()).thenReturn(List.of());

        List<PetModel> resultado = petService.listarAnimais();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRetornarListaDeAnimaisQuandoHouverCadastros() {
        List<PetModel> animais = List.of(pet, pet2);

        when(petRepository.findAll()).thenReturn(animais);

        List<PetModel> resultado = petService.listarAnimais();

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertEquals("Spike", resultado.get(0).getNome());
        assertEquals("Mimi", resultado.get(1).getNome());

    }

    @Test
    void deveRetornarPetQuandoBuscarPorId() {

        // ARRANGE — prepara o cenário
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        // ACT — executa o que quer testar
        PetModel resultado = petService.buscarPetPorId(pet.getId());

        // ASSERT — verifica o resultado
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Spike", resultado.getNome());
    }

}
