package com.gestion.empleados.demo.Controlador;

import com.gestion.empleados.demo.modelo.Empleado;
import com.gestion.empleados.demo.modelo.Excepciones.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestion.empleados.demo.repositorio.EmpleadoRepositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoRepositorio repositorio;
    //Obtener empleado

    @GetMapping("/empleados")
    public List<Empleado> listarTodosLosempleados(){

        return repositorio.findAll();
    }
    //guardar empleados requestBody en formato json sirve
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){

        return repositorio.save(empleado);
    }
    //Obtener usuario por ID
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadosPorId(@PathVariable Long id){

        Empleado empleado=repositorio.findById(id).orElseThrow(()->new RuntimeException("No existe usuario con el ID"+id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado detallesEmpleado){
        System.out.println("Here----------------------------->");
        Empleado empleado=repositorio.findById(id).orElseThrow(()->new ResourceNotFoundException("No existe el empleado"));
        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());
        Empleado empleadoActualizado=repositorio.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado=repositorio.findById(id).orElseThrow(()->new ResourceNotFoundException("No existe el empleado con el ID:"+id));
        repositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return  ResponseEntity.ok(respuesta);
    }
}
