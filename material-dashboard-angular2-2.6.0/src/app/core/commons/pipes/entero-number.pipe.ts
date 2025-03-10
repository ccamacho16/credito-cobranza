import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'enteroNumber'
})
export class EnteroNumber implements PipeTransform {

  transform(value: number): string {
    if (value === null || value === undefined) return null;
    
    // Formatea el número con separador de miles y añade el símbolo de $
    return value.toLocaleString('es-ES') + ' Bs.';
  }
}