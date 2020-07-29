import { Directive, forwardRef  } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl, ValidatorFn } from '@angular/forms';
import { RfbUtils } from '../util/rfb-utils';

@Directive({
  selector: '[appCnpj][ngModel],[appCnpj][formControl]',
  providers: [{provide: NG_VALIDATORS, useExisting: forwardRef(() => CnpjValidatorDirective), multi: true}]
})
export class CnpjValidatorDirective implements Validator {

  // https://blog.thoughtram.io/angular/2016/03/14/custom-validators-in-angular-2.html
  constructor() {
    console.log('CnpjValidatorDirective()');
  }

  validate(control: AbstractControl): {[key: string]: any} | null {
    console.log('CnpjValidatorDirective.validate');
    const val: string = control.value;
    if ((val) && (val.length === 18)) {
      const valid = RfbUtils.isValidCnpj(control.value);
      return valid ? null : {
        cnpj: {
          valid: false
        }
      };
    }
    return null;
  }

}
