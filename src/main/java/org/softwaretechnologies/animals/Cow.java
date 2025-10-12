package org.softwaretechnologies.animals;

public class Cow extends Animal{
    public Cow(String name) {
        super(name);
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public String sound(){
        return "moo";
    }
}
