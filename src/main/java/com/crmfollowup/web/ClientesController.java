package com.crmfollowup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crmfollowup.domain.User;
import com.crmfollowup.repository.UserRepository;

@Controller
public class ClientesController 
{
  private UserRepository userRepo;
  
  
//  @RequestMapping(value="prueba", method=RequestMethod.GET)
//  public String prueba(ModelMap model, @AuthenticationPrincipal User user)
//  {     
  
//    User clienteNuevo = new User();
//    
//    List<User> clientes = userRepo.findAll();
//  
//    if(clientes != null)
//    {
//      User[] clientesArr = new User[clientes.size()];
//
//      int x = 0;
//      for(User cliente : clientes) 
//      {
//        clientesArr[x] = new User();
//        clientesArr[x].setEmail(cliente.getEmail());
//        clientesArr[x].setNombre(cliente.getNombre());
//        clientesArr[x].setId(cliente.getId());
//        clientesArr[x].setTelefono(cliente.getTelefono());
//        clientesArr[x].setMovil(cliente.getMovil());
//        x++;
//      }
//      
//      
//      model.put("clientes", clientesArr);
//    }
//    
//    model.put("cliente", clienteNuevo);
//    
//    return "prueba";
//  }
  
  @RequestMapping(value="clientes", method=RequestMethod.GET)
  public String courses(String searchTerm, ModelMap model, @AuthenticationPrincipal User user)
  {
    User clienteNuevo = new User(); 
    
    List<User> clientes = userRepo.findByEsCliente();
    
    if(searchTerm != null)
    {
      clientes = userRepo.findByNombreContainingOrderByNombreAsc(searchTerm);      
    }
    
    cambiarListOfClientesToArray(model, clientes);
    
    model.put("clientes", clientes);
    
    model.put("cliente", clienteNuevo);
    
    return "clientes";
    
  }
  
  @RequestMapping(value="clientes", method=RequestMethod.POST)
  public String clientesPost (@ModelAttribute User cliente, ModelMap model, @AuthenticationPrincipal User user)
  {
    cliente.setEsCliente(true);
    User savedCliente = userRepo.save(cliente);
    
    return "redirect:/editCliente/" + savedCliente.getId();
  }
  
  @RequestMapping(value="editCliente/{clienteId}", method=RequestMethod.GET)
  public String editClienteGet (@PathVariable("clienteId") Long clienteId , @ModelAttribute String message, ModelMap model)
  {
    User clienteNuevo = userRepo.findOne(clienteId);
    if(clienteNuevo == null)
    {
      return "redirect:/clientes";
    }
    
    List<User> clientes = userRepo.findAll();
    
    cambiarListOfClientesToArray(model, clientes);
    
    model.put("cliente", clienteNuevo);
    model.put("message",message);

    return "editCliente";
  }


  @RequestMapping(value="editCliente/{clienteId}", method=RequestMethod.POST)
  public String editClientePost (@ModelAttribute User cliente , @PathVariable("clienteId") Long clienteId , ModelMap model)
  {
    User clienteSaved = userRepo.findOne(clienteId);
//    clienteSaved.setEmail(cliente.getEmail());
    cliente.setId(clienteId);
    
    //  Poner en null empresa si el cliente es EMPRESA
    if(cliente.getEsEmpresa() == true)
      cliente.setEmpresa(null);
    
    //    Revisar que exista empresa una empresa en cliente
    if(cliente.getEmpresa() !=null)
    {
      // Revisar que el id de empresa sea diferente de null
      if(cliente.getEmpresa().getId() == null)
        cliente.setEmpresa(null);
    }
    cliente.setEsCliente(clienteSaved.getEsCliente());
    
    userRepo.save(cliente);
    
    model.addAttribute("message", "Los datos fueron actualizados correctamente");
    
    return "redirect:/clientes";
  }
  
  @RequestMapping(value="editcliente/{clienteId}/deleteCliente", method=RequestMethod.POST)
  public String deleteCliente (@PathVariable("clienteId") Long clienteId, @AuthenticationPrincipal User user)
  {
    User cliente = userRepo.findOne(clienteId);
    
    userRepo.delete(cliente);
    
    return "redirect:/";
  }
  
  /**
   * @param model
   * @param clientes
   * cambiar una lista de clientes a Array y enviar una lista al modelo
   */
  private void cambiarListOfClientesToArray(ModelMap model, List<User> clientes) {
    if(clientes != null)
    {
       
      User[] clientesArr = new User[clientes.size()];

      int x = 0;
      for(User cliente : clientes) 
      {
        clientesArr[x] = new User();
        clientesArr[x].setEmail(cliente.getEmail());
        clientesArr[x].setNombre(cliente.getNombre());
        clientesArr[x].setId(cliente.getId());
        clientesArr[x].setTelefono(cliente.getTelefono());
        clientesArr[x].setMovil(cliente.getMovil());
        x++;
      }
      
      
    model.put("clientesArr", clientesArr);
    }
  }
  
  
  @Autowired
  public void setUserRepo(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  
  
}

