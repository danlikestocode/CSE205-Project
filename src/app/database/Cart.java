package app.database;

import java.sql.Array;

public class Cart {

    //the index of the array represents the product id
    private static int[] products = new int[0];

    //Method to load items to the cart
    public static void loadCart(int[] cart) {
        if (cart != null) {
            products = cart;
        }
    }
    //Adds product to the cart
    public static void addProduct (int id) {
        //if the current array is too small
        updateLength(id);
        Database.searchForInt("products", "productid", id);
        if (products[id] < Database.returnCurrentInt("stock")) {
            products[id]++;
        }
        //printCart(); // for deubgging

        // update the db with the cart   vvv
        boolean success = Database.updateArray("users", "cart", User.getUsername(), products, "usernames");
        //System.out.println(success); // for debugging
    }

    //Subtracts an item from the cart
    public static void subtractProduct (int id) {
        if (products[id] > 0) products[id]--;
//        printCart(); // for debugging
        boolean success = Database.updateArray("users", "cart", User.getUsername(), products, "usernames");

    }

    public static void printCart() {
        //System.out.println(); // for debugging
        for (int i = 0; i < products.length; i++) {
            System.out.print(products[i] + " ");
        }
    }

    //Gets the length of the cart
    public static void updateLength(int length) {
        // for when it's too small
        if (length >= products.length) {
            int[] temp = products;
            products = new int[length + 1];
            for (int i = 0; i < temp.length; i++) {
                products[i] = temp[i];
            }
        }
    }

    //Getter that returns the products
    public static int[] getProducts() {
        return products;
    }

}
