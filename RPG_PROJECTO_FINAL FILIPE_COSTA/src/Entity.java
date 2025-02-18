
//Crie uma classe abstrata “Entidade” com os atributos:
// nome (String)
// vidaMax / maxHp (int)
// vidaAtual / hp (int)
// força (int).
//o vidaMax e vidaAtual devem começar iguais.

abstract class Entity {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int strength;

    public Entity(String name, int maxHp, int strength) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.strength = strength;
        /**.
         * @param name nome da entity
         * @param maxHp vida máxima inicial
         * @param strength força inicial
         */
    }

    // getters para aceder aos atributos -----------------------------------

    /**
     * @return nome
     */
    public String getName() { return name; }

    /**
     * @return A vida atual da entidade
     */
    public int getHp() { return hp; }

    /**
     * @return força
     */
    public int getStrength() { return strength; }

    //metodos para reduzir e aumentar o hp ---------------------------------

    /**
     * Reduz a vida da entity, garantindo que não fique negativa
     * @param amount A quantidade de dano a ser aplicada
     */
    public void reduceHp(int amount) {
        this.hp = Math.max(0, this.hp - amount);
    }


    /**
     * Aumenta a vida da entity, garantindo que não ultrapasse o máximo
     * @param amount A quantidade de cura a ser aplicada
     */
    public void increaseHp(int amount) {
        this.hp = Math.min(maxHp, this.hp + amount);
    }

    /**
     * Altera a força da entity
     * @param strength  força
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Exibe apenas o nivel de hp
     */
    public void showHp() {
        System.out.println(name + ": HP " + hp + "/" + maxHp);
    }

    //o Crie o metodo mostrarDetalhes que escreva na consola todos os detalhes da entidade.
    public void checkStatus() {
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Strength: " + strength);
    }


}