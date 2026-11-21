package main.java.InventorySystem;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Cart implements Comparable<Cart> {

    private final int id;
    private final Map<Product, Integer> products;
    private final LocalDate date;
    private final CartType type;

    public Cart(int id, CartType type) {
        this(id, type, LocalDate.now());
    }

    public Cart(int id, CartType type, LocalDate date) {
        this.id = id;
        this.type = Objects.requireNonNull(type);
        this.date = Objects.requireNonNull(date);
        this.products = new LinkedHashMap<>();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public CartType getType() {
        return type;
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public boolean addItem(InventoryItem item, int qty) {
        if (item == null || qty <= 0) {
            return false;
        }
        if (!item.reserveItem(qty)) {
            return false;
        }
        products.merge(item.getProduct(), qty, Integer::sum);
        return true;
    }

    public int removeItem(InventoryItem item, int qty) {
        if (item == null || qty <= 0) {
            return 0;
        }
        Integer currentQty = products.get(item.getProduct());
        if (currentQty == null) {
            return 0;
        }
        int toRemove = Math.min(qty, currentQty);
        item.releaseItem(toRemove);
        if (currentQty - toRemove == 0) {
            products.remove(item.getProduct());
        } else {
            products.put(item.getProduct(), currentQty - toRemove);
        }
        return toRemove;
    }

    public void clearWithoutReleasing() {
        products.clear();
    }

    public double printSalesSlip(Map<String, InventoryItem> storeInventory) {
        System.out.println("=== Ticket de caisse | Panier #" + id + " (" + type + ", " + date + ") ===");
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            InventoryItem invItem = storeInventory.get(product.sku());
            double unitPrice = (invItem != null) ? invItem.getSalesPrice() : 0.0;
            double lineTotal = unitPrice * qty;
            total += lineTotal;
            System.out.printf("  %-8s %-20s x%2d @ %6.2f = %7.2f%n",
                    product.sku(), product.name(), qty, unitPrice, lineTotal);
        }
        System.out.printf("Total : %.2f%n", total);
        return total;
    }

    @Override
    public int compareTo(Cart other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
