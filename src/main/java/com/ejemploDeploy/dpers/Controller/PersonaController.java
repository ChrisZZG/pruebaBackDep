
package com.ejemploDeploy.dpers.Controller;

import com.ejemploDeploy.dpers.Dto.dtoPersona;
import com.ejemploDeploy.dpers.Entity.Persona;
import com.ejemploDeploy.dpers.Service.ServicePersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "https://registropruebadeploy.firebaseapp.com")
public class PersonaController {
    
    @Autowired
    ServicePersona servicePersona;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = servicePersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!servicePersona.existsById(id)){
            return new ResponseEntity(new Mensaje("Ese Id no existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = servicePersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = new Persona(dtopersona.getNombre(), dtopersona.getApellido(), dtopersona.getDescripcion());
        servicePersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona Creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
         if(!servicePersona.existsById(id)){
            return new ResponseEntity(new Mensaje("Ese Id no existe"), HttpStatus.NOT_FOUND);
        }
         if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }
         Persona persona = servicePersona.getOne(id).get();
         persona.setNombre(dtopersona.getNombre());
         persona.setApellido(dtopersona.getApellido());
         persona.setDescripcion(dtopersona.getDescripcion());
         
         servicePersona.save(persona);
         return new ResponseEntity(new Mensaje("Persona Actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
         if(!servicePersona.existsById(id)){
            return new ResponseEntity(new Mensaje("Ese Id no existe"), HttpStatus.NOT_FOUND);
        }
         servicePersona.delete(id);
         return new ResponseEntity(new Mensaje("Persona Eliminada"), HttpStatus.OK);
    }
}
