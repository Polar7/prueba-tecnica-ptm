import {AfterViewInit, Component, OnInit} from '@angular/core';
import {UselessFactsService} from "../../../../core/service/useless-facts.service";

/**
 * Componente para el footer de la pagina
 */
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent implements OnInit {

  /**
   * Dato del dia
   */
  public uselessFact: string;

  /**
   * Construye el componente
   * @param uselessFactsService Servicio para consultar el dato del dia
   */
  constructor(private uselessFactsService: UselessFactsService) {
  }

  /**
   * Consulta el dato del dia despues de inicializado el componente
   */
  ngOnInit(): void {
    this.uselessFactsService.getUselessFactToday().subscribe(x => this.uselessFact = x.text);
  }

}
