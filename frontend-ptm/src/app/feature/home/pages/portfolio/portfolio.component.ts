import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ProductDto} from "../../../../core/dto/productDto";
import {ProductService} from "../../../../core/service/product.service";
import {MeowFactsService} from "../../../../core/service/meow-facts.service";
import Swal from "sweetalert2";

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
