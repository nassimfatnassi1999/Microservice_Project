import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Dish } from '../models/Dish';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
   dishes: Dish[] = [
    {
      id_dish:1,
      name: 'pasta',
    description: 'st',
    price: 15,
    photo: '../../assets/images/menu/cake.jpg',
    category: 'string',
    badge:'string',
   restaurant : {
      id_restaurant: 1,
      name: 'Restaurant 1',
      logo: '../../assets/images/1.jpg',
      category: 'Category 1',
      averageRating: 4.5,
      waitTime: 20,
      isEcoFriendly: true,
      contactInfo: 'Contact Info 1',
      delivery: true,
      menu: []  
    }},
    {
      id_dish:2,
      name: 'pasta',
    description: 'st',
    price: 15,
    photo: '../../assets/images/menu/cake.jpg',
    category: 'string',
    badge:'string',
   restaurant :{
    id_restaurant: 2,
    name: 'Restaurant 2',
    logo: '../../assets/images/1.jpg',
    category: 'Category 2',
    averageRating: 4.5,
    waitTime: 20,
    isEcoFriendly: true,
    contactInfo: 'Contact Info 2',
    delivery: true,
    menu: []
  }}
 
    
  ];
  id_restaurant: number | undefined;
  constructor(private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id_restaurant');
      if (idParam !== null) {
        this.id_restaurant = +idParam;
      }
    });
  }
}
