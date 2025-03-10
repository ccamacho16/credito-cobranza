import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RowView } from 'app/core/models/row-view';

@Component({
  selector: 'app-user-view-dlg',
  templateUrl: './user-view-dlg.component.html',
  styleUrls: ['./user-view-dlg.component.scss']
})
export class UserViewDlgComponent implements OnInit {

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
