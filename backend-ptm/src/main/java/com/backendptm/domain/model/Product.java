package com.backendptm.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de un Producto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    /**
     * Id del producto
     */
    private Integer id;

    /**
     * Nombre del producto
     */
    private String name;

    /**
     * Descripcion del producto
     */
    private String description;

    /**
     * Precio del producto
     */
    private Double value;

    /**
     * Unidades en existencia del producto
     */
    private Integer stock;

    /**
     * Valor total del inventario del producto
     */
    private Double totalValue;
}
