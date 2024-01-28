package com.example.ejercicioRest.controllers;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/buscarPorId/{id}")
    public Cliente buscar(@PathVariable int id){

       return clienteService.buscarPorID(id);
    }

    @GetMapping("/fecha/{id}")
    public Cliente fecha(@PathVariable int id){

        return clienteService.anios(LocalDate.now(), clienteService.buscarPorID(id).f_nacimiento);

    }

    @GetMapping("/calcular-edad/{id}")
    public String calcularEdad(@PathVariable int id) {
        try {
            LocalDate fechaNac = LocalDate.parse(clienteService.buscarPorID(id).f_nacimiento);
            LocalDate fechaHoy = LocalDate.now();
            Duration diferencia = Duration.between(fechaNac.atStartOfDay(), fechaHoy.atStartOfDay());

            if (diferencia.toDays() < 365) {
                return "Menor a 1 año, igual a " + diferencia.toDays() + " días de edad";
            } else {
                return "La edad es " + (diferencia.toDays() / 365) + " años";
            }
        } catch (DateTimeParseException e) {
            return "Error: Formato de fecha de nacimiento inválido";
        }
    }

    @PostMapping("/guardar")
    public Cliente guardar(@RequestBody Cliente cliente){

       return clienteService.guardar(cliente);

    }
}
