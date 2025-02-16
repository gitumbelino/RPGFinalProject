
// Desenvolva a classe abstrata ItemHeroi que tem como atributos:
// nome (String)
// preço em moedas de ouro (int)
// ArrayList<String> heroisPermitidos que irá guardar o tipo de heróis que podem/sabem usar dito
//item.
//o Desenvolva também o metodo mostrarDetalhes.

import java.util.ArrayList;

abstract class HeroItem {
    protected String name;
    protected int price;
    protected ArrayList<String> allowedHeroes;

    public HeroItem(String name, int price) {
        this.name = name;
        this.price = price;
        this.allowedHeroes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void addAllowedHero(String heroType) {
        allowedHeroes.add(heroType);
    }

    public void showDetails() {
        System.out.println("Item: " + name);
        System.out.println("Price: " + price + " gold");
        System.out.println("Usable by: " + String.join(", ", allowedHeroes));
    }
}