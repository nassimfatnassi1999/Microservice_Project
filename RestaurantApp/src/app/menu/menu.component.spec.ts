import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Dish } from '../models/Dish';
import { RestaurantService } from '../services/restaurant.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  restaurantId!: number;
  dishes!: Dish[];

  constructor(private route: ActivatedRoute, private restaurantService: RestaurantService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.restaurantId = +params['id'];
      this.getDishes();
    });
  }

  getDishes() {
    this.restaurantService.getDishesByRestaurantId(this.restaurantId).subscribe(dishes => {
      this.dishes = dishes;
    });
  }
}
