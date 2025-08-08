import CLASS.SUB.Food;
import CLASS.Stock;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

    public static void Run() {
        while (true) {
            System.out.println("\n--- Məhsul İdarəetmə Sistemi ---");
            System.out.println("1. Məhsul əlavə et");
            System.out.println("2. Məhsulları göstər");
            System.out.println("3. Məhsulu anbardan sil");
            System.out.println("4. Qiyməti yenilə");
            System.out.println("5. Çıxış");
            System.out.print("Seçiminizi edin: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // enter temizleme
            Food fd;
            switch (choice) {
                case 1:
                    System.out.print("Məhsulun adını daxil edin: ");
                    String name = scanner.nextLine();

                    System.out.print("Son istifadə tarixini daxil edin (gg/aa/il): ");
                    String expirationDate = scanner.nextLine();

                    System.out.print("Məhsulun qiymətini daxil edin (AZN): ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    fd=new Food(name,price,expirationDate);
                    fd.setID();
                    fd.productFromWhere();
                    Stock.addToStock(fd);
                    Stock.save();

                    break;
                case 2:
                    Stock.getListAll();
                    break;
                case 3:
                    System.out.print("Məhsulu silmək üçün kodunu daxil edin: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Stock.deleteWithID(id);
                    break;
                case 4:
                    System.out.print("Məhsulun yeni qiymətini yazmaq üçün kodunu daxil edin: ");
                    int id2 = scanner.nextInt();
                    System.out.print("Məhsulun yeni qiymətini daxil edin: ");
                    double price2= scanner.nextDouble();
                    Stock.updateProductByID(id2,price2);
                    break;
                case 5:
                    System.out.println("Proqramdan çıxılır...");
                    return;
                default:
                    System.out.println("Yanlış seçim! Yenidən cəhd edin.");
            }
        }
    }

}
