package org.softwaretechnologies;

// TODO: 09.12.2024 Создайте класс MilkCoffee, поддерживающий интерфейс CofeIntrface
//  к стоимости базового напитка добавьте 10.
//  к описанию добавьте " + milk"

public class MilkCoffee extends Coffee {
    private CoffeeInterface coffee;
    public static final int COST = 10;

    public MilkCoffee(CoffeeInterface coffee){
        this.coffee = coffee;
    }
    @Override
    public int getCost() {
        return coffee.getCost() + COST;
    }

    @Override
    public String description() {
        return coffee.description() + " + milk";
    }


}