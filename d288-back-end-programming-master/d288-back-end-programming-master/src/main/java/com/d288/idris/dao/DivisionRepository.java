package com.d288.idris.dao;

import com.d288.idris.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface DivisionRepository extends JpaRepository<Division, Long> {
}
