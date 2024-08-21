public class SkincareItem extends Item {
    // Instance variables
    private String category;
    private String[] ingredients;
    private String skinType;

    SkincareItem() {}
    
    // Specific constructor
    SkincareItem(String itemName, double itemPrice, String category, String[] ingredients, String skinType) {
        super(itemName, itemPrice);
        setCategory(category);
        setIngredients(ingredients);
        setSkinType(skinType);
    }

    // Accesors
    public String getCategory() { return this.category; }
    public String[] getIngredients() { return this.ingredients; }
    public String getSkinType() { return this.skinType; }

    // Mutators
    public void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("The category of the skin care item must be provided.");
        }
        this.category = category;
    }

    public void setIngredients(String[] ingredients) {
        if (ingredients == null) {
            throw new IllegalArgumentException("Ingredients must be provided.");
        }
        this.ingredients = ingredients;
    }

    public void setSkinType(String skinType) {
        if (skinType == null || skinType.isEmpty()) {
            throw new IllegalArgumentException("Skin type must be provided.");
        }
        this.skinType = skinType;
    }

    // Displays item information for Skincare product catalog
    public String displayForCatalog() {
        return "***" + this.getItemName() + "***\n" +
        "Price $" + this.getItemPrice() + "\n" +
        "Category: " + this.getCategory() + "\n" +
        "Skin Type: " + this.getSkinType();
    }

    // Displays full item information
    public String toString() {
        String[] ingredients = this.getIngredients();
        String displayIngredients = "";
        for (int i = 0; i < ingredients.length; i++) {
            displayIngredients += ingredients[i] + "\n";
        }

        return "***Skincare Item Information***\n" +
        super.toString() + "\n" +
        "Category: " + this.getCategory() + "\n" +
        "Skin Type: " + this.getSkinType() + "\n\n" +
        "Ingredients:\n" + displayIngredients;
    }
}
