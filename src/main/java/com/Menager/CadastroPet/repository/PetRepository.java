package com.Menager.CadastroPet.repository;

import com.Menager.CadastroPet.model.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetModel, Long> {
}
