import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RcClientDto } from 'app/core/models/rc-client-dto';
import { RowView } from 'app/core/models/row-view';

@Component({
  selector: 'app-client-view-dlg',
  templateUrl: './client-view-dlg.component.html',
  styleUrls: ['./client-view-dlg.component.scss']
})
export class ClientViewDlgComponent implements OnInit {

  infoRows : RowView[];
  titulo: string = '';
  accion: string = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.infoRows = data.rows;
    this.titulo = data.titulo;
    this.accion = data.accion;
  }

  ngOnInit(): void {
  }

}
