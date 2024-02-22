import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MeowFactDto} from "../dto/meowFactDto";

/**
 * Servicio para el consumo de la API de MeowFacts
 */
@Injectable({
  providedIn: 'root'
})
export class MeowFactsService {

  /**
   * Construye el servicio
   * @param http Libreria para las peticiones HTTP
   */
  constructor(private http: HttpClient) { }

  /**
   * Consulta un listado de datos sobre gatos
   * @return Observable con los datos
   */
  public getMeowFacts(): Observable<MeowFactDto> {
    return this.http.get<MeowFactDto>("https://meowfacts.herokuapp.com/?lang=esp&count=2");
  }
}
