import { Injectable } from '@angular/core';
import * as Notiflix from 'notiflix';

@Injectable({
  providedIn: 'root',
})
export class NotiflixService {

  constructor() {
    this.configureNotiflix();
  }

  private configureNotiflix() {
    Notiflix.Confirm.init({
      titleColor: '#263238',
      okButtonBackground: '#303f9f',
      cancelButtonBackground: '#dc3545',
      width: '400px',
      fontFamily: 'Arial, sans-serif',
      borderRadius: '3px',
    });

    Notiflix.Notify.init({
      timeout: 3000, // El mensaje permanecerá 5 segundos
      position: 'right-bottom', // Establece la posición en la parte inferior derecha
    });
  }

  

  public showConfirm(title: string, message: string, onConfirm: () => void, onCancel: () => void) {
    Notiflix.Confirm.show(
      title,
      message,
      'Sí',
      'No',
      onConfirm,
      onCancel
    );
  }

  public showSuccess(message: string){
    Notiflix.Notify.success(message);
  }

  public showError(message: any) {
    Notiflix.Notify.failure(message);
  }
}