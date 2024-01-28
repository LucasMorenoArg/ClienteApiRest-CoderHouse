package com.example.ejercicioRest.services;


import java.time.LocalDate;

public interface ClienteService<E>{

    E anios(LocalDate fhoy, String nacim);
    E buscarPorID(Integer id);
    E guardar(E entity);
}
