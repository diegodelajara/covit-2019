package com.covit.repository;

import com.covit.domain.Condominium;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Condominium entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CondominiumRepository extends JpaRepository<Condominium, Long> {

}
