package app.database;

import java.sql.Array;

public class Cart {

    //the index of the array represents the product id
    private static int[] products = new int[0];

    public static void loadCart(int[] cart) {
        if (cart != null) {
            products = cart;
        }
    }

    public static void addProduct (int id) {
        //if the current array is too small
        if (id >= products.length) {
            int[] temp = products;
            products = new int[id + 1];
            for (int i = 0; i < temp.length; i++) {
                products[i] = temp[i];
            }
        }
        products[id]++;

        printCart(); // for deubgging
        // update the db with the cart   vvv
        boolean success = Database.updateArray("users", "cart", User.getUsername(), products, "usernames");
        System.out.println(success); // for debugging
    }

    public static void subtractProduct (int id) {
        if (products[id] > 0) products[id]--;
        printCart(); // for debugging
    }

    public static void printCart() {
        System.out.println(); // for debugging
        for (int i = 0; i < products.length; i++) {
            System.out.print(products[i] + " ");
        }
    }

    public static void updateLength(int length) {
        if (length >= products.length) {
            int[] temp = products;
            products = new int[length + 1];
            for (int i = 0; i < temp.length; i++) {
                products[i] = temp[i];
            }
        }
    }

    public static int[] getProducts() {
        return products;
    }

}
