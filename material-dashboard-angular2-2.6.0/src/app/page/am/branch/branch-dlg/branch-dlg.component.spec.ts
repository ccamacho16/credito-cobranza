import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchDlgComponent } from './branch-dlg.component';

describe('BranchDlgComponent', () => {
  let component: BranchDlgComponent;
  let fixture: ComponentFixture<BranchDlgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BranchDlgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchDlgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
