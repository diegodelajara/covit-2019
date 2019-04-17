package com.covit.repository;

import com.covit.domain.Egress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Egress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EgressRepository extends JpaRepository<Egress, Long> {

}
