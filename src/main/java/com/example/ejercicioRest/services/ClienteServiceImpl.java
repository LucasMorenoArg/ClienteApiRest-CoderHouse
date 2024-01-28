package com.example.ejercicioRest.services;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService<Cliente>,ClienteEdad<String> {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente buscarPorId(Integer id) {

        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            return clienteOptional.orElseThrow(() ->
                    new IllegalArgumentException("No se encontró un cliente con el ID: " + id));
        } catch (IllegalArgumentException e) {
            throw e;
        }
     }
    @Override
    public Cliente guardar(Cliente entity) {

        try {
            return clienteRepository.save(entity);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al guardar el cliente en la base de datos", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente", e);
        }
    }

    @Override
    public boolean borrarPorId(Integer id) {

        try {
            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al borrar el cliente con ID: " + id, e);
        }
    }
    @Override
    public String clienteEdad(LocalDate fhoy, String nacim) {
        LocalDate nac = LocalDate.parse(nacim);
        Duration dd = Duration.between(nac.atStartOfDay(), fhoy.atStartOfDay());

        try {
            LocalDate nacimiento = LocalDate.parse(nacim);

            Period periodo = Period.between(nacimiento, fhoy);

            if (periodo.getYears() < 1) {
                return String.format("Menor a 1 año, igual a %d meses y %d días de edad",
                        periodo.getMonths(), periodo.getDays());
            } else {
                return String.format("La edad es de %d años, %d meses y %d días",
                        periodo.getYears(), periodo.getMonths(), periodo.getDays());
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha incorrecto. Debe ser en formato 'YYYY-MM-DD'.");
        }

    }
}
