package com.covit.repository;

import com.covit.domain.Cellar;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Cellar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CellarRepository extends JpaRepository<Cellar, Long> {

}
