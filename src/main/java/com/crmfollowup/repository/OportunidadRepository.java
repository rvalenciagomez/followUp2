package com.crmfollowup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crmfollowup.domain.EtapaOportunidad;
import com.crmfollowup.domain.Oportunidad;
import com.crmfollowup.domain.User;

public interface OportunidadRepository extends JpaRepository<Oportunidad, Long> 
{
    
//  public List<Course> FindAll();
   @Query("select o from Oportunidad o where o.user.id = ?#{principal.id} or 1=?#{hasRole('ROLE_ADMIN') ? 1 : 0 }")
  public List<Oportunidad> findOportunidadByUser(User user);
   
   
//   @Query("select o from Oportunidad o where o.etapa.id = ?#{EtapaOportunidad.id}")
  public List<Oportunidad> findOportunidadByEtapaOportunidadAndUser(EtapaOportunidad etapa , User user); 
  
  public List<Oportunidad> findByUserOrderByFechaAccionAsc(User user);
  
  
}
