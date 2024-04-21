
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from '../models/Restaurant';
import { RestaurantService } from '../services/restaurant.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent {

  listRestaurant: Restaurant[] = [];

  constructor(
    private router: Router,
    private restaurantService: RestaurantService
  ) {}
  ngOnInit(): void {
    this.loadRestaurants(); }
    loadRestaurants(): void {
      this.restaurantService.getAllRestaurants()
        .subscribe(restaurants => {
          this.listRestaurant = restaurants;
        });
    }
  
    showMenu(restaurant: Restaurant): void {
      this.router.navigate(['/menu', restaurant.id_restaurant]);
    }
}
