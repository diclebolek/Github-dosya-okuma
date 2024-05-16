/**
*
* Dicle Bölek dicle.bolek@ogr.sakarya.edu.tr
* 01.04.2024
* 
* Burada ilk dosyalarını gezdirdim denedim isimleri geliyor mu ,doğru taranıyor mu diye,
* sonrasında .java uzantıları aldım ve sınıf olanları ayıkladım ,analiz kısmına yolladım
*/
package git;

import java.io.File;

public class AnalyzeRepository {

    public static void analyzeRepository(File repoDir) {
        if (repoDir.isDirectory()) {
            File[] files = repoDir.listFiles();
            if (files != null && files.length > 0) {
                //System.out.println("Klonlanan dizinin içeriği:");

                // Dosyaları listele
                //for (File file : files) {
                    //System.out.println(file.getName());
                //}

                for (File file : files) {
                    if (file.isDirectory()) {
                        analyzeRepository(file); // Dizinse içeriğini analiz et
                    } else if (file.getName().endsWith(".java")) {
                        // Eğer dosya .java uzantılıysa ve içinde sadece sınıf tanımları varsa analiz et
                        if (ContainsOnlyClasses.containsOnlyClasses(file)) {
                        	AnalyzeJavaFile.analyzeJavaFile(file);
                        } else {
                            System.out.println(file.getName() + " dosyası içinde sınıf tanımı bulunamadığı için analiz edilmedi.");
                            System.out.println("-----------------------------");
                        }
                    }
                }
            } else {
                // Dosya listesi boş veya null ise hata mesajı verin
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("Klonlanmış repository bulunamadı veya dizin değil.");
        }
    }

}	
