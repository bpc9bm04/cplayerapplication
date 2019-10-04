import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerDailogComponent } from './player-dailog.component';

describe('MovieDailogComponent', () => {
  let component: PlayerDailogComponent;
  let fixture: ComponentFixture<PlayerDailogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayerDailogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerDailogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
