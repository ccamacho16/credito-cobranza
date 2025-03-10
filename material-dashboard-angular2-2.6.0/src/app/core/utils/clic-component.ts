import {Page} from './paginator/page';
import {ChangeDetectorRef} from '@angular/core';
import {MediaMatcher} from '@angular/cdk/layout';


export abstract class ClicComponent {
    /**
     *
     * @type {Page}
     */
    public pageControl: Page = new Page();
  
    /**
     * @Atribute xsMatcher
     * Listener que escucha cuando la dimension de pantalla esta en el rango 0-599px
     */
    private xsMatcher: MediaQueryList; // (max-width: 599px) => xs
  
    /**
     * @Atribute smMatcher
     * Listener que escucha cuando la dimension de pantalla esta en el rango 600-959px
     */
    private smMatcher: MediaQueryList; // (min-width: 600px) and (max-width: 959px) => sm
  
    /**
     * @Atribute mdMatcher
     * Listener que escucha cuando la dimension de pantalla esta en el rango 960px-maxWidth
     */
    private mdMatcher: MediaQueryList; // (min-width: 960px) and (max-width: 1279px) => md
  
    /**
     * @Atribute mdMatcher
     * Listener que escucha cuando la dimension de pantalla esta en el rango 960px-maxWidth
     */
    private lgMatcher: MediaQueryList; // (min-width: 1280px) and (max-width: 1919px)
  
    /**
     * @Atribute mdMatcher
     * Listener que escucha cuando la dimension de pantalla esta en el rango 960px-maxWidth
     */
    private gt_lgMatcher: MediaQueryList; // (min-width: 1920px) => gt-lg
  
    /**
     * flag para activar scrol horizontal en tablas
     */
    public scrollH: boolean;
  
    /**
     * porcentaje del dialogo frente a la dimension actual de pantalla ('50%')
     */
    public dialogWidth: string;
  
    /**
     * porcentaje del dialogos de notificacion y confirmacion frente a la dimension actual de pantalla ('20%')
     */
    public confirmDialogWith: string;
  
    public render: boolean;
  
    protected constructor(){
      this.render = false;
    }
  
    abstract setPage(pageInfo: Page);
  
    public initializePage(pageSize: number, execute: boolean = false) {
      this.pageControl.size = pageSize;
      this.pageControl.number = 0;
      const page: Page = new Page();
      page.offset = this.pageControl.number;
      if (execute) this.setPage(page);
    }
  
    /**
     * se ejecutara cuando el listener {@link xsMatcher} se dispare
     */
    abstract onXsScreen();
  
    /**
     * se ejecutara cuando el listener {@link smMatcher} se dispare
     */
    abstract onSmScreen();
  
    /**
     * se ejecutara cuando el listener {@link mdMatcher} se dispare
     */
    abstract onMdScreen();
  
    /**
     * se ejecutara cuando el listener {@link lgMatcher} se dispare
     */
    abstract onLgScreen();
  
    /**
     * se ejecutara cuando el listener {@link gt_lgMatcher} se dispare
     */
    abstract onGtLgScreen();
  
    /**
     * TODO IMPORTANTE: Ejecute initialListener en OnInit del componente que extienda esta clase
     * Metodo para inicializar los listeners y ejecutar el metodo que corresponda al tamano de pantalla actual.
     * @param {ChangeDetectorRef} changeDetectorRef, se toma de la inyeccion del componente que hereda esta clase
     * @param {MediaMatcher} media, se toma de la inyeccion del componente que hereda esta clase
     */
    public initialListener(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher) {
      const queryListener = () => changeDetectorRef.detectChanges();
      this.xsMatcher = media.matchMedia('(max-width: 599px)');
      this.xsMatcher.addListener(queryListener);
      this.smMatcher = media.matchMedia('(min-width: 600px) and (max-width: 959px)');
      this.smMatcher.addListener(queryListener);
      this.mdMatcher = media.matchMedia('(min-width: 960px) and (max-width: 1279px)');
      this.mdMatcher.addListener(queryListener);
      this.lgMatcher = media.matchMedia('(min-width: 1280px) and (max-width: 1919px)');
      this.lgMatcher.addListener(queryListener);
      this.gt_lgMatcher = media.matchMedia('(min-width: 1920px)');
      this.gt_lgMatcher.addListener(queryListener);
  
      this.xsMatcher.onchange = () => {
        if (this.xsMatcher.matches) {
          this.scrollH = true;
          this.confirmDialogWith = '90%';
          this.dialogWidth = '99%';
          this.onXsScreen();
        }
      };
      this.smMatcher.onchange = () => {
        if (this.smMatcher.matches) {
          this.scrollH = true;
          this.confirmDialogWith = '80%';
          this.dialogWidth = '90%';
          this.onSmScreen();
        }
      };
      this.mdMatcher.onchange = () => {
        if (this.mdMatcher.matches) {
          this.scrollH = true;
          this.confirmDialogWith = '40%';
          this.dialogWidth = '40%';
          this.onMdScreen();
        }
      };
  
      this.lgMatcher.onchange = () => {
        if (this.lgMatcher.matches) {
          this.scrollH = false;
          this.confirmDialogWith = '575px';
          this.dialogWidth = '650px';
          this.onLgScreen();
        }
      };
  
      this.gt_lgMatcher.onchange = () => {
        if (this.gt_lgMatcher.matches) {
          this.scrollH = false;
          this.confirmDialogWith = '575px';
          this.dialogWidth = '650px';
          this.onGtLgScreen();
        }
      };
  
      if (this.xsMatcher.matches) {
        this.scrollH = true;
        this.confirmDialogWith = '90%';
        this.dialogWidth = '90%';
        this.onXsScreen();
      }
  
      if (this.smMatcher.matches) {
        this.scrollH = true;
        this.confirmDialogWith = '80%';
        this.dialogWidth = '80%';
        this.onSmScreen();
      }
  
      if (this.mdMatcher.matches) {
        this.scrollH = true;
        this.confirmDialogWith = '40%';
        this.dialogWidth = '40%';
        this.onMdScreen();
      }
  
      if (this.lgMatcher.matches) {
        this.scrollH = false;
        this.confirmDialogWith = '575px';
        this.dialogWidth = '650px';
        this.onLgScreen();
      }
  
      if (this.gt_lgMatcher.matches) {
        this.scrollH = false;
        this.confirmDialogWith = '575px';
        this.dialogWidth = '650px';
        this.onGtLgScreen();
      }
    }
  
    public tableMessages() {
      return {emptyMessage: 'Ningún registro para mostrar', totalMessage: 'en total', selectedMessage: 'seleccionados'};
    }
  
    public dialogConfig(data: any) {
      return {
          width: '1050px',
          minWidth: '200px',
          maxWidth: '95vw',
          panelClass: ['zero-padding', 'scroll-x-hidden'],
          data
      };
    }
  }
  
  