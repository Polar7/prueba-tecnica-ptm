import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ProductDto} from "../../../../core/dto/productDto";
import {ProductService} from "../../../../core/service/product.service";
import {MeowFactsService} from "../../../../core/service/meow-facts.service";
import Swal from "sweetalert2";
import {CombinationDto} from "../../../../core/dto/combinationDto";

/**
 * Componente general donde se visualizan todos los productos
 */
@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrl: './portfolio.component.css'
})
export class PortfolioComponent implements OnInit, AfterViewInit {

  /**
   * Lista con todos los productos
   */
  public allProducts: Array<ProductDto>;

  /**
   * Objeto con los datos de gatos
   */
  public meowFacts: Array<string>;

  /**
   * Valor para el filtro de la combinacion de productos
   */
  public valueFilterCombination: number;

  /**
   * Lista de las combinaciones de los productos
   */
  public combinationList: Array<CombinationDto>;

  /**
   * Construye el componente
   * @param productService Servicio de producto
   * @param meowFactService Servicio de datos de gatos
   */
  constructor(private productService: ProductService, private meowFactService: MeowFactsService) {
  }

  /**
   * Consulta los datos de los gatos despues de inicializado el componente
   */
  ngOnInit(): void {
    this.meowFactService.getMeowFacts().subscribe({
      next: x => {
        this.meowFacts = x.data;
        this.showAlertsMeowFacts();
      }
    });
  }

  /**
   * Consulta los productos despues de haber pintado el componente
   */
  ngAfterViewInit(): void {
    this.productService.getAllProducts().subscribe(x => this.allProducts = x);
  }

  /**
   * Elimina un producto seleccionado en la tabla
   * @param idProduct ID del producto a eliminar
   */
  public deleteProduct(idProduct: number): void {
    Swal.fire({
      title: "Est&aacute;s seguro de eliminar este producto?",
      showDenyButton: true,
      confirmButtonText: "Si, eliminar",
      confirmButtonColor: "#013F4E",
      denyButtonText: `Cancelar`
    }).then((result) => {
      if (result.isConfirmed) {
        this.productService.deleteProduct(idProduct).subscribe({
          next: value => {
            if (value) {
              Swal.fire({
                title: "Producto eliminado correctamente",
                icon: "success",
                confirmButtonColor: "#013F4E",
              });
            } else {
              Swal.fire({
                title: "Error t&eacute;cnico al eliminar el producto",
                icon: "success",
                confirmButtonColor: "#013F4E",
              });
            }
          }
        });
      }
    });
  }

  /**
   * Busca las combinaciones posibles segun el valor ingresado para el filtro
   */
  public searchCombinationProducts(): void {
    this.productService.getCombinationProducts(this.valueFilterCombination).subscribe(x => this.combinationList = x);
  }

  /**
   * Lanza modales emergentes con cada dato de los gatos
   */
  private async showAlertsMeowFacts(): Promise<void> {
    for (let fact of this.meowFacts) {
      await Swal.fire({
        title: "Sab&iacute;as que...",
        text: fact,
        icon: "question",
        iconColor: "#0BA5BE",
        confirmButtonColor: "#013F4E"
      });
    }
  }

}
