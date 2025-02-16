class NPC extends Entity {
    protected int gold;

    public NPC(String name, int maxHp, int strength, int gold) {
        super(name, maxHp, strength);
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    @Override
    public void checkStatus() {
        super.checkStatus();
        System.out.println("Gold: " + gold);
    }

    public void reduceGold(int amount) {
        if (amount <= gold) {
            gold -= amount;
            System.out.println(getName() + " lost " + amount + " gold");
        }
    }

    public void addGold(int amount) {
        gold += amount;
        System.out.println(getName() + " gained " + amount + " gold");
    }
}