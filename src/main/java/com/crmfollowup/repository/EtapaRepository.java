package com.crmfollowup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crmfollowup.domain.EtapaOportunidad;


public interface EtapaRepository extends JpaRepository<EtapaOportunidad, Long> 
{
    public List<EtapaOportunidad> findByOrderByOrdenAsc();
}
