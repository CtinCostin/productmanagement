import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

        ProductDao productDao = new ProductDao();
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("0.Exit");
            System.out.println("1.Add products");
            System.out.println("2.Show products");
            System.out.println("3.Remove products");
            System.out.println("4.Edit price of products");
            System.out.println("Select option:");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Insert product name:");
                String name = scanner.nextLine();
                System.out.println("Insert product description:");
                String description = scanner.nextLine();
                System.out.println("Insert product price:");
                double price = scanner.nextDouble();
                scanner.nextLine();

                ProductModel newProduct = new ProductModel();
                newProduct.setName(name);
                newProduct.setDescription(description);
                newProduct.setPrice(price);
                productDao.addProduct(newProduct);

            }
            if (option == 2) {
                List<ProductModel> productModelList = productDao.getProducts();
                for (ProductModel product : productModelList) {
                    System.out.println(product.getId() + "." + product.getName() + " - " + product.getDescription()
                            + " - price:" + product.getPrice());
                }

            }
            if (option == 3) {
                List<ProductModel> productModelList = productDao.getProducts();
                for (ProductModel product : productModelList) {
                    System.out.println(product.getId() + "." + product.getName() + " - " + product.getDescription()
                            + " - price:" + product.getPrice());
                }
                System.out.println("Enter id of the product you want to remove:");
                int id = scanner.nextInt();
                scanner.nextLine();

                productDao.removeProduct(id);
            }
            if (option == 4) {
                List<ProductModel> productModelList = productDao.getProducts();
                for (ProductModel product : productModelList) {
                    System.out.println(product.getId() + "." + product.getName() + " - " + product.getDescription()
                            + " - price:" + product.getPrice());
                }
                System.out.println("Enter the id of product you want to edit price:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter the new price for the product you chosen:");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();

                productDao.editPrice(id, newPrice);

            }
        }
    }
}
