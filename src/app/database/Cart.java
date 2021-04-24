package app.database;

public class Cart {
    private static int[] productIDs;
    private static int[] quantities;

    public static void loadCart(int[][] cart2DArray) {
        //initializes the cart from the db
        if (cart2DArray != null) {
            for (int i = 0; i < cart2DArray[0].length; i++) {
                productIDs = new int[cart2DArray[0].length];
                quantities = new int[cart2DArray[1].length];

                productIDs[i] = cart2DArray[0][i];
                quantities[i] = cart2DArray[1][i];
            }
        }
    }

    public static int[] getIDs() { return productIDs; }
    public static int[] getQuantities() { return quantities; }

}
