package main.java.InventorySystem;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Store store = new Store();

        Product apples = new Product("PRD-101", "Pommes Gala", "Vergers du Nord", Category.PRODUCE);
        Product milk = new Product("DRY-201", "Lait Entier 1L", "Laiterie Bio", Category.DAIRY);
        Product bread = new Product("BKR-301", "Pain de Campagne", "Boulangerie Artisanale", Category.BAKERY);
        Product pasta = new Product("PNT-401", "Pâtes Spaghetti", "Pastificio", Category.PANTRY);

        store.addInventoryItem(new InventoryItem(apples, 50, 10, 25, 2.50));
        store.addInventoryItem(new InventoryItem(milk, 30, 8, 20, 1.80));
        store.addInventoryItem(new InventoryItem(bread, 20, 5, 15, 3.20));
        store.addInventoryItem(new InventoryItem(pasta, 40, 10, 30, 1.40));

        store.listProductsByCategory();

        // Panier physique du jour
        Cart cart1 = new Cart(1, CartType.PHYSICAL);
        cart1.addItem(store.getInventoryItem("PRD-101"), 6);
        cart1.addItem(store.getInventoryItem("BKR-301"), 2);
        store.manageStoreCarts(cart1);

        // Panier virtuel d'hier (sera abandonné et ses quantités libérées)
        Cart oldCart = new Cart(2, CartType.VIRTUAL, LocalDate.now().minusDays(1));
        oldCart.addItem(store.getInventoryItem("DRY-201"), 5);
        store.manageStoreCarts(oldCart);

        System.out.println("\nPassage en caisse du panier #1 :");
        store.checkOutCart(cart1);

        System.out.println("\nAbandon des paniers dont la date != aujourd'hui :");
        int abandoned = store.abandonCarts();
        System.out.println("Paniers abandonnés : " + abandoned);

        System.out.println();
        store.printFullInventory();
    }
}
