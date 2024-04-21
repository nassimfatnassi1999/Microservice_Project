import { Dish } from "./Dish";

export interface Restaurant {
    id_restaurant: number;
    name: string;
    logo: string;
    category: string; 
    averageRating: number;
    waitTime: number;
    isEcoFriendly: boolean;
    contactInfo: string;
    delivery: boolean;
    menu: Dish[];
  }