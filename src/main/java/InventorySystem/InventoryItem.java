package main.java.InventorySystem;

import java.util.Objects;

public class InventoryItem {

    private final Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyLow;
    private int qtyReorder;
    private double salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyLow, int qtyReorder, double salesPrice) {
        this.product = Objects.requireNonNull(product);
        this.qtyTotal = Math.max(0, qtyTotal);
        this.qtyReserved = 0;
        this.qtyLow = Math.max(0, qtyLow);
        this.qtyReorder = Math.max(0, qtyReorder);
        this.salesPrice = Math.max(0.0, salesPrice);
        checkAndReorder();
    }

    public Product getProduct() {
        return product;
    }

    public int getQtyTotal() {
        return qtyTotal;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public int getAvailableQuantity() {
        return qtyTotal - qtyReserved;
    }

    public int getQtyLow() {
        return qtyLow;
    }

    public int getQtyReorder() {
        return qtyReorder;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public boolean reserveItem(int qty) {
        if (qty <= 0 || qty > getAvailableQuantity()) {
            return false;
        }
        qtyReserved += qty;
        return true;
    }

    public void releaseItem(int qty) {
        if (qty <= 0) {
            return;
        }
        qtyReserved = Math.max(0, qtyReserved - qty);
    }

    public boolean sellItem(int qty) {
        if (qty <= 0 || qty > qtyReserved || qty > qtyTotal) {
            return false;
        }
        qtyReserved -= qty;
        qtyTotal -= qty;
        checkAndReorder();
        return true;
    }

    public void placeInventoryOrder() {
        if (qtyReorder > 0) {
            qtyTotal += qtyReorder;
            System.out.printf("  [Commande réappro] +%d unités de %s (%s) -> Nouveau stock total : %d%n",
                    qtyReorder, product.name(), product.sku(), qtyTotal);
        }
    }

    private void checkAndReorder() {
        if (qtyTotal <= qtyLow) {
            placeInventoryOrder();
        }
    }

    @Override
    public String toString() {
        return "%-8s | %-22s | Prix: %6.2f | Total: %3d | Réservé: %2d | Dispo: %3d".formatted(
                product.sku(), product.name(), salesPrice, qtyTotal, qtyReserved, getAvailableQuantity());
    }
}
