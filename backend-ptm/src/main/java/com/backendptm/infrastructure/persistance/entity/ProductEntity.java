package com.backendptm.infrastructure.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class ProductEntity {

    /**
     * Id del producto
     */
    @Id
    private Integer id;

    /**
     * Nombre del producto
     */
    @Column(name = "nombre")
    private String name;

    /**
     * Descripcion del producto
     */
    @Column(name = "descripcion")
    private String description;

    /**
     * Precio del producto
     */
    @Column(name = "precio")
    private Double value;

    /**
     * Unidades en existencia del producto
     */
    private Integer stock;
}
