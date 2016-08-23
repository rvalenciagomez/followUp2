package com.crmfollowup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crmfollowup.domain.User;
import com.crmfollowup.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

  @Autowired
  private UserRepository userRepo;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
  {
    User user = userRepo.findUserByEmail(username);

    if(username == null)
      throw new UsernameNotFoundException("User with username: " + username + " not found");
    else
      return new CustomUserDetails(user);
  }

}
