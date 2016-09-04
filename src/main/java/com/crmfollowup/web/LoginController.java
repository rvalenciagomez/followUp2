package com.crmfollowup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crmfollowup.domain.User;
import com.crmfollowup.repository.UserRepository;

@Controller
public class LoginController 
{
  UserRepository userRepo;
  
  
  @RequestMapping(value="login" , method=RequestMethod.GET)
  public String login ()
  {
    
    return "login";
  }
  
  @RequestMapping(value="register" , method=RequestMethod.GET)
  public String register (ModelMap model)
  {
    User user = new User();
    model.put("user",user);
    
    return "register";
  }

  @RequestMapping(value="register" , method=RequestMethod.POST)
  public String registerPost (@ModelAttribute User user, ModelMap model)
  {
    User userByEmail = userRepo.findUserByEmail(user.getEmail());
    
    if(userByEmail != null)
    {
      
      model.put("errorMsg", "Error: This user already exists with the same email address.");
      return "register";
    }
    else
    {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(user.getPassword()));
      
      userRepo.save(user);
      return "redirect:/login";
    }
    
  }


  @Autowired
  public void setUserRepo(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

}
