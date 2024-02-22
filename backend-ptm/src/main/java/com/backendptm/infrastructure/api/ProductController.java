package com.backendptm.infrastructure.api;

import com.backendptm.domain.model.Combination;
import com.backendptm.domain.model.Product;
import com.backendptm.domain.service.IProductServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador Rest de Producto
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * Puerto del servicio de Producto
     */
    private final IProductServicePort servicePort;

    /**
     * Construye el controlador de Producto
     * @param servicePort Puerto del servicio del Producto
     */
    public ProductController(IProductServicePort servicePort) {
        this.servicePort = servicePort;
    }

    /**
     * Devuelve el listado de productos
     * @return HttpCode Ok con lista de productos
     */
    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(servicePort.getAll());
    }

    /**
     * Devuelve un producto dado su ID
     * @param id ID del producto a buscar
     * @return HttpCode Ok si encuentra, HttpCode Not Found si no existe
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return ResponseEntity.of(servicePort.getProductById(id));
    }

    /**
     * Retorna una lista de combinaciones de nombres de productos con base al valor que llega por parametro
     * @param valueFilter Valor que sirve de filtro para la combinacion
     * @return HttpCode Ok con lista de productos de la combinacion
     */
    @GetMapping(path = "/combination/{valueFilter}")
    public ResponseEntity<List<Combination>> getCombinationProducts(@PathVariable Double valueFilter) {
        return ResponseEntity.ok(servicePort.getCombinationProducts(valueFilter));
    }

    /**
     * Crea un producto
     * @return HttpCode Created con el producto creado
     */
    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product newProduct) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicePort.save(newProduct));
    }

    /**
     * Modifica un producto
     * @return HttpCode Ok con el producto modificado
     */
    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody Product modifyProduct) {
        return ResponseEntity.of(servicePort.update(modifyProduct));
    }

    /**
     * Elimina un producto dado su id
     * @param id Id del producto a eliminar
     * @return HttpCode Ok si la elimina, HttpCode Not Found si no existe
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(servicePort.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
