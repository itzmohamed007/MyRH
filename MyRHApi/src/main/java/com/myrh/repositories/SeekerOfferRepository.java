package com.myrh.repositories;

import com.myrh.Embeddables.SeekerOfferId;
import com.myrh.models.SeekerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeekerOfferRepository extends JpaRepository<SeekerOffer, SeekerOfferId> {

}
