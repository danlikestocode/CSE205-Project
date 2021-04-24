package app.database;

public class Cart {
    private static int[] products;

    public static void loadCart(int[][] cart2DArray) {
        //initializes the cart from the db
        if (cart2DArray != null) {
            for (int i = 0; i < cart2DArray[0].length; i++) {
                products[i] = cart2DArray[1][i];
            }
        }
    }

    public static void addProduct (int id) {

    }

    public static void subtractProduct (int id) {
        if (products[id] > 0) products[id]--;
    }

}
