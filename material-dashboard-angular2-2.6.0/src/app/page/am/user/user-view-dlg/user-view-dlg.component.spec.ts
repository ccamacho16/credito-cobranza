import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserViewDlgComponent } from './user-view-dlg.component';

describe('UserViewDlgComponent', () => {
  let component: UserViewDlgComponent;
  let fixture: ComponentFixture<UserViewDlgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserViewDlgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserViewDlgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
