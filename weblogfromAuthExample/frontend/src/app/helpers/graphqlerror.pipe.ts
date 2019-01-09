import {Pipe, PipeTransform} from '@angular/core';
import {SlicePipe} from '@angular/common';

@Pipe({
  name: 'graphqlerror'
})
export class GraphqlerrorPipe extends SlicePipe implements PipeTransform {

  transform(value: any, args?: any): any {
    return super.transform(value, 22 );
  }

}
