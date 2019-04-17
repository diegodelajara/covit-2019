package com.covit.repository;

import com.covit.domain.PenaltyFeeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PenaltyFeeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PenaltyFeeTypeRepository extends JpaRepository<PenaltyFeeType, Long> {

}
