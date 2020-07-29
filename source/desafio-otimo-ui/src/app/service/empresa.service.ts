import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { EnvironmentService } from './environment.service';

import { Empresa } from '../model/empresa';
import { Page } from '../model/page';

@Injectable()
export class EmpresaService {

  private url;

  constructor(private httpClient: HttpClient, private environmentService: EnvironmentService) {
    this.url = this.environmentService.getResourcesUrl() + '/api/empresas';
    console.log(`URL:  ${this.url}`);
  }

  public get(id: number): Observable<Empresa> {
    console.log(`EmpresaService.get(${id});`);
    const url = `${this.url}/${id}`;
    return this.httpClient.get(url);
  }

  public save(empresa: Empresa): Observable<Empresa> {
    console.log(`EmpresaService.save(${empresa};`);
    const url = `${this.url}`;
    return this.httpClient.post(url, empresa);
  }

  public update(empresa: Empresa): Observable<Empresa> {
    console.log(`EmpresaService.update(${empresa};`);
    const url = `${this.url}/${empresa.id}`;
    return this.httpClient.put(url, empresa);
  }

  public delete(id: number): Observable<any> {
    console.log(`EmpresaService.delete(${id};`);
    const url = `${this.url}/${id}`;
    return this.httpClient.delete(url);
  }

  public list(tipo: string, cnpj: string, nome: string, page: number, size: number = 10): Observable<Page<Empresa>> {
    console.log(`EmpresaService.list(${tipo}, ${cnpj}, ${nome}, ${page}, ${size});`);
    const url = `${this.url}?tipo=${tipo}&cnpj=${cnpj}&nome=${nome}&page=${page}&size=${size}`;
    return this.httpClient.get(url);
  }

}
