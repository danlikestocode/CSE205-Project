package app.database;

public class Cart {

    //the index of the array represents the product id
    private static int[] products;

    public static void loadCart(int[] cart) {
        //initializes the cart from the db
        if (cart != null) {
            products = new int[cart.length];
            for (int i = 0; i < cart.length; i++) {
                products[i] = cart[i];
            }
        }
    }

    public static void addProduct (int id) {
        products[id]++;
    }

    public static void subtractProduct (int id) {
        if (products[id] > 0) products[id]--;
    }

}
