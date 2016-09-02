package com.crmfollowup.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crmfollowup.domain.Oportunidad;
import com.crmfollowup.repository.OportunidadRepository;

@Service
public class FiltersForOportunidades 
{
	@Autowired
	private OportunidadRepository oportRepo;
	
	public List<Oportunidad> recepcionFiltro(String filtro, List<Oportunidad> oportunidades)
	{
		Oportunidad oportunidad = new Oportunidad();	  
		
		if(filtro.equals("MesActual"))
	    {
	    	oportunidades = filterByMonth(oportunidades);
	    }
		
		if(filtro.equals("SemanaActual"))
	    {
	    	oportunidades = filterByThisWeek(oportunidades);
	    }
		
		if(filtro.equals("SiguienteSemana"))
	    {
	    	oportunidades = filterByNextWeek(oportunidades);
	    }
		
		return oportunidades;
	}
	
	
	//Filtro por mes actual
	public List<Oportunidad> filterByMonth(List<Oportunidad> oportunidades)
	{
		Calendar fechaActual = Calendar.getInstance();
	    List<Oportunidad> oportsFiltradas = new ArrayList<Oportunidad>();
	    
	    for(Oportunidad oport : oportunidades)
	    {
	    //convert date to datetime
	      LocalDate oportDate = oport.getFechaAccion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	      int oportMonth = oportDate.getMonthValue() -1;
	      
	      if(oportMonth == fechaActual.get(Calendar.MONTH))
	        oportsFiltradas.add(oport); 
	    }
		
		return oportsFiltradas;
	}
	
	//Filtro por semana a actual
	public List<Oportunidad> filterByThisWeek(List<Oportunidad> oportunidades)
	{
		Calendar fechaActual = Calendar.getInstance();
	    List<Oportunidad> oportsFiltradas = new ArrayList<Oportunidad>();
	    
	    for(Oportunidad oport : oportunidades)
	    {
	    //convert date to datetime
	      LocalDate oportDate = oport.getFechaAccion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	   // Or use a specific locale, or configure your own rules
	      WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
	      int oportWeek = oportDate.get(weekFields.weekOfWeekBasedYear());
	      
	      if(oportWeek == fechaActual.get(Calendar.WEEK_OF_YEAR))
	        oportsFiltradas.add(oport); 
	    }
		
		return oportsFiltradas;
	}
	
	//Filtro por siguiente semana a actual
	public List<Oportunidad> filterByNextWeek(List<Oportunidad> oportunidades)
	{
		Calendar fechaActual = Calendar.getInstance();
	    List<Oportunidad> oportsFiltradas = new ArrayList<Oportunidad>();
	    
	    for(Oportunidad oport : oportunidades)
	    {
	    //convert date to datetime
	      LocalDate oportDate = oport.getFechaAccion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	   // Or use a specific locale, or configure your own rules
	      WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
	      int oportNextWeek = oportDate.get(weekFields.weekOfWeekBasedYear());
	      
	      if(oportNextWeek == (fechaActual.get(Calendar.WEEK_OF_YEAR) + 1))
	        oportsFiltradas.add(oport); 
	    }
		
		return oportsFiltradas;
	}
}
