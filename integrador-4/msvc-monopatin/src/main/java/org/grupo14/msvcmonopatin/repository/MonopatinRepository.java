package org.grupo14.msvcmonopatin.repository;

import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonopatinRepository  extends JpaRepository<Monopatin,Long> {

}
