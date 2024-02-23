import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UselessFactDto} from "../dto/uselessFactDto";

/**
 * Servicio para el consumo de la API de UselessFacts
 */
@Injectable({
  providedIn: 'root'
})
export class UselessFactsService {

  /**
   * Construye el servicio
   * @param http Libreria para las peticiones HTTP
   */
  constructor(private http: HttpClient) { }

  /**
   * Consulta un dato inutil del dia
   * @return Observable con el dato
   */
  public getUselessFactToday(): Observable<UselessFactDto> {
    return this.http.get<UselessFactDto>("https://uselessfacts.jsph.pl/api/v2/facts/today");
  }
}
