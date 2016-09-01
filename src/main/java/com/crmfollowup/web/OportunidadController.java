package com.crmfollowup.web;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crmfollowup.domain.EtapaOportunidad;
import com.crmfollowup.domain.Oportunidad;
import com.crmfollowup.domain.User;
import com.crmfollowup.repository.EtapaRepository;
import com.crmfollowup.repository.OportunidadRepository;
import com.crmfollowup.repository.UserRepository;
import com.crmfollowup.service.CSVReaderService;
import com.crmfollowup.service.SmtpMailSender;


@Controller
public class OportunidadController 
{
  private OportunidadRepository oportRepo;
  private UserRepository userRepo;
  private EtapaRepository etapaRepo;
  
 
  @Autowired
  TaskScheduler taskScheduler;

  @Autowired
  private SmtpMailSender smtpMailSender;
  
  @Autowired
  private CSVReaderService csvReader;
 

  @RequestMapping("/")
  public String rootPath()
  {
    return "redirect:/oportunidades";
  }
  
  @RequestMapping("/upload-csv")
  public @ResponseBody void uploadCSV()
  {       
    csvReader.readCSVFile();
  }
  
  
  
  @RequestMapping(value="index", method=RequestMethod.GET)
  public String indexSorterExample()
  {
    return "index";
  }
  @RequestMapping(value="oportunidades", method=RequestMethod.GET)
  public String oportunidades(ModelMap model, @AuthenticationPrincipal User user)
  {
    Oportunidad oportunidad = new Oportunidad();
//    List<Course> courses = courseRepo.findAll();
//    Page<Course> courses = courseRepo.findAll(new PageRequest(0,10));
//    List<Oportunidad> oportunidades = oportRepo.findOportunidadByUser(user);
    
    EtapaOportunidad etapa = etapaRepo.findOne((long) 1); 
    List<Oportunidad> oportunidades = oportRepo.findByUserOrderByFechaAccionAsc(user);
    
    List<EtapaOportunidad> etapasList = etapaRepo.findByOrderByOrdenAsc();
    Calendar fechaActual = Calendar.getInstance();
    List<Oportunidad> oportsFiltradas = new ArrayList<Oportunidad>();
    
    for(Oportunidad oport : oportunidades)
    {
    //convert date to datetime
      LocalDate localDate = oport.getFechaAccion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      int oportMonth = localDate.getMonthValue() -1;
      
      if(oportMonth == fechaActual.get(Calendar.MONTH))
        oportsFiltradas.add(oport); 
    }
    
    model.put("oportunidades", oportsFiltradas);
    model.put("oportunidad", oportunidad);
    model.put("etapas", etapasList);
    return "oportunidades";
    
  }
  
  @RequestMapping(value="oportunidades", method=RequestMethod.POST)
  public String oportunidadesPost (@ModelAttribute Oportunidad oportunidad, ModelMap model, @AuthenticationPrincipal User user)
  {
    oportunidad.setUser(user);
    user.getOportunidades().add(oportunidad);
    
    Oportunidad savedCourse = oportRepo.save(oportunidad);
    
    return "redirect:/editOportunidad/" + savedCourse.getId();
  }
  
  @RequestMapping(value="oportunidades/mesactual", method=RequestMethod.POST)
  public  @ResponseBody List<Oportunidad> oportunidadesMesActual ( ModelMap model, @AuthenticationPrincipal User user)
  {
	  List<Oportunidad> oportunidades = oportRepo.findByUserOrderByFechaAccionAsc(user);
	    
    

//	  List<Object> objectsList = new ArrayList List<Object>();
//	  objectsList.add(oportunidades);
//	  objectsList.add(etapasList);
	  	  
    return oportunidades;
  }
  
  @RequestMapping(value="etapaoportunidades/mesactual", method=RequestMethod.POST)
  public  @ResponseBody List<EtapaOportunidad> etapaOportunidadesMesActual ( ModelMap model, @AuthenticationPrincipal User user)
  {
	  List<EtapaOportunidad> etapasList = etapaRepo.findByOrderByOrdenAsc();
	 
    return etapasList;
  }
  
  @RequestMapping(value="findOportunidad/{oportId}", method=RequestMethod.POST)
  public  @ResponseBody Oportunidad findOportunidadPost (@PathVariable("oportId") Long oportId, ModelMap model, @AuthenticationPrincipal User user)
  {
	  Oportunidad oportunidad = oportRepo.findOne(oportId);
	 
    return oportunidad;
  }
  
  
  @RequestMapping(value="oportunidades/{oportId}", method=RequestMethod.POST)
  public  @ResponseBody Oportunidad oportunidadesPost (@PathVariable("oportId") Long oportId, 
      @RequestParam Long etapaId, ModelMap model)
  {
    Oportunidad oportunidad = oportRepo.findOne(oportId);
    EtapaOportunidad etapaOportunidad = etapaRepo.findOne(etapaId);
    
    oportunidad.setEtapaOportunidad(etapaOportunidad);
    oportRepo.save(oportunidad);
    return oportunidad;
  }
  
  
  
  @RequestMapping(value="editOportunidad/{oportId}", method=RequestMethod.GET)
  public String editOportunidades(ModelMap model, @PathVariable("oportId") Long oportId , @AuthenticationPrincipal User user)
  {
    Oportunidad oportunidad = oportRepo.findOne(oportId);
    List<EtapaOportunidad> etapas = etapaRepo.findAll();
    
    if(oportunidad == null)
    {
      return "redirect:/oportunidades";
    }
    
    model.put("oportunidad", oportunidad);
    
    List<User> clientes = userRepo.findAll();
    
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
      
      
      model.put("clientes", clientesArr);
      
      
      model.put("etapas",etapas);
    }
   
    return "editOportunidad";
  }
  
  @RequestMapping(value="editOportunidad/{oportId}", method=RequestMethod.POST)
  public String editOportunidadesPost(@ModelAttribute Oportunidad oportunidad, ModelMap model, 
      @PathVariable("oportId") Long oportId , @AuthenticationPrincipal User user) throws ParseException
  {
    User cliente = userRepo.findOne(oportunidad.getCliente().getId());
    Oportunidad oportunidadSaved = oportRepo.findOne(oportId);

    
    oportunidad.setId(oportId);
    oportunidad.setUser(oportunidadSaved.getUser());
    oportunidad.setCliente(cliente);
    oportRepo.save(oportunidad);
    
//    String dateString = "2016-08-14 10:60";
//    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//    Date startTime = dt.parse(dateString);
    Date startTime = oportunidad.getFechaAccion();
    
    taskScheduler.schedule(new Runnable() {
      public void run() {
        try {
          task(startTime, user, oportunidad);
        } catch (MessagingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }, startTime);

    
    return "redirect:/oportunidades";
  }
  
  
  @RequestMapping(value="editOportunidad/busquedaCliente", method=RequestMethod.POST)
  public @ResponseBody String[] createSectionPost(@RequestParam Long oportunidadId, @RequestParam String searchCliente, 
      ModelMap model , @AuthenticationPrincipal User user)
  {
    Oportunidad oportunidad = oportRepo.findOne(oportunidadId);
    List<User> clientes = userRepo.findByEmailContaining(searchCliente);
     
    String[] clientesArray;
    
   if(clientes != null)
   {
      
     clientesArray = new String[clientes.size()];
     clientes.toArray(clientesArray); // fill the array
     
     return clientesArray;
   }
    
    
//        
//    if(cliente != null)
//    {
//      oportunidad.setUser(cliente);
//      oportRepo.save(oportunidad);
      
      
//      for(User user : clientes)
//      {
//        nombreClientes.add(user.getEmail());
//      }
      
//      List<User> clientes = userRepo.findAll();
//      String[] nombreClientes = new String[]{"admin","user","rob"};
//      model.put("clientes", nombreClientes);
//
//    }
//    else
//    {
//      model.put("message", "Ese cliente no existe");
//    }
    
    return null;
  }
  
  public void task (Date fechaMensaje, User user, Oportunidad oportunidad) throws MessagingException
  {
    smtpMailSender.send(user.getEmail(),
        "Recordatorio de Oportunidad: "  + oportunidad.getAsunto() + "a las: " + fechaMensaje, 
        "Le recuerdo que tiene una Oportunidad: "  + oportunidad.getAsunto() +" , del cliente: "+ oportunidad.getCliente().getNombre() + " , a las: " + fechaMensaje);
  }
  
  @Autowired
  public void setOportRepo(OportunidadRepository oportRepo) {
    this.oportRepo = oportRepo;
  }
  
  @Autowired
  public void setUserRepo(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Autowired
  public void setEtapaRepo(EtapaRepository etapaRepo) {
    this.etapaRepo = etapaRepo;
  }

  
  
}

