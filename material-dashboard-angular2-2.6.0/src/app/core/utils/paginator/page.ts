import {Sort} from './sort';
import {Pageable} from './pageable';

export class Page {
  content: any[];
  pageable: Pageable;
  last: boolean;
  totalPages: number;
  totalElements: number;
  sort: Sort;
  first: boolean;
  numberOfElements: number;
  size: number;
  number: number;
  empty: boolean;
  offset: number;
  constructor() {}
}

