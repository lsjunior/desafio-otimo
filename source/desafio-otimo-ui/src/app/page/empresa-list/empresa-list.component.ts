import { Component, OnInit } from '@angular/core';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { DialogComponent } from '../dialog/dialog.component';

import { EmpresaService } from 'src/app/service/empresa.service';

import { Page } from 'src/app/model/page';
import { Empresa } from 'src/app/model/empresa';

@Component({
  selector: 'app-empresa-list',
  templateUrl: './empresa-list.component.html',
  styleUrls: ['./empresa-list.component.scss']
})
export class EmpresaListComponent implements OnInit {

  cnpj: string;

  nome: string;

  page: Page<Empresa>;

  constructor(private ngbModal: NgbModal, private empresaService: EmpresaService) { }

  ngOnInit(): void {
    console.log('EmpresaListComponent.ngOnInit();');
    this.cnpj = '';
    this.nome = '';
    this.doList();
  }

  public list(): void {
    console.log('EmpresaListComponent.list();');
    this.page = null;
    this.doList();
  }

  public onPageChange(event: any): void {
    console.log('New Page: ' + event);
    this.doList();
  }

  public onClickDelete(item: Empresa): void {
    console.log(`EmpresaListComponent.onClickDelete(${item.id})`);
    const ref = this.ngbModal.open(DialogComponent);
    ref.result.then(result => {
      if (result === 'Confirm') {
        this.empresaService.delete(item.id).subscribe(
          body => {
            this.onSuccess();
            this.doList();
          },
          response => this.onError(response),
          () => console.log('Complete')
        );
      }
    });
    const instance: DialogComponent = ref.componentInstance as DialogComponent;
    const msg = `Deseja realmente excluir essa empresa ${item.nome}`;
    // const msg = 'Deseja realmente excluir essa empresa?';
    instance.configure('Confirmação', msg, 'danger', false, true, true);
  }

  private doList(): void {
    const pageNumber = this.page ? this.page.number: 1;
    const pageSize = 10;
    const observable = this.empresaService.list('', this.cnpj, this.nome, pageNumber - 1, pageSize);
    observable.subscribe(
      page => {
        this.page = page;
        this.page.number++;
      },
      response => this.onError(response),
      () => console.log('Complete')
    );
  }

  private onSuccess() {
    console.log('Success...');
    const msg = ['Empresa excluida corretamente'];
    const ref = this.ngbModal.open(DialogComponent);
    const instance: DialogComponent = ref.componentInstance as DialogComponent;
    instance.configure('Mensagem', msg, 'success', true);
  }

  private onError(response: any) {
    console.log('Error...');
    console.log(JSON.stringify(response));
    let msg = ['Erro ao executar a pesquisa'];
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
