public class ClothingItem extends Item {
    // Instance variables
    private String color;
    private String size;

    // Default constructor
    ClothingItem() {}
    
    // Specific constructor
    ClothingItem(String itemName, double itemPrice, String color, String size) {
        super(itemName, itemPrice);
        setColor(color);
        setSize(size);
    }

    // Accesors
    public String getColor() { return this.color; }
    public String getSize() { return this.size; }

    // Mutators
    public void setColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color has to be provided.");
        }
        this.color = color;
    }

    public void setSize(String size) {
        if (size == null || size.isEmpty()) {
            throw new IllegalArgumentException("Size must be provided.");
        }
        this.size = size;
    }


    // Displays item information for Clothing product catalog
    public String displayForCatalog() {
        return "***" + this.getItemName() + "***\n" +
        "Price: $" + this.getItemPrice() + "\n" +
        "Color: " + this.getColor() + "\n" +
        "Size: " + this.getSize();
    } 

    // Displays full item information
    public String toString() {
        return "***Clothing Item Information***\n" +
        super.toString() + "\n" +
        "Color: " + this.getColor() + "\n" +
        "Size: " + this.getSize();
    }
}
