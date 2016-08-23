package com.crmfollowup.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.crmfollowup.domain.Authorities;
import com.crmfollowup.domain.User;

public class CustomUserDetails extends User implements UserDetails
{
  private static final long serialVersionUID = 344094893169692400L;

  public CustomUserDetails () {}
 
  public CustomUserDetails(User user) { 
    super (user);
  }

  @Override
  public Set<Authorities> getAuthorities() 
  {  
    return super.getAuthorities();
//    return AuthorityUtils.createAuthorityList("ROLE_USER,ROLE_ADMIN");
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

}
