package com.example.ejercicioRest.services;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService<Cliente>,ClienteEdad<String> {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente buscarPorId(Integer id) {

        Optional<Cliente> personaOptional= clienteRepository.findById(id);
        return personaOptional.get();

    }
    @Override
    public Cliente guardar(Cliente entity) {

        return clienteRepository.save(entity);

    }

    @Override
    public String clienteEdad(LocalDate fhoy, String nacim) {
        LocalDate nac = LocalDate.parse(nacim);
        Duration dd = Duration.between(nac.atStartOfDay(), fhoy.atStartOfDay());

        if (dd.toDays() < 365) {
            return "Menor a 1 año, igual a " + dd.toDays() + " dias de edad";
        } else return "La edad es de " + dd.toDays() / 365 + " años";
    }
}
