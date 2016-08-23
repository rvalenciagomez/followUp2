package com.crmfollowup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crmfollowup.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
  public User findUserByEmail(String email);

//  @Param("users") User User
//  @Param("esEmpresa")
  @Query("select u from User u where u.esCliente = true")
  public List<User> findByEsCliente();
  
//  @Query("select u from User u where u.email *= email")
//  public List<User> findUsersBySearchCliente(String email);
//  
//  @Query("select u from User u where u.email like %?1")
  public List<User> findByEmailContaining(String email);

//  @Query("select u from User u where u.nombre = %term%")
  public List<User> findByNombreContainingOrderByNombreAsc(String term);
  
}
