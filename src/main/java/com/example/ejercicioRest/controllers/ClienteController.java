package com.example.ejercicioRest.controllers;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.services.ClienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
public class ClienteController {


    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/buscarXId/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {

        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(clienteService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.No se encontró registro.\"}");
        }
    }

    @GetMapping("/calcular-edad/{id}")
    public ResponseEntity<?> calcularEdad(@PathVariable int id) {

        try {
            LocalDate fechaHoy = LocalDate.now();
            return ResponseEntity.status(HttpStatus.OK).
                    body(clienteService.clienteEdad(fechaHoy, clienteService.buscarPorId(id).f_nacimiento));
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.Verificar datos.\"}");

        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente) {

        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body("Cliente creado correctamente: " + "\n" + clienteService.guardar(cliente));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.No se creó o actualizó el registro.\"}");
        }

    }
    @DeleteMapping("/")
    public ResponseEntity<?> borrarPorId(@PathVariable int id){

        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(clienteService.borrarPorId(id) + "Operación finalizada");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.No se realizo el borrado.\"}");
         }
       }
    }

