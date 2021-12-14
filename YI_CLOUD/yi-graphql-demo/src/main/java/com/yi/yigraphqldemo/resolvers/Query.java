package com.yi.yigraphqldemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.yi.yigraphqldemo.entities.Pet;
import com.yi.yigraphqldemo.eums.Animal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    public List<Pet> pets() {
        List<Pet> pets = new ArrayList<>();

        Pet aPet = new Pet();
        aPet.setId(1L);
        aPet.setName("Covey's cat");
        aPet.setAge(3);
        aPet.setType(Animal.CAT);

        pets.add(aPet);

        return pets;
    }

    public List<Animal> animals() {
        Animal animal = Animal.MAMMOTH;
        Animal animal1 = Animal.BADGER;
        Animal animal2 = Animal.CAT;
        Animal animal3 = Animal.OOOOOO;
        Animal animal4 = Animal.DOG;
        List<Animal> animalList = new ArrayList<>(4);
        animalList.add(animal);
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        return animalList;
    }
}
