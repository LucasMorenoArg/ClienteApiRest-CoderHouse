package com.example.ejercicioRest.services;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            Optional<Cliente> personaOptional= clienteRepository.findById(id);
            return personaOptional.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Cliente guardar(Cliente entity) {

        try {
            return clienteRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cliente borrarPorId(Integer id) {

        try {
            clienteRepository.deleteById(id);

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
