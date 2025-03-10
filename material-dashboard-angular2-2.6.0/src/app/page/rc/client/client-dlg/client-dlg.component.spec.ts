import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDlgComponent } from './client-dlg.component';

describe('ClientDlgComponent', () => {
  let component: ClientDlgComponent;
  let fixture: ComponentFixture<ClientDlgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientDlgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDlgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
