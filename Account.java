public class Account {
    // Instance Variables
    private String username;
    private String password;
    private String accountType;

    private Item[] shoppingCart;
    private double shoppingCartTotal;
    private String[] receipts;
    
    private int numOfItems;
    public static final int MAX_NUM_ITEMS = 100;

    public static final int MAX_NUM_RECEIPTS = 100;
    private static int numOfReceipts;

    private static int numOfAccounts;

    // Default constructor
    Account() {}

    // Specific constructor, requiring the username, password, and account type
    Account(String username, String password, String accountType) {
        setUsername(username);
        setPassword(password);
        setAccountType(accountType);

        this.shoppingCart = new Item[MAX_NUM_ITEMS]; // Initialize the array with maximum capacity
        this.numOfItems = 0; // Initialize number of items to 0

        this.receipts = new String[MAX_NUM_RECEIPTS]; // Initialize the array of receipts
        numOfReceipts = 0;

        // Increments every time an account is created
        numOfAccounts++;
    }

    // Accessors
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getAccountType() { return this.accountType; }

    public Item[] getShoppingCart() { return this.shoppingCart; }
    public double getShoppingCartTotal() { return this.shoppingCartTotal; }

    public String[] getReceipts() { return this.receipts; }
    public static int getNumOfReceipts() { return numOfReceipts; }

    public int getNumOfItems() { return this.numOfItems; }

    public static int getNumOfAccounts() { return numOfAccounts; }


    // Mutators
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        this.password = password;
    }

    public void setAccountType(String accountType) {
        if (!accountType.equalsIgnoreCase("Customer Account") && 
        !accountType.equalsIgnoreCase("Store Manager Account")) {
            throw new IllegalArgumentException("The account type must either be a \"Customer Account\" or a \"Store Manager Account\".");
        }

        this.accountType = accountType;
    }


    // Special Purpose Methods

    // Adds an individual item into the shopping cart
    public void addItemToCart(Item item) {
        shoppingCart[this.getNumOfItems()] = item;
        this.numOfItems++;
    }

    // Removes an individual item from the shopping cart
    public void removeItemFromCart(Item item) {
        // Find the index of the item in the cart
        int index = -1;
        for (int i = 0; i < this.getNumOfItems(); i++) {
            if (shoppingCart[i].equals(item)) {
                index = i;
                break;
            }
        }
        // If the item is found, remove it from the cart and shift remaining items
        if (index != -1) {
            for (int i = index; i < this.getNumOfItems() - 1; i++) {
                shoppingCart[i] = shoppingCart[i + 1];
            }
            shoppingCart[this.getNumOfItems() - 1] = null; // Set the last element to null
            this.numOfItems--; // Decrement the cart size
        }
    }

    // Stores a receipt into an array after the order has been placed
    public void storeReceipt(String aReceipt) {
        this.receipts[getNumOfReceipts()] = aReceipt;
        numOfReceipts++;
    }

    // Clears the shopping cart once the purchased have been finished
    public void clearShoppingCart() {
        this.shoppingCart = new Item[MAX_NUM_ITEMS];
        this.numOfItems = 0;
        this.shoppingCartTotal = 0;
    }

    // Displays the user's current items in the shopping cart
    public String displayShoppingCart() {
        if (this.getNumOfItems() == 0) {
            return "(Shopping Cart is empty. No items have been added yet.)";
        }
    
        String result = "Name | Price\n";
        double total = 0.0;
    
        for (int i = 0; i < this.getNumOfItems(); i++) {
            Item item = shoppingCart[i];
            result += item.getItemName() + " | $" + item.getItemPrice() + "\n";
            total += item.getItemPrice();
        }
        this.shoppingCartTotal = total;
    
        result += "---------------------\n";
        result += "Total Price: $" + String.format("%.2f", total);
    
        return result;
    }

    // Displays the user's personal information
    public String toString() {
        return "***Official Account Information***\n\n" +
        "Username: " + this.getUsername() + "\n" +
        "Password: " + this.getPassword() + "\n" +
        "Account Type: " + this.getAccountType();
    }
}