package com.backendptm.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* DTO de un Producto para solicitudes de guardado
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

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
}
