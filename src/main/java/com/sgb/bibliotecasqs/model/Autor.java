package com.sgb.bibliotecasqs.model;

public record Autor(
        String id,
        String nombre,
        String nacionalidad,
        String fechaNacimiento,
        String fechaDefuncion) {
}
