package org.softwaretechnologies.animals;

public enum AnimalType {
    CAT{
        @Override
        public Animal create(String name){
            return new Cat(name);
        }

    }, DOG{
        public Animal create(String name) {
            return new Dog(name);
        }

    }, COW{
            public Animal create(String name){
                return new Cow(name);
        }

    };


    public abstract Animal create(String name);
}
