import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductDto} from "../dto/productDto";
import {CombinationDto} from "../dto/combinationDto";

/**
 * Servicio para el consumo de la API de Productos
 */
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  /**
   * URL de la API de Productos
   */
  private readonly url: string = "http://localhost:8090/products";

  /**
   * Construye el servicio
   * @param http Libreria para las peticiones HTTP
   */
  constructor(private http: HttpClient) { }

  /**
   * Consulta todos los productos
   * @return Observable con los productos
   */
  public getAllProducts(): Observable<ProductDto[]> {
    return this.http.get<ProductDto[]>(`${this.url}`);
  }

  /**
   * Consulta un producto dado su ID
   * @return Observable con el producto encontrado
   */
  public getProductById(idProduct: number): Observable<ProductDto> {
    return this.http.get<ProductDto>(`${this.url}/${idProduct}`);
  }

  /**
   * Guarda un nuevo producto
   * @param newProduct Producto a guardar
   * @return Observable con el producto guardado correctamente
   */
  public registerProduct(newProduct: ProductDto): Observable<ProductDto> {
    return this.http.post<ProductDto>(`${this.url}`, newProduct);
  }

  /**
   * Modifica un producto existente
   * @param product Producto a modificar
   * @return Observable con el producto editado correctamente
   */
  public modifyProduct(product: ProductDto): Observable<ProductDto> {
    return this.http.put<ProductDto>(`${this.url}`, product);
  }

  /**
   * Elimina un producto existente
   * @param idProduct Id del producto a eliminar
   * @return Observable con true si se elimino, false de lo contrario
   */
  public deleteProduct(idProduct: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.url}/${idProduct}`);
  }

  /**
   * Consulta las posibles combinaciones de nombres de productos con base al valor enviado
   * @param valueFilter Valor para el filtro
   * @return Observable con la lista de combinaciones encontradas
   */
  public getCombinationProducts(valueFilter: number): Observable<CombinationDto[]> {
    return this.http.get<CombinationDto[]>(`${this.url}/combination/${valueFilter}`);
  }

}
