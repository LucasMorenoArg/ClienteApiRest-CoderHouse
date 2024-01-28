package com.example.ejercicioRest.controllers;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/buscarPorId/{id}")
    public Cliente buscar(@PathVariable int id){

       return clienteService.buscarPorId(id);
    }

    @GetMapping("/calcular-edad/{id}")
    public String calcularEdad(@PathVariable int id) {

        try {
            LocalDate fechaHoy = LocalDate.now();
            return clienteService.clienteEdad(fechaHoy, clienteService.buscarPorId(id).f_nacimiento);
        } catch (DateTimeParseException e) {
            return "Error: Formato de fecha de nacimiento inválido";
        }
    }

    @PostMapping("/guardar")
    public Cliente guardar(@RequestBody Cliente cliente){

       return clienteService.guardar(cliente);

    }
}
