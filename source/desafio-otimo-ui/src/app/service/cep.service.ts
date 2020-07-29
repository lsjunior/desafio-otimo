import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { EnvironmentService } from './environment.service';

import { Cep } from '../model/cep';

@Injectable()
export class CepService {

  private url;

  constructor(private httpClient: HttpClient, private environmentService: EnvironmentService) {
    this.url = this.environmentService.getResourcesUrl() + '/api/ceps';
    console.log(`URL:  ${this.url}`);
  }

  public get(cep: string): Observable<Cep> {
    console.log(`CepService.get(${cep});`);
    const getUrl = `${this.url}/${cep}`;

    return this.httpClient.get(getUrl);
  }

}
