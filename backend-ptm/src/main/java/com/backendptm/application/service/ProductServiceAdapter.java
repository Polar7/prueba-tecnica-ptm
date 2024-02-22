package com.backendptm.application.service;

import com.backendptm.application.dto.ProductRequestDto;
import com.backendptm.application.mapper.IProductRequestMapper;
import com.backendptm.domain.model.Combination;
import com.backendptm.domain.model.Product;
import com.backendptm.domain.repository.IProductRepositoryPort;
import com.backendptm.domain.service.IProductServicePort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador con las implementaciones del repositorio de Producto
 */
@Service
public class ProductServiceAdapter implements IProductServicePort {

    /**
     * Puerto del repositorio de Producto
     */
    private final IProductRepositoryPort repositoryPort;

    /**
     * Mapper de un productRequest
     */
    private final IProductRequestMapper productRequestMapper;

    /**
     * Construye el adaptador del servicio de Producto
     * @param repositoryPort Puerto del repository de producto
     * @param productRequestMapper Mapper
     */
    public ProductServiceAdapter(IProductRepositoryPort repositoryPort, IProductRequestMapper productRequestMapper) {
        this.repositoryPort = repositoryPort;
        this.productRequestMapper = productRequestMapper;
    }

    /**
     * Obtiene todos los productos
     * @return Lista con todos los productos existentes
     */
    @Override
    public List<Product> getAll() {
        return repositoryPort.getAll();
    }

    /**
     * Devuelve un producto dado su id
     * @param idProduct Id del producto a buscar
     * @return Optional del producto encontrado
     */
    @Override
    public Optional<Product> getProductById(Integer idProduct) {
        return repositoryPort.getProductById(idProduct);
    }

    /**
     * Guarda un Producto
     * @param product Producto a guardar
     * @return Producto guardado
     */
    @Override
    public Product save(ProductRequestDto product) {
        return repositoryPort.save(productRequestMapper.toProduct(product));
    }

    /**
     * Modifica un Producto
     * @param product Producto a modificar
     * @return Producto modificado
     */
    @Override
    public Optional<Product> update(ProductRequestDto product) {
        if (repositoryPort.getProductById(product.getId()).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(save(product));
    }

    /**
     * Elimina un producto dado su id
     * @param idProduct Id del producto a eliminar
     */
    @Override
    public boolean delete(Integer idProduct) {
        if (repositoryPort.getProductById(idProduct).isEmpty()) {
            return false;
        }

        repositoryPort.delete(idProduct);
        return true;
    }

    /**
     * Retorna una lista de combinaciones sin repeticion de nombres de productos donde
     * la suma de sus precios no es superior al valor que llega por parametro
     * @param valueFilter Valor que sirve de filtro para la combinacion
     * @return Listado de nombres de productos que cumplen con el filtro, ordenados descendentemente por el precio
     */
    @Override
    public List<Combination> getCombinationProducts(Double valueFilter) {
        List<Product> allProducts = getAll();
        if (allProducts.size() < 2) {
            return new ArrayList<>();
        }

        int limitListCombination = 5;
        List<Combination> stringCombination;

        stringCombination = twoCombination(allProducts, valueFilter, limitListCombination);

        if (stringCombination.size() < limitListCombination) {
            stringCombination.addAll(threeCombination(allProducts, valueFilter, limitListCombination, stringCombination.size()));
        }

        stringCombination.sort(Comparator.comparingDouble(Combination::getTotal).reversed());
        return stringCombination;
    }

    /**
     * Obtiene los combinaciones pares posibles
     * @param products Lista original con todos los productos
     * @param valueLimit Valor maximo que pueden sumar los dos productos
     * @param limitListCombination Maximo de pares permitidos en la listaCombination
     * @return Lista de combinaciones de pares encontradas
     */
    private List<Combination> twoCombination(List<Product> products, double valueLimit, int limitListCombination) {

        List<Combination> stringCombination = new ArrayList<>();

        for (int i = 0; i < products.size() && stringCombination.size() < limitListCombination; i++) {
            for (int j = i + 1; j < products.size() && stringCombination.size() < limitListCombination; j++) {

                double currentSumProduct = products.get(i).getValue() + products.get(j).getValue();

                if (currentSumProduct <= valueLimit) {
                    stringCombination.add(
                            new Combination(
                                    Arrays.asList(products.get(i).getName(), products.get(j).getName()),
                                    currentSumProduct));
                }
            }
        }

        return stringCombination;
    }

    /**
     * Combina las combinaciones ternas posibles
     * @param products Lista original con todos los productos
     * @param valueLimit Valor maximo que pueden sumar los tres productos
     * @param limitListCombination Maximo de ternas permitidas en la listaCombination
     * @param totalStringCombination Tamanio actual de la listaCombination
     * @return Lista de combinaciones de ternas encontradas
     */
    private List<Combination> threeCombination(List<Product> products, double valueLimit, int limitListCombination, int totalStringCombination) {

        List<Combination> stringCombination = new ArrayList<>();

        for (int i = 0; i < products.size() && totalStringCombination < limitListCombination; i++) {
            for (int j = i + 1; j < products.size() && totalStringCombination < limitListCombination; j++) {
                for (int k = j + 1; k < products.size() && totalStringCombination < limitListCombination; k++) {

                    double currentSumProduct = products.get(i).getValue() + products.get(j).getValue() + products.get(k).getValue();

                    if (currentSumProduct <= valueLimit) {
                        stringCombination.add(
                                new Combination(
                                        Arrays.asList(products.get(i).getName(), products.get(j).getName(), products.get(k).getName()),
                                        currentSumProduct));
                        totalStringCombination++;
                    }
                }
            }
        }

        return stringCombination;
    }

}
