public class HouseApplianceItem extends Item {
    // Instance variables
    private int warrantyDuration;
    private String quality;
    private String brand;

    HouseApplianceItem() {}
    
    // Specific constructor
    HouseApplianceItem(String itemName, double itemPrice, int warrantyDuration, String quality, String brand) {
        super(itemName, itemPrice);
        setWarrantyDuration(warrantyDuration);
        setQuality(quality);
        setBrand(brand);
    }

    // Accesors
    public int getWarrantyDuration() { return this.warrantyDuration; }
    public String getQuality() { return this.quality; }
    public String getBrand() { return this.brand; }

    // Mutators
    public void setWarrantyDuration(int warrantyDuration) {
        if (warrantyDuration < 0) {
            throw new IllegalArgumentException("The warranty duration cannot be a negative number.");
        }
        this.warrantyDuration = warrantyDuration;
    }

    public void setQuality(String quality) {
        if (quality == null || quality.isEmpty()) {
            throw new IllegalArgumentException("The quality of the item must be provided.");
        }
        this.quality = quality;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Brand name must be provided.");
        }
        this.brand = brand;
    }

    // Displays item information for House Appliances product catalog
    public String displayForCatalog() {
        return "***" + this.getItemName() + "***\n" +
        "Brand: " + this.getBrand() + "\n" +
        "Price: $" + this.getItemPrice() + "\n" +
        "Warranty Duration: " + this.getWarrantyDuration() + " years\n" +
        "Quality: " + this.getQuality();
    }

    // Displays full item information
    public String toString() {
        return "***House Appliance Item Information***\n" +
        super.toString() + "\n" +
        "Warranty Duration: " + this.getWarrantyDuration() + " years\n" +
        "Quality: " + this.getQuality() + "\n" +
        "Brand: " + this.getBrand();
    }
}
