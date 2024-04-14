import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsCompoenentComponent } from './news-compoenent.component';

describe('NewsCompoenentComponent', () => {
  let component: NewsCompoenentComponent;
  let fixture: ComponentFixture<NewsCompoenentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewsCompoenentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewsCompoenentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
