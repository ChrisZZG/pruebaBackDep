
package com.ejemploDeploy.dpers.Service;

import com.ejemploDeploy.dpers.Entity.Persona;
import com.ejemploDeploy.dpers.Repository.IRepositoryPersona;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicePersona {
    
    @Autowired
    IRepositoryPersona iRepositoryPersona;
    
    public List<Persona> list(){
        return iRepositoryPersona.findAll();
    }
    public Optional<Persona> getOne(int id){
        return iRepositoryPersona.findById(id);
    }
    public Optional<Persona> getByNombre(String nombre){
        return iRepositoryPersona.findByNombre(nombre);
    }
    public void save(Persona persona){
        iRepositoryPersona.save(persona);
    }
    public void delete(int id){
        iRepositoryPersona.deleteById(id);
    }
    public boolean existsById(int id){
        return iRepositoryPersona.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return iRepositoryPersona.existsByNombre(nombre);
    }
}
