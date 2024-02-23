package com.backendptm.infrastructure.api;

import com.backendptm.application.dto.ProductRequestDto;
import com.backendptm.domain.model.Combination;
import com.backendptm.domain.model.Product;
import com.backendptm.domain.service.IProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
     * @return ResponseEntity Ok con lista de productos
     */
    @Operation(summary = "Devuelve el listado de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo los productos encontrados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)),
                            mediaType = "application/json")),
    })
    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(servicePort.getAll());
    }

    /**
     * Devuelve un producto dado su ID
     * @param id ID del producto a buscar
     * @return ResponseEntity Ok si encuentra, ResponseEntity Not Found si no existe
     */
    @Operation(summary = "Obtiene un producto dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)})
    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return ResponseEntity.of(servicePort.getProductById(id));
    }

    /**
     * Retorna una lista de combinaciones de nombres de productos con base al valor que llega por parametro
     * @param valueFilter Valor que sirve de filtro para la combinacion
     * @return ResponseEntity Ok con lista de productos de la combinacion
     */
    @Operation(summary = "Devuelve un listado de combinaciones de productos cuya suma de precios sea menor o igual al valor ingresado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Combinaciones encontradas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Combination.class)),
                            mediaType = "application/json")),
    })
    @GetMapping(path = "/combination/{valueFilter}")
    public ResponseEntity<List<Combination>> getCombinationProducts(@PathVariable Double valueFilter) {
        return ResponseEntity.ok(servicePort.getCombinationProducts(valueFilter));
    }

    /**
     * Crea un producto
     * @return ResponseEntity Created con el producto creado
     */
    @Operation(summary = "Agrega un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequestDto.class)))
    })
    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody ProductRequestDto newProduct) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicePort.save(newProduct));
    }

    /**
     * Modifica un producto
     * @return ResponseEntity Ok con el producto modificado, ResponseEntity Not Found si no existe el producto
     */
    @Operation(summary = "Modifica un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto modificado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequestDto.class))),
            @ApiResponse(responseCode = "404", description = "Producto a modificar no existe", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody ProductRequestDto modifyProduct) {
        return ResponseEntity.of(servicePort.update(modifyProduct));
    }

    /**
     * Elimina un producto dado su id
     * @param id Id del producto a eliminar
     * @return ResponseEntity Ok si la elimina, ResponseEntity Not Found si no existe el producto
     */
    @Operation(summary = "Elimina un producto dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto a eliminar no existe", content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(servicePort.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
