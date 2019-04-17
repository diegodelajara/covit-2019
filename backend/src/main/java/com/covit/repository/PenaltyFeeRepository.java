package com.covit.repository;

import com.covit.domain.PenaltyFee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PenaltyFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PenaltyFeeRepository extends JpaRepository<PenaltyFee, Long> {

}
