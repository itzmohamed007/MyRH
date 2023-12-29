package com.myrh.repositories;

import com.myrh.Embeddables.ApplyingId;
import com.myrh.models.Applying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyingRepository extends JpaRepository<Applying, ApplyingId> {

}
