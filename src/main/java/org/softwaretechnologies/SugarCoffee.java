package org.softwaretechnologies;


// TODO: 09.12.2024 /**
//     Создайте класс SugarCoffee, поддерживающий интерфейс CofeIntrface
//     к стоимости базового напитка добавьте 20.
//     к описанию добавьте " + sugar"
//     */

public class SugarCoffee extends Coffee {
    private CoffeeInterface coffee;
    public static final int COST = 20;

    public SugarCoffee(CoffeeInterface coffee){
        this.coffee = coffee;
    }
    @Override
    public int getCost() {
        return coffee.getCost() + COST;
    }

    @Override
    public String description() {
        return coffee.description() + " + sugar";
    }
}
