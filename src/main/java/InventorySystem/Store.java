package main.java.InventorySystem;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Store {

    private final Map<String, InventoryItem> inventory;
    private final NavigableSet<Cart> carts;
    private final Map<Category, Map<String, InventoryItem>> aisleInventory;

    public Store() {
        this.inventory = new TreeMap<>();
        this.carts = new TreeSet<>();
        this.aisleInventory = new EnumMap<>(Category.class);
        for (Category category : Category.values()) {
            aisleInventory.put(category, new TreeMap<>());
        }
    }

    public void addInventoryItem(InventoryItem item) {
        if (item == null) return;
        String sku = item.getProduct().sku();
        inventory.put(sku, item);
        aisleInventory.get(item.getProduct().category()).put(sku, item);
    }

    public InventoryItem getInventoryItem(String sku) {
        return inventory.get(sku);
    }

    public void manageStoreCarts(Cart cart) {
        if (cart == null) return;
        carts.add(cart);
    }

    public boolean checkOutCart(Cart cart) {
        if (cart == null || !carts.contains(cart)) {
            return false;
        }

        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            InventoryItem item = inventory.get(entry.getKey().sku());
            if (item == null || !item.sellItem(entry.getValue())) {
                return false;
            }
        }

        cart.printSalesSlip(inventory);
        cart.clearWithoutReleasing();
        carts.remove(cart);
        return true;
    }

    public int abandonCarts() {
        return abandonCarts(LocalDate.now());
    }

    public int abandonCarts(LocalDate referenceDate) {
        int abandonedCount = 0;
        Iterator<Cart> iterator = carts.iterator();

        while (iterator.hasNext()) {
            Cart cart = iterator.next();
            if (!cart.getDate().equals(referenceDate)) {
                for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
                    InventoryItem item = inventory.get(entry.getKey().sku());
                    if (item != null) {
                        item.releaseItem(entry.getValue());
                    }
                }
                cart.clearWithoutReleasing();
                iterator.remove();
                abandonedCount++;
            }
        }
        return abandonedCount;
    }

    public void listProductsByCategory() {
        System.out.println("=== Inventaire par catégorie (Rayons) ===");
        for (Map.Entry<Category, Map<String, InventoryItem>> entry : aisleInventory.entrySet()) {
            System.out.println("Catégorie : " + entry.getKey());
            Map<String, InventoryItem> itemsInCategory = entry.getValue();
            if (itemsInCategory.isEmpty()) {
                System.out.println("  (Aucun produit)");
                continue;
            }
            itemsInCategory.values().stream()
                    .sorted(Comparator.comparing(i -> i.getProduct().name()))
                    .forEach(item -> System.out.println("  " + item));
        }
    }

    public void printFullInventory() {
        System.out.println("=== Inventaire global du magasin ===");
        inventory.values().forEach(item -> System.out.println("  " + item));
    }
}
