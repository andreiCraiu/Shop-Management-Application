package domain;

import java.util.Objects;

public class Product extends Entity<String>{
    private String name;
    private Double price;
    private int numberOfItems;
    private String manufacturer;

    public Product(String id, String name, Double price, int numberOfItems, String manufacturer) {
        super(id);
        this.name = name;
        this.price = price;
        this.numberOfItems = numberOfItems;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getNumberOfItems() == product.getNumberOfItems() &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getManufacturer(), product.getManufacturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getNumberOfItems(), getManufacturer());
    }
}
