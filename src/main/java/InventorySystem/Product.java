package main.java.InventorySystem;

import java.util.Objects;

public record Product(String sku, String name, String manufacturer, Category category) {

    public Product {
        Objects.requireNonNull(sku, "SKU cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(manufacturer, "Manufacturer cannot be null");
        Objects.requireNonNull(category, "Category cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return sku.equalsIgnoreCase(other.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku.toUpperCase());
    }
}
