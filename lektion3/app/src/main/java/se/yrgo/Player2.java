package se.yrgo;

public class Player2 {
    private ItemContainer container;
    private RandomProvider random;

    public Player2(ItemContainer container, RandomProvider random) {
        // ...
        this.container = container;
        this.random = random;
    }

    // ...
    
    public void lootBox() {
        Item item = random.getItem();
        container.put(item);
    }
}
