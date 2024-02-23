package com.backendptm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Modelo de una Combinacion de productos
 */
@Getter
@Setter
@AllArgsConstructor
public class Combination {

    /**
     * Nombres de los productos
     */
    private List<String> names;

    /**
     * Suma de los valores de los productos que estan en la lista names
     */
    private Double total;
}
