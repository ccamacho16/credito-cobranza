import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientViewDlgComponent } from './client-view-dlg.component';

describe('ClientViewDlgComponent', () => {
  let component: ClientViewDlgComponent;
  let fixture: ComponentFixture<ClientViewDlgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientViewDlgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientViewDlgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
