package app.database;

public class Cart {
    private Product[] products;
    private int[] quantities;

    public Product[] getIDs() { return products; }
    public int[] getQuantities() { return quantities; }

    public Cart(int[][] cart2DArray) {

        for (int i = 0; i < cart2DArray[0].length; i++) {

            Database.searchForInt("products", "ids", cart2DArray[0][i]);

            int id = Database.returnCurrentInt("ids");
            String name = Database.returnCurrentString("names");
            int stock = Database.returnCurrentInt("stock");

            products = new Product[cart2DArray[0].length];
            products[i] = new Product(id, name, stock);
        }

        for (int i = 0; i < cart2DArray[1].length; i++) {
            quantities = new int[cart2DArray[1].length];
            quantities[i] = cart2DArray[1][i];
        }

    }
}
