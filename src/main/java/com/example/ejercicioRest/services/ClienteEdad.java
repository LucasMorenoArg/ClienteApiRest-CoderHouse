package com.example.ejercicioRest.services;

import java.time.LocalDate;

public interface ClienteEdad<E> {

    E clienteEdad(LocalDate fhoy, String nacim);
}
