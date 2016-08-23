package com.crmfollowup.service;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crmfollowup.domain.User;
import com.crmfollowup.repository.UserRepository;
import com.opencsv.CSVReader;

@Component
public class CSVReaderService {

//  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  UserRepository userRepo;
  
    public void readCSVFile() {

        String csvFile = "/Users/rvalencia/Downloads/pruebaImporClientes.csv";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] header = reader.readNext();
            
            String[] lineObject;
            while ((lineObject = reader.readNext()) != null) {
//                System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
              
              // Si el Id es invalido
              Long idFileObject = (long) 0;
              User userFromBD = null;
              if(lineObject[0].length() != 0)
              {
                idFileObject = Long.valueOf(lineObject[0]);
                
                userFromBD =userRepo.findOne(idFileObject);
              }
              
              // el user no existe
              if(userFromBD == null)
              {
                User user = new User();
                user.setId(idFileObject);
                user.setNombre(lineObject[1]);
                user.setEmail(lineObject[2]);
                user.setTelefono(lineObject[3]);
                user.setMovil(lineObject[4]);
                user.setRFC(lineObject[5]);
                user.setEsCliente(true);
                userRepo.save(user);
              } 
              else // si el user existe
              {
                userFromBD.setNombre(lineObject[1]);
                userFromBD.setEmail(lineObject[2]);
                userFromBD.setTelefono(lineObject[3]);
                userFromBD.setMovil(lineObject[4]);
                userFromBD.setRFC(lineObject[5]);
                userFromBD.setEsCliente(true);
                userRepo.save(userFromBD);
              }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}