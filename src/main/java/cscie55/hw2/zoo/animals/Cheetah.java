package cscie55.hw2.zoo.animals;

import java.util.ArrayList;
import java.util.List;

/******************************
* 
* class: Cheetah
* name: Brendan Murphy
* CSCIE-55 HW 2
* date: 9/29/2018
******************************/
public class Cheetah {

    //3 fields specifying characteristics including favoriteFoods
    private String name;
    private String color;
    private List<String> favoriteFoods = new ArrayList<String>();
    
    //public, no-argument constructor for each class that allows instances of each class to be created.
    public Cheetah(){
        this.name = "Brendan";
        this.color = "yellow";
        this.favoriteFoods.add("meat");
        this.favoriteFoods.add("fish");
        eat(this.favoriteFoods.get(1));
    }    
    
    public String eat(String favoriteFood) {
        return ("Yum I love "+ favoriteFood);
    }
    
    public String speak() {
        return ("roarrr");
    }
    
    public String play() {
        return ("yayyy");
    }

}
