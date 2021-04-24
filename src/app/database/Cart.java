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
        System.out.println(id);
        if (id >= products.length) {
            int[] temp = products;
            products = new int[id + 1];
            for (int i = 0; i < temp.length; i++) {
                products[i] = temp[i];
            }
        }
        products[id]++;
        //Database.updateArray("users", "cart", User.getUsername(), products, "usernames");
    }

    public static void subtractProduct (int id) {
        if (products[id] > 0) products[id]--;
    }

}
