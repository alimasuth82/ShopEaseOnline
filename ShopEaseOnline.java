import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ShopEaseOnline {
    // Defined icons across all methods
    static ImageIcon logo = new ImageIcon("icons/logo.png");
    static ImageIcon check = new ImageIcon("icons/check.png");

    public static void main(String[] args) {
        final int MAX_NUM_ARRAYS = 100;
        Item[] allItems = new Item[MAX_NUM_ARRAYS];
        Account[] allAccounts = new Account[MAX_NUM_ARRAYS];

        // Hardcoding all items in the store
        setClothingItems(allItems);
        setHouseAppliances(allItems);
        setSkincareItems(allItems);


        // The main program runs here
        int userOption = 0;
        boolean quit = false;

        // Switch cases of different options, calling different methods
        do {
            userOption = getUserOption();

            switch (userOption) {
                case 0:
                    createAccount(allAccounts);
                    break;
                case 1:
                    logIn(allAccounts, allItems);
                    break;
                default:
                    quit = true;
                    break;
            }
        } while (!quit);

        System.exit(0);
    }


    // All Hardcoded items in the store
    private static void setClothingItems(Item[] allItems) {
        allItems[0] = new ClothingItem("Nike T-shirt", 19.99, "Blue", "Medium");
        allItems[1] = new ClothingItem("Slim Fit Jeans", 29.99, "Black", "Large");
        allItems[2] = new ClothingItem("Red Dress", 39.99, "Red", "Small");
        allItems[3] = new ClothingItem("Gray Hoodie", 24.99, "Gray", "Extra Large");
        allItems[4] = new ClothingItem("Floral Skirt", 14.99, "Pink", "Extra Small");
    }    
    
    private static void setHouseAppliances(Item[] allItems) {
        allItems[5] = new HouseApplianceItem("Stainless Steel Refrigerator", 799.99, 3, "High", "Samsung");
        allItems[6] = new HouseApplianceItem("Washing Machine", 649.99, 5, "Premium", "LG");
        allItems[7] = new HouseApplianceItem("Gas Range Oven", 899.99, 2, "Mid", "Whirlpool");
        allItems[8] = new HouseApplianceItem("Dishwasher", 499.99, 4, "Standard", "GE");
        allItems[9] = new HouseApplianceItem("Microwave Oven", 199.99, 1, "Basic", "Panasonic");
    }    
    
    private static void setSkincareItems(Item[] allItems) {
        String[] ingredients1 = {"Hyaluronic Acid", "Vitamin C", "Retinol", "Collagen", "Peptides"};
        String[] ingredients2 = {"Green Tea Extract", "Hyaluronic Acid", "Vitamin E", "Jojoba Oil", "Aloe Vera"};
        String[] ingredients3 = {"Salicylic Acid", "Glycolic Acid", "Tea Tree Oil", "Niacinamide", "Witch Hazel"};
    
        allItems[10] = new SkincareItem("Anti-Aging Serum", 29.99, "Serum", ingredients1, "All Skin Types");
        allItems[11] = new SkincareItem("Moisturizing Cream", 19.99, "Cream", ingredients2, "Dry Skin");
        allItems[12] = new SkincareItem("Acne Treatment", 14.99, "Spot Treatment", ingredients3, "Oily Skin");
    }    


    // Main methods involving the program

    // The main menu at the beginning of the program, where the user can create account, login, or quit
    private static int getUserOption() {
        String[] userChoices = {"Create Account", "Log In", "Quit"};
        int userOption = JOptionPane.showOptionDialog(null, "Welcome to ShopEaseOnline!\n" +
        "Please select an option to get started:", "ShopEaseOnline", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, logo, userChoices, userChoices[0]);

        return userOption;
    }

    // A method to retrieve the user input
    private static String getInput(String promptMessage, String dialogTitle) {
        String input = JOptionPane.showInputDialog(null, promptMessage, dialogTitle, JOptionPane.QUESTION_MESSAGE);
        return input;
    }    

    /* 
        A method where the user will create their account. The user will first select the type of account
        they wish to create. After that, they will set their username and password, which they will use to
        log in later.
    */
    private static void createAccount(Account[] allAccounts) {
        // Defined icons
        ImageIcon smileyFace = new ImageIcon("icons/smileyFace.png");
        ImageIcon accountIcon = new ImageIcon("icons/accountIcon.png");

        // Initialize account
        allAccounts[Account.getNumOfAccounts()] = new Account();

        // Display welcome message and instructions for creating an account
        JOptionPane.showMessageDialog(null, "Welcome to the creation of your official ShopEaseOnline account!\n" +
            "In order to use our shopping service online, we only require that you must have a username and password.\n" +
            "If you do not have one, no problem! You will be creating them here.", "Account Creation", JOptionPane.INFORMATION_MESSAGE, smileyFace);
    
        // Present account type options: Customer Account, Store Manager Account, or Back
        String[] accountTypeOptions = {"Customer Account", "Store Manager Account", "Back"};
        int getAccountTypeOption = JOptionPane.showOptionDialog(
            null,
            "Please select your desired account type:\n" +
            "Customer Account:\n" +
            "- Users can only browse product catalog, select/remove items from their shopping cart, and make purchases.\n\n" +
            "Store Manager Account:\n" +
            "- Users can shop regularly just as a customer can, but as a manager, they will also have the ability to handle store inventory by adding or removing items.\n\n" +
            "Once your account is created, please log in with your established credentials and you will be directed to a specific menu based on your account type.",
            "Account Creation",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            accountIcon,
            accountTypeOptions,
            accountTypeOptions[0]
        );
    
        // If user selects "Back", return without creating an account
        if (getAccountTypeOption == 2) {
            return;
        }
    
        // Initialize variables for account creation
        String accountType = "";
        String username = "";
        boolean usernameExists = false;
    
        boolean validInput = false;
        // Loop to ensure a unique username is entered
        do {
            try {
                // Prompt user to enter a username
                username = getInput("To get started, please create your username:", "Account Creation");
                if (username == null) {
                    return;
                }

                // Check if username already exists
                usernameExists = checkUsername(username, allAccounts);
        
                // If username exists or is empty, prompt user to try again
                if (usernameExists) {
                    JOptionPane.showMessageDialog(null, "The username you entered already exists. Please try again.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    allAccounts[Account.getNumOfAccounts()].setUsername(username);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);
    
        // Loop to ensure a password is entered
        String password = "";

        validInput = false;
        do {
            try {
                // Prompt user to enter a password
                password = getInput("Enter your password:", "Account Creation");
                if (password == null) {
                    return;
                }

                allAccounts[Account.getNumOfAccounts()].setPassword(password);
                validInput = true;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);
    
        // Create account based on selected account type
        if (getAccountTypeOption == 0) {
            accountType = "Customer Account";
            // Create a new Customer Account object and store it in the allAccounts array
            allAccounts[Account.getNumOfAccounts()] = new Account(username, password, accountType);

            // Display account creation success message for Customer Account
            JOptionPane.showMessageDialog(null, 
                allAccounts[Account.getNumOfAccounts() - 1].toString() + "\n\n" +
                "Your account creation is a success!\n" +
                "Please log in with your credentials in order to browse and purchase items.\n" +
                "Happy Shopping!", "Account Creation Success", JOptionPane.INFORMATION_MESSAGE, check);
        } else {
            accountType = "Store Manager Account";
            // Create a new Store Manager Account object and store it in the allAccounts array
            allAccounts[Account.getNumOfAccounts()] = new Account(username, password, accountType);
            
            // Display account creation success message for Store Manager Account
            JOptionPane.showMessageDialog(null, 
                allAccounts[Account.getNumOfAccounts() - 1].toString() + "\n\n" +
                "Your account creation is a success!\n" +
                "Please log in with your credentials in order to add or remove items from the store.\n" +
                "Thank you!", "Account Creation Success", JOptionPane.INFORMATION_MESSAGE, check);
        }
    }   

    /* 
        A special purpose method that returns a boolean value of "true" when an
        existing username is found.
    */
    private static boolean checkUsername(String username, Account[] allAccounts) {
        boolean usernameExists = false;
        for (int i = 0; i < Account.getNumOfAccounts(); i++) {
            if (username.equalsIgnoreCase(allAccounts[i].getUsername())) {
                usernameExists = true;
                break;
            }
        }

        return usernameExists;
    }


    /* 
        This method is where the user will log in with their established credentials.
        Depending on their account type (Customer Account or Store Manager Account), the user
        will either directly access to their shopping menu if they're a customer, or access the
        store inventory management system if they are a store manager. 
    */
    private static void logIn(Account[] allAccounts, Item[] allItems) {
        // Prompt user to enter username and validate input
        String usernameToLogin;
        do {
            usernameToLogin = getInput("ShopEaseOnline Login\nEnter your username:", "Log In");
            if (usernameToLogin == null) {
                return;
            } else if (usernameToLogin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username, or click \"Cancel\" to quit.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (usernameToLogin.isEmpty());
    
        // Prompt user to enter password and validate input
        String passwordToLogin;
        do {
            passwordToLogin = getInput("ShopEaseOnline Login\nEnter your password:", "Log In");
            if (passwordToLogin == null) {
                return;
            } else if (passwordToLogin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a password, or click \"Cancel\" to quit.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (passwordToLogin.isEmpty());
    
        // Initialize variables for login validation
        boolean accountFound = false;
        Account accountToLogin = null;
        int accountIndex = 0;
    
        // Iterate through all accounts to validate login credentials
        for (int i = 0; i < Account.getNumOfAccounts(); i++) {
            if (usernameToLogin.equalsIgnoreCase(allAccounts[i].getUsername()) &&
                passwordToLogin.equals(allAccounts[i].getPassword())) {
                // If credentials match, set accountFound to true and store the account and account index
                accountFound = true;
                accountToLogin = allAccounts[i];
                accountIndex = i;
                break;
            }
        }
    
        // Check login validation and redirect to appropriate menu
        if (accountFound && accountToLogin.getAccountType().equalsIgnoreCase("Customer Account")) {
            // If login is valid and the account is a customer account, redirect to shopping menu
            shoppingMenu(allItems, allAccounts, accountIndex);
        } else if (accountFound && accountToLogin.getAccountType().equalsIgnoreCase("Store Manager Account")) {
            // If login is valid and the account is a store manager account, redirect to store inventory menu
            storeInventory(allItems, allAccounts, accountIndex);
        } else {
            // If login is not valid or account not found, display error message
            JOptionPane.showMessageDialog(null, "The username or password you entered was incorrect.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /* 
        The shopping menu where the user selects to view all items available in the store,
        add/remove items from their shopping cart, and checkout their order.
    */
    private static void shoppingMenu(Item[] allItems, Account[] allAccounts, int accountIndex) {
        // Array to store user choices in the shopping menu
        String[] userChoices = {"View Product Catalog", "Shopping Cart", "Checkout", "Log Out"};
        int userOption = 0;
    
        // Flag to control loop termination
        boolean quit = false;
        do {
            // Display options to the user and store their choice
            userOption = JOptionPane.showOptionDialog(null, "Please select an option:", "Shopping Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userChoices, userChoices[0]);
    
            // Perform actions based on user's choice
            switch (userOption) {
                case 0:
                    // View available items
                    viewProductCatalog(allItems);
                    break;
                case 1:
                    // Manage shopping cart
                    shoppingCart(allItems, allAccounts, accountIndex);
                    break;
                case 2:
                    // Proceed to checkout
                    checkout(allItems, allAccounts, accountIndex);
                    break;
                default:
                    // Handle logout option
                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Leaving the Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        // If user confirms logout, set quit flag to true
                        quit = true;
                    }
                    break;
            }
        } while (!quit); // Continue looping until quit flag is true
    }    

    /* 
        The method where the user can search for an item to display its details, and as well as view
        an item catalog from each section.
    */
    private static void viewProductCatalog(Item[] allItems) {
        // Array to store user choices in the view items menu
        String[] userChoices = {"Search Item", "Display Clothing Items", "Display House Appliances", "Display Skincare Items", "Back"};
        int userOption = 0;
    
        // Flag to control loop termination
        boolean back = false;
        do {
            // Display options to the user and store their choice
            userOption = JOptionPane.showOptionDialog(null, "Please select an option:", "Product Catalog", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userChoices, userChoices[0]);
    
            // Perform actions based on user's choice
            switch (userOption) {
                case 0:
                    // Search for a specific item
                    searchItem(allItems);
                    break;
                case 1:
                    // Display all clothing items
                    displayAllClothingItems(allItems);
                    break;
                case 2:
                    // Display all house appliances
                    displayAllHouseAppliances(allItems);
                    break;
                case 3:
                    // Display all skincare items
                    displayAllSkincareItems(allItems);
                    break;
                default:
                    // Set back flag to true to exit the loop
                    back = true;
                    break;
            }
        } while (!back); // Continue looping until back flag is true
    }    

    /*
        Method where the user enters the item name to display its information.
    */
    private static void searchItem(Item[] allItems) {
        // Prompt the user to enter the item name to search
        String searchQuery;
        do {
            searchQuery = getInput("Enter the name of the item to search:", "Search Item");

            // Check if the searchQuery is null (user clicked cancel)
            if (searchQuery == null) {
                return; // Cancel the method if searchQuery is null
            } else if (searchQuery.isEmpty()) {
                // Display a message if the searchQuery is empty
                JOptionPane.showMessageDialog(null, "Please enter an item name before continuing.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (searchQuery.isEmpty()); // Repeat until a non-empty searchQuery is entered

        // Initialize output message for item not found
        String output = "The item \"" + searchQuery + "\" cannot be found in the store.";
        int messageType = JOptionPane.WARNING_MESSAGE;
        ImageIcon iconResultType = null;

        String itemName = "";

        // Iterate through all items to find the matching item name
        for (int i = 0; i < Item.getNumOfItems(); i++) {
            if (searchQuery.equalsIgnoreCase(allItems[i].getItemName())) {
                // Update output with the information of the found item
                output = allItems[i].toString();
                itemName = allItems[i].getItemName();
                messageType = JOptionPane.INFORMATION_MESSAGE;
                iconResultType = new ImageIcon("icons/check.png");

                break; // Stop searching after finding the first match
            }
        }

        // Display the search result to the user
        JOptionPane.showMessageDialog(null, output, "Search Result for \"" + searchQuery + "\"", messageType, iconResultType);
    }

    /* 
        Method to display all clothing items.
    */
    private static void displayAllClothingItems(Item[] allItems) {
        // Initialize output message
        String output = "***All Clothing Items***\n\n";

        // Iterate through all items to find clothing items
        for (int i = 0; i < Item.getNumOfItems(); i++) {
            if (allItems[i] instanceof ClothingItem) {
                // Add clothing item information to the output message
                output += allItems[i].displayForCatalog() + "\n\n";
            }
        }

        // Display all clothing items to the user
        JOptionPane.showMessageDialog(null, output, "All Clothing Items", JOptionPane.INFORMATION_MESSAGE, check);
    }

    /* 
        Method to display all house appliances
    */
    private static void displayAllHouseAppliances(Item[] allItems) {
        // Initialize output message
        String output = "***All House Appliances***\n\n";

        // Iterate through all items to find house appliances
        for (int i = 0; i < Item.getNumOfItems(); i++) {
            if (allItems[i] instanceof HouseApplianceItem) {
                // Add house appliance item information to the output message
                output += allItems[i].displayForCatalog() + "\n\n";
            }
        }

        // Display all house appliances to the user
        JOptionPane.showMessageDialog(null, output, "All House Appliances", JOptionPane.INFORMATION_MESSAGE, check);
    }

    /* 
        Method to display all skincare items
    */
    private static void displayAllSkincareItems(Item[] allItems) {
        // Initialize output message
        String output = "***All Skincare Items***\n\n";

        // Iterate through all items to find skincare items
        for (int i = 0; i < Item.getNumOfItems(); i++) {
            if (allItems[i] instanceof SkincareItem) {
                // Add skincare item information to the output message
                output += allItems[i].displayForCatalog() + "\n\n";
            }
        }

        // Display all skincare items to the user
        JOptionPane.showMessageDialog(null, output, "All Skincare Items", JOptionPane.INFORMATION_MESSAGE, check);
    }


    /* 
        A method where the user manages their shopping cart.
    */
    private static void shoppingCart(Item[] allItems, Account[] allAccounts, int accountIndex) {
        // Array of user choices for shopping cart operations
        String[] userChoices = {"View Product Catalog", "Add Item", "Delete Item", "Back"};
        int userOption = 0;

        // Flag to indicate if the user wants to go back
        boolean back = false;
        do {
            // Display the current items in the shopping cart
            String displayItemsInCart = "Edit Shopping Cart:\n" + allAccounts[accountIndex].displayShoppingCart();
            // Show options for shopping cart operations
            userOption = JOptionPane.showOptionDialog(null, displayItemsInCart, "Shopping Cart", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userChoices, userChoices[0]);

            // Perform actions based on user choice
            switch (userOption) {
                case 0:
                    viewProductCatalog(allItems);
                    break;
                case 1:
                    addItem(allItems, allAccounts, accountIndex);
                    break;
                case 2:
                    deleteItem(allItems, allAccounts, accountIndex);
                    break;
                default:
                    back = true;
                    break;
            }
        } while (!back);
    }

    /* 
        Method to add an item to the shopping cart 
    */
    private static void addItem(Item[] allItems, Account[] allAccounts, int accountIndex) {
        // Prompt the user to enter the name of the item to add
        String itemName = "";
        boolean itemFound = false;

        do {
            itemName = getInput("Enter the name of the item you wish to add:", "Shopping Cart");
            if (itemName == null) {
                return;
            } else if (itemName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an item name before proceeding.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                // Iterate through all items to find the item to add
                for (int i = 0; i < Item.getNumOfItems(); i++) {
                    if (itemName.equalsIgnoreCase(allItems[i].getItemName())) {
                        // Add the item to the shopping cart of the specified account
                        allAccounts[accountIndex].addItemToCart(allItems[i]);
                        itemFound = true;
                        break;
                    }
                }
            }
        } while (itemName.isEmpty());

        // Display success or failure message based on whether the item was found
        if (itemFound) {
            JOptionPane.showMessageDialog(null, "Item \"" + itemName + "\" has been successfully added into your cart.", "Success", JOptionPane.INFORMATION_MESSAGE, check);
        } else {
            JOptionPane.showMessageDialog(null, "Item \"" + itemName + "\" is not available in the store.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /*
        Method to delete an item from the shopping cart
    */
    private static void deleteItem(Item[] allItems, Account[] allAccounts, int accountIndex) {
        // Prompt the user to enter the name of the item to delete
        String itemName = "";
        boolean itemFound = false;
        
        do {
            itemName = getInput("Enter the name of the item you wish to remove:", "Shopping Cart");
            if (itemName == null) {
                return;
            } else if (itemName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an item name before proceeding.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                // Iterate through all items to find the item to delete
                for (int i = 0; i < allAccounts[accountIndex].getNumOfItems(); i++) {
                    if (itemName.equalsIgnoreCase(allAccounts[accountIndex].getShoppingCart()[i].getItemName())) {
                        // Remove the item from the shopping cart of the specified account
                        allAccounts[accountIndex].removeItemFromCart(allAccounts[accountIndex].getShoppingCart()[i]);
                        itemFound = true;
                        break;
                    }
                }
            }
        } while (itemName.isEmpty());

        // Display success or failure message based on whether the item was found
        if (itemFound) {
            JOptionPane.showMessageDialog(null, "Item \"" + itemName + "\" has been successfully removed from your cart.", "Success", JOptionPane.INFORMATION_MESSAGE, check);
        } else {
            JOptionPane.showMessageDialog(null, "Item \"" + itemName + "\" is not found in your shopping cart.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }


    /* 
        A method for checking out the order. The user has an option to proceed their transaction,
        edit their shopping cart, and view previous orders they placed in the past.
    */
	private static void checkout(Item[] allItems, Account[] allAccounts, int accountIndex) {
	    // Array of user choices for checkout options
	    String[] userChoices = {"Proceed Transaction", "Edit Shopping Cart", "View Previous Orders", "Back"};
	    int userOption = 0;

	    // Flag to indicate if the user wants to go back
	    boolean back = false;
	    do {
	        // Display the current items in the shopping cart
	        String displayItemsInCart = "Your current shopping cart:\n" + allAccounts[accountIndex].displayShoppingCart();
	        // Show options for checkout
	        userOption = JOptionPane.showOptionDialog(null, displayItemsInCart, "Checkout", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userChoices, userChoices[0]);

	        // Perform actions based on user choice
	        switch (userOption) {
	            case 0:
	                proceedTransaction(allAccounts, accountIndex);
	                break;
	            case 1:
	                shoppingCart(allItems, allAccounts, accountIndex);
	                break;
	            case 2:
	                viewPreviousOrders(allAccounts, accountIndex);
	                break;
	            default:
	                back = true;
	                break;
	        }
	    } while (!back);
	}

    /* 
        Method to proceed transaction.
    */
    private static void proceedTransaction(Account[] allAccounts, int accountIndex) {
        // Array of user choices for transaction methods
        String[] userChoices = {"Credit/Debit Card", "Gift Card", "Back"};
        int userOption = 0;
    
        // Check if the shopping cart is empty
        if (allAccounts[accountIndex].getNumOfItems() == 0) {
            // If empty, display a message with the empty cart
            JOptionPane.showMessageDialog(null, "A transaction cannot be placed since your shopping cart is empty.\nPlease add an item before continuing.", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            // If not empty, prompt the user to select a payment method
            userOption = JOptionPane.showOptionDialog(null, "Your total is $" + String.format("%.2f", allAccounts[accountIndex].getShoppingCartTotal()) +
            "\nSelect the method you wish to pay:", "Checkout", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, userChoices, userChoices[0]);
        
            // Perform actions based on user choice
            switch (userOption) {
                case 0:
                    checkoutWithCard(allAccounts, accountIndex);
                    break;
                case 1:
                    checkoutWithGiftCard(allAccounts, accountIndex);
                    break;
                default:
                    // Back option
                    break;
            }
        }
    }    

    /* 
        Performing a checkout if the user selects "Credit/Debit card."
    */
    private static void checkoutWithCard(Account[] allAccounts, int accountIndex) {
        // Array of card options
        String[] cardOptions = {"Visa", "MasterCard", "Back"};
        String cardType = ""; // Initialize card type
        int getCardTypeValue = 0; // Initialize card type value
    
        String cardNumberInput = "";
        int cardNumber = 0; // Initialize card number
        
        // Prompt user to select card type
        getCardTypeValue = JOptionPane.showOptionDialog(null, "Select your card type:", "Checkout", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cardOptions, cardOptions[0]);
    
        // Switch statement based on selected card type
        switch (getCardTypeValue) {
            case 0:
                cardType = "Visa"; // Set card type to Visa
                break;
            case 1:
                cardType = "MasterCard"; // Set card type to MasterCard
                break;
            default:
                // Back option or any invalid selection
                return; // Exit the method if the user selects "Back"
        }
    
        boolean validInput = false;
        do {
            cardNumberInput = getInput("Enter your card number:", "Checkout"); // Prompt user to enter card number
            if (cardNumberInput == null) {
                return; // Exit if the user cancels the input dialog
            } else if (cardNumberInput.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Card Number must be provided!", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    cardNumber = Integer.parseInt(cardNumberInput);
                    if (cardNumber < 0) {
                        JOptionPane.showMessageDialog(null, "Card number should be at least 0.", "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input type entered for the card number.\nPlease try again.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        } while (!validInput);
    
        // Create receipt with information regarding the selected card
        createReceiptForCreditCard(allAccounts, accountIndex, "Credit/Debit Card", cardType, cardNumber);
    }    

    /* 
        Performing a checkout if the user selects "Gift Card."
    */
    private static void checkoutWithGiftCard(Account[] allAccounts, int accountIndex) {
        // Initialize variables
        String giftCardCompany = "";
        String giftCardCode = "";

        do {
            // Prompt user to enter gift card company
            giftCardCompany = getInput("Enter the company of your gift card:", "Checkout");
            if (giftCardCompany == null) {
                return;
            } else if (giftCardCompany.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the Gift Card's company name before proceeding.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (giftCardCompany.isEmpty());

        do {
            // Prompt user to enter gift card code
            giftCardCode = getInput("Enter the code of your gift card:", "Checkout");
            if (giftCardCode == null) {
                return;
            } else if (giftCardCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Gift Card's code must be provided!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (giftCardCode.isEmpty());
        
        // Create receipt with gift card information
        createReceiptForGiftCard(allAccounts, accountIndex, "Gift Card", giftCardCompany, giftCardCode);
    }    

    /* 
        Method for creating an official receipt with credit/debit card information and storing it in 
        the array of receipts within the Account data definition class.
    */
    private static void createReceiptForCreditCard(Account[] allAccounts, int accountIndex, String paymentMethod, String companyName, int cardNumber) {
        // Generate a random receipt number
        int receiptNumber = (int) (Math.random() * 90000) + 10000;
    
        // Construct the receipt string
        String receipt = "***Official Receipt***\n" +
                "Receipt #: " + receiptNumber + "\n" +
                "Method of Payment: " + paymentMethod + "\n" +
                "Company Name: " + companyName + "\n" +
                "Card Number: " + cardNumber + "\n\n" +
                allAccounts[accountIndex].displayShoppingCart();
    
        // Store the receipt in the account's receipts
        allAccounts[accountIndex].storeReceipt(receipt);
        // Clear the shopping cart after creating the receipt
        allAccounts[accountIndex].clearShoppingCart();
    
        // Display the receipt to the user
        JOptionPane.showMessageDialog(null, receipt, "Checkout Successfully Processed", JOptionPane.INFORMATION_MESSAGE, check);
    }
    
    /* 
        Method for creating an official receipt with credit/debit card information and storing it in 
        the array of receipts within the Account data definition class.
    */
    private static void createReceiptForGiftCard(Account[] allAccounts, int accountIndex, String paymentMethod, String giftCardCompany, String giftCardCode) {
        // Generate a random receipt number
        int receiptNumber = (int) (Math.random() * 90000) + 10000;
    
        // Construct the receipt string
        String receipt = "***Official Receipt***\n" +
                "Receipt #: " + receiptNumber + "\n" +
                "Method of Payment: " + paymentMethod + "\n" +
                "Gift Card Company Name: " + giftCardCompany + "\n" +
                "Gift Card Code: " + giftCardCode + "\n\n" +
                allAccounts[accountIndex].displayShoppingCart();
    
        // Store the receipt in the account's receipts
        allAccounts[accountIndex].storeReceipt(receipt);
        // Clear the shopping cart after creating the receipt
        allAccounts[accountIndex].clearShoppingCart();
    
        // Display the receipt to the user
        JOptionPane.showMessageDialog(null, receipt, "Checkout Successfully Processed", JOptionPane.INFORMATION_MESSAGE, check);
    }

    /* 
        Displays all previous orders placed by displaying all receipts within the user's account.
    */
    private static void viewPreviousOrders(Account[] allAccounts, int accountIndex) {
        String output = "";
        int messageType = 0;
        String messageTitle = "";
        ImageIcon iconResultType = null;

        // Check if there are any receipts
        if (Account.getNumOfReceipts() == 0) {
            output = "No orders have been placed yet.";
            messageType = JOptionPane.WARNING_MESSAGE;
            messageTitle = "Error";
        } else {
            // Loop through each receipt and append it to the output
            for (int i = 0; i < Account.getNumOfReceipts(); i++) {
                output += allAccounts[accountIndex].getReceipts()[i] + "\n\n";
            }
            
            messageType = JOptionPane.INFORMATION_MESSAGE;
            messageTitle = "View Previous Orders";
            iconResultType = new ImageIcon("icons/check.png");
        }
    
        // Display the output to the user
        JOptionPane.showMessageDialog(null, output, messageTitle, messageType, iconResultType);
    }    


    /* 
        Method where the user can manage the store inventory by adding or removing items from the store.
        The user also has an option to perform regular shopping to purchase items as well.

        Note: This menu is only available to users who selected "Store Manager Account" during their
        account creation. Only the managers are able to add/remove items from the store but the customer
        cannot.
    */
    private static void storeInventory(Item[] allItems, Account[] allAccounts, int accountIndex) {
        String[] userChoices = {"Add Item to Store", "Remove Item from Store", "Search Item", "Shop Items", "Log Out"};
        int userOption = 0;
    
        boolean quit = false;
        // Loop until the user decides to logout
        do {
            // Display the options to the user and get their choice
            userOption = JOptionPane.showOptionDialog(
                null,
                "Please select an option:",
                "Store Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                userChoices,
                userChoices[0]
            );
    
            // Perform actions based on the user's choice
            switch (userOption) {
                case 0:
                    addItemToStore(allItems);
                    break;
                case 1:
                    removeItemFromStore(allItems);
                    break;
                case 2:
                    searchItem(allItems);
                    break;
                case 3:
                    shoppingMenu(allItems, allAccounts, accountIndex);
                    break;
                default:
                    // Ask for confirmation before logging out
                    if (JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to log out?",
                        "Logging Out",
                        JOptionPane.YES_NO_OPTION
                    ) == JOptionPane.YES_OPTION) {
                        quit = true;
                    }
                    break;
            }
        } while (!quit);
    }

    /* 
        A method for creating an official item and storing it in the store. First, 
        the program request for the type of item the user wishes to add. Next, they
        will be asked for the item's name and price, followed by a set of variables according
        to the item's type.
    */
    private static void addItemToStore(Item[] allItems) {
        String itemName = "";
        double itemPrice = 0;
    
        // Array to hold the options for item types
        String[] itemTypes = {"Clothing", "House Appliance", "Skincare", "Back"};
        // Display a dialog box to select an item type
        int getItemType = JOptionPane.showOptionDialog(
            null,
            "Please select an item type:",
            "Add Item to Store",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            itemTypes,
            itemTypes[0]
        );
        
        switch (getItemType) {
            case 0:
                addClothingItem(allItems);
                break;
            case 1:
                addHouseAppliance(allItems);
                break;
            case 2:
                addSkincareItem(allItems);
                break;
            default:
                // Back option
                break;
        }
    }

    /* 
        Method for adding a Clothing item.
    */
    private static void addClothingItem(Item[] allItems) {
        // Initialize Clothing Item object
        allItems[Item.getNumOfItems()] = new ClothingItem();

        // Initialize variables
        String itemName = "";
        double itemPrice = 0;
        String color = "";
        String size = "";

        boolean validInput = false;

        do {
            try {
                itemName = getInput("Enter the name of your item:", "Add Clothing Item");
                if (itemName == null) {
                    return;
                } else {
                    allItems[Item.getNumOfItems()].setItemName(itemName);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                String itemPriceInput = getInput("Enter the price of your item:", "Add Clothing Item");
                if (itemPriceInput == null) {
                    return;
                } else {
                    itemPrice = Double.parseDouble(itemPriceInput);
                    allItems[Item.getNumOfItems()].setItemPrice(itemPrice);
                    validInput = true;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input type entered for the price. Please try again.", "Error", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                color = getInput("Enter the color of your clothing:", "Add Clothing Item");
                if (color == null) {
                    return;
                } else {
                    ((ClothingItem) allItems[Item.getNumOfItems()]).setColor(color);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                size = getInput("Enter the size of your clothing:", "Add Clothing Item");
                if (size == null) {
                    return;
                } else {
                    ((ClothingItem) allItems[Item.getNumOfItems()]).setSize(size);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        // Set Clothing Item object and display final results
        allItems[Item.getNumOfItems()] = new ClothingItem(itemName, itemPrice, color, size);
        JOptionPane.showMessageDialog(null, allItems[Item.getNumOfItems() - 1].toString(), "Item Successfully Added", JOptionPane.INFORMATION_MESSAGE, check);
    }

    /* 
        Method for adding a House Appliance item.
    */
    private static void addHouseAppliance(Item[] allItems) {
        // Initialize House Appliance object
        allItems[Item.getNumOfItems()] = new HouseApplianceItem();

        // Initialize variables
        String itemName = "";
        double itemPrice = 0;
        int warrantyDuration = 0;
        String quality = "";
        String brand = "";

        boolean validInput = false;

        do {
            try {
                itemName = getInput("Enter the name of your item:", "Add House Appliance");
                if (itemName == null) {
                    return;
                } else {
                    allItems[Item.getNumOfItems()].setItemName(itemName);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                String itemPriceInput = getInput("Enter the price of your item:", "Add House Appliance");
                if (itemPriceInput == null) {
                    return;
                } else {
                    itemPrice = Double.parseDouble(itemPriceInput);
                    allItems[Item.getNumOfItems()].setItemPrice(itemPrice);
                    validInput = true;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input type entered for the price. Please try again.", "Error", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                String warrantyDurationInput = getInput("Enter the warranty duration of your item:", "Add House Appliance");
                if (warrantyDurationInput == null) {
                    return;
                } else {
                    warrantyDuration = Integer.parseInt(warrantyDurationInput);
                    ((HouseApplianceItem) allItems[Item.getNumOfItems()]).setWarrantyDuration(warrantyDuration);
                    validInput = true;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input type entered for the warranty duration. Please try again.", "Error", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                quality = getInput("Enter the quality of your item:", "Add House Appliance");
                if (quality == null) {
                    return;
                } else {
                    ((HouseApplianceItem) allItems[Item.getNumOfItems()]).setQuality(quality);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                brand = getInput("Enter the brand of your item:", "Add House Appliance");
                if (brand == null) {
                    return;
                } else {
                    ((HouseApplianceItem) allItems[Item.getNumOfItems()]).setBrand(brand);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        // Set House Appliance Item object and display final results
        allItems[Item.getNumOfItems()] = new HouseApplianceItem(itemName, itemPrice, warrantyDuration, quality, brand);
        JOptionPane.showMessageDialog(null, allItems[Item.getNumOfItems() - 1].toString(), "Item Successfully Added", JOptionPane.INFORMATION_MESSAGE, check);
    }

    /* 
        Method for adding a Skincare item.
    */
    private static void addSkincareItem(Item[] allItems) {
        // Initialize Skincare object
        allItems[Item.getNumOfItems()] = new SkincareItem();

        // Initialize variables
        String itemName = "";
        double itemPrice = 0;
        String category = "";
        String[] ingredients = {""};
        String skinType = "";

        boolean validInput = false;

        do {
            try {
                itemName = getInput("Enter the name of your item:", "Add Skincare Item");
                if (itemName == null) {
                    return;
                } else {
                    allItems[Item.getNumOfItems()].setItemName(itemName);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                String itemPriceInput = getInput("Enter the price of your item:", "Add Skincare Item");
                if (itemPriceInput == null) {
                    return;
                } else {
                    itemPrice = Double.parseDouble(itemPriceInput);
                    allItems[Item.getNumOfItems()].setItemPrice(itemPrice);
                    validInput = true;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input type entered for the price. Please try again.", "Error", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                category = getInput("Enter the category of the skincare item:", "Add Skincare Item");
                if (category == null) {
                    return;
                } else {
                    ((SkincareItem) allItems[Item.getNumOfItems()]).setCategory(category);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                String ingredientsInput = getInput("Enter the ingredients of the skincare item (separated by commas):", "Add Skincare Item");
                if (ingredientsInput == null) {
                    return;
                } else if (ingredientsInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingredients must be provided.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ingredients = ingredientsInput.split(", ");
                    ((SkincareItem) allItems[Item.getNumOfItems()]).setIngredients(ingredients);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        validInput = false;
        do {
            try {
                skinType = getInput("Enter the skin type for which the skincare item is suitable:", "Add Skincare Item");
                if (skinType == null) {
                    return;
                } else {
                    ((SkincareItem) allItems[Item.getNumOfItems()]).setSkinType(skinType);
                    validInput = true;
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } while (!validInput);

        // Set House Appliance Item object and display final results
        allItems[Item.getNumOfItems()] = new SkincareItem(itemName, itemPrice, category, ingredients, skinType);
        JOptionPane.showMessageDialog(null, allItems[Item.getNumOfItems() - 1].toString(), "Item Successfully Added", JOptionPane.INFORMATION_MESSAGE, check);
    }

    /* 
        Method for removing an item by prompting the user to enter the name of the item to remove.
    */
    private static void removeItemFromStore(Item[] allItems) {
        // Get the name of the item to remove from the user
        String itemNameToRemove = "";
        boolean itemFound = false;

        do {
            itemNameToRemove = getInput("Enter the name of the item you want to remove:", "Remove Item from Store");
            if (itemNameToRemove == null) {
                return;
            } else if (itemNameToRemove.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an item name before proceeding.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                // Iterate over the items to find and remove the specified item
                for (int i = 0; i < allItems.length; i++) {
                    if (allItems[i] != null && allItems[i].getItemName().equalsIgnoreCase(itemNameToRemove)) {
                        // Found the item, remove it by shifting the remaining items in the array
                        for (int j = i; j < allItems.length - 1; j++) {
                            allItems[j] = allItems[j + 1];
                        }
                        // Set the last element to null to remove the reference
                        allItems[allItems.length - 1] = null;
            
                        // Update the item count
                        Item.decrementNumOfItems();
            
                        JOptionPane.showMessageDialog(null, "Item \"" + itemNameToRemove + "\" has been successfully removed from the store.", "Success", JOptionPane.INFORMATION_MESSAGE, check);
                        itemFound = true;
                        break;
                    }
                }
            }
        } while (itemNameToRemove.isEmpty());

        // Notify the user if the item was not found
        if (!itemFound) {
            JOptionPane.showMessageDialog(null, "Item \"" + itemNameToRemove + "\" is not found in the store.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }    
}
