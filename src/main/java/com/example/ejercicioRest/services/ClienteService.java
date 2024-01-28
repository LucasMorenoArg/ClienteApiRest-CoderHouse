package com.example.ejercicioRest.services;


import java.time.LocalDate;

public interface ClienteService<E>{

    E buscarPorId(Integer id);
    E guardar(E entity);
    E borrarPorId(Integer id);
}
