import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Params, Router } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Observable } from 'rxjs';

import { DialogComponent } from '../dialog/dialog.component';

import { Empresa } from 'src/app/model/empresa';
import { Page } from 'src/app/model/page';

import { EmpresaService } from 'src/app/service/empresa.service';
import { CepService } from 'src/app/service/cep.service';

@Component({
  selector: 'app-empresa-edit',
  templateUrl: './empresa-edit.component.html',
  styleUrls: ['./empresa-edit.component.scss']
})
export class EmpresaEditComponent implements OnInit {

  id: number;

  model: Empresa;

  matrizStr: string;

  cnpjMask = [/\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  cepMask = [/\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/,  /\d/];

  constructor(private route: ActivatedRoute, private router: Router, private ngbModal: NgbModal,
              private empresaService: EmpresaService, private cepService: CepService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: Params) => {
        if (params.id) {
          const id = parseInt(params.id, 10);
          this.empresaService.get(id).subscribe(
            item => {
              this.id = item.id;
              this.model = item;
            },
            response => this.onError(response),
            () => console.log('Complete')
          );
        } else {
          this.model = {tipo: '', estado: ''};
        }
      }
    );
  }

  public onSearchMatriz(text$: Observable<string>): Observable<Page<Empresa>> {
    const obs = this.empresaService.list('MATRIZ', '', '', 0, 100);
    return obs;
  }

  public onClickCep(): void {
    const cepStr = this.model.cep;
    if ((cepStr) && (cepStr.length === 9)) {
      this.cepService.get(cepStr).subscribe(
        item => {
          this.model.cep = item.cep;
          this.model.estado = item.uf;
          this.model.bairro = item.bairro;
          this.model.cidade = item.localidade;
          this.model.logradouro = item.logradouro;
          this.model.complemento = '';
        },
        response => {
          this.model.estado = '';
          this.model.bairro = '';
          this.model.cidade = '';
          this.model.logradouro = '';
          this.model.complemento = '';
        },
        () => console.log('Complete')
      );
    }
  }

  public onSubmit(): void {
    console.log('EmpresaEditComponent.onSubmit();');
    const empresa: Empresa = this.model;
    console.log(JSON.stringify(empresa));

    let obs: Observable<Empresa>;

    if (this.id) {
      empresa.id = this.id;
      obs = this.empresaService.update(empresa);
    } else {
      obs = this.empresaService.save(empresa);
    }

    obs.subscribe(
      item => {
        this.onSuccess(item);
        // this.setFormValue(this.form, this.formErrors, item);
      },
      response => this.onError(response),
      () => console.log('Complete')
    );

  }

  private onSuccess(item: Empresa) {
    console.log('Success...');
    const msg = ['Empresa cadastrada corretamente'];
    const ref = this.ngbModal.open(DialogComponent);
    const instance: DialogComponent = ref.componentInstance as DialogComponent;
    instance.configure('Mensagem', msg, 'success', true);
  }

  private onError(response: any) {
    console.log('Error...');
    console.log(JSON.stringify(response));
    let msg = ['Erro ao cadastrar a empresa'];
    if (typeof response === 'string') {
      msg = [response];
    } else if ((response) && (response.error) && (response.error.message)) {
      msg = [response.error.message];
    } else if ((response) && (response.message)) {
      msg = [response.message];
    }

    const ref = this.ngbModal.open(DialogComponent);
    const instance: DialogComponent = ref.componentInstance as DialogComponent;
    instance.configure('Erro', msg, 'danger', true);
  }

}
