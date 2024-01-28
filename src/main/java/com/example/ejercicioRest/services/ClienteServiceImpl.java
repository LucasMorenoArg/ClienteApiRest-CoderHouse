package com.example.ejercicioRest.services;

import com.example.ejercicioRest.entities.Cliente;
import com.example.ejercicioRest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Cliente anios(LocalDate fhoy, String nacim) {
        LocalDate nac = LocalDate.parse(nacim);
        Duration dd = Duration.between(nac.atStartOfDay(), fhoy.atStartOfDay());

        if (dd.toDays() < 365) {
            System.out.println("Menor a 1 año, igual a " + dd.toDays() + " dias de edad");
        } else System.out.println("La edad es " + dd.toDays() / 365 + " años");
        return null;
    }


    @Override
    public Cliente buscarPorID(Integer id) {
        Optional<Cliente> personaOptional= clienteRepository.findById(id);

        return personaOptional.get();

    }
    @Override
    public Cliente guardar(Cliente entity) {


        return clienteRepository.save(entity);

    }
}
