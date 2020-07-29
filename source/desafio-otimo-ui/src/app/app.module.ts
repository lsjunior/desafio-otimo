import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TextMaskModule } from 'angular2-text-mask';
// import { AngularValidateBrLibModule } from 'angular-validate-br';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CnpjValidatorDirective } from './directive/cnpj.directive';

import { CepService } from './service/cep.service';
import { EmpresaService } from './service/empresa.service';
import { EnvironmentService } from './service/environment.service';

import { DialogComponent } from './page/dialog/dialog.component';
import { EmpresaEditComponent } from './page/empresa-edit/empresa-edit.component';
import { EmpresaListComponent } from './page/empresa-list/empresa-list.component';
import { HeaderComponent } from './page/header/header.component';
import { HomeComponent } from './page/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    CnpjValidatorDirective,
    EmpresaEditComponent,
    EmpresaListComponent,
    HeaderComponent,
    HomeComponent,
    DialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    AppRoutingModule,
    NgbModule,
    TextMaskModule
     // AngularValidateBrLibModule
  ],
  exports: [
    DialogComponent
  ],
  providers: [
    CepService,
    EmpresaService,
    EnvironmentService
  ],
  entryComponents: [
    DialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
