package CLASS;

import CLASS.SUB.Food;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Stock {
    private static HashMap<String, Product> list = new HashMap<>();
    private static ArrayList<String> newList = new ArrayList<>();
    private static boolean isFoundProduct = false;


    public static void addToStock(Product product) {

        list.put(product.getID(), product);


    }

    public static void getListAll() {
        try {

            //db den melumatlari cekmek birinci db faylinin oldugu ve icinin bos olmadigi yolxlanilir daha sonra melumatlar cekilir
            Scanner scanner = new Scanner(new File("DB15.txt"));

            int lineCount = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }
            scanner.close();

            if (lineCount > 2) {
                scanner = new Scanner(new File("DB15.txt"));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
                scanner.close();
            } else {
                System.out.println("Anbarda məhsul tapılmadı!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void save() {
        try {
            if (list != null) {
                File file = new File("DB15.txt");

                    FileWriter writer = new FileWriter(file, true);
                    for (Map.Entry<String, Product> entry : list.entrySet()) {
                        Food fd = (Food) entry.getValue();
                        writer.write(String.format("%-5s %-20s %-20s %-10s%n",
                                fd.getID() + "/",
                                fd.getName() + "/",
                                fd.getExpirationDate() + "/",
                                String.format("%.2f AZN", fd.calculateInflationRate(fd.getPrice(),8 ))
                        ));

                    }
                    writer.close();
                    list.clear();

                    System.out.println("Uğurla yadda saxlanıldı");

            } else {
                System.out.println("Yaddaşa yazılacaq heç bir qeyd tapılmadı!!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteWithID(int ID) {
        try {
            //db den melumatlari cekmek birinci db faylinin oldugu ve icinin bos olmadigi yolxlanilir daha sonra melumatlar cekilir
            Path path = Paths.get("DB15.txt");
            List<String> lines = Files.readAllLines(path);
            //newList.clear();
            for (String line : lines) {
                // Başlık ve çizgi setirlerini saxla
                if (line.trim().isEmpty() || line.startsWith("Kod") || line.startsWith("-----")) {
                    newList.add(line);
                    continue;
                }

                // İlk sütundaki kodu "/" işaretine qeder al
                String kod = line.split("/")[0];


                // ID uygun olmasaa basliq setri qoruyacaq
                if (!kod.equals(String.valueOf(ID))) {
                    newList.add(line);
                    // System.out.println("Mehsul tapilmadi...");
                } else {
                    isFoundProduct = true;
                }

            }
            if (isFoundProduct) {
                System.out.println("Məhsul sistemdən ugurla silindi...");
                isFoundProduct = false;
            } else {
                System.out.println("Anbarda məhsul tapılmadı!");
            }

            // update edilib yeni file yazildi
            Files.write(path, newList);
            newList.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateProductByID(int ID, double price) {


        try {
            Path path = Paths.get("DB15.txt");
            List<String> lines = Files.readAllLines(path);
            List<String> newList = new ArrayList<>();

            for (String line : lines) {
                // Başlık ve çizgi satırlarını koru
                if (line.trim().isEmpty() || line.startsWith("Kod") || line.startsWith("-----")) {
                    newList.add(line);
                    continue;
                }

                // Setırı "/" işaretlerine göre 4 parçaya ayır
                String[] parts = line.split("/", 4);
                if (parts.length < 4) {
                    // Beklenen formatta değilse olduğu gibi ekle
                    newList.add(line);
                    continue;
                }

                String kod = parts[0].trim();
                String name = parts[1].trim();
                String date = parts[2].trim();
                String pricePart = parts[3].trim();

                if (!kod.equals(String.valueOf(ID))) {
                    newList.add(line);
                } else {
                    isFoundProduct = true;

                    // Yeni fiyat formatı
                    String updatedLine = String.format("%s/ %s/ %s/ %.2f AZN", kod, name, date, price);

                    newList.add(updatedLine);
                }
            }

            if (isFoundProduct) {
                System.out.println("Məhsulun qiyməti uğurla yeniləndi...");
                isFoundProduct = false;
            } else {
                System.out.println("Anbarda məhsul tapılmadı!");
            }

            Files.write(path, newList);
            newList.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
