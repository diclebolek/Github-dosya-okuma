/**
*
* Dicle Bölek dicle.bolek@ogr.sakarya.edu.tr
* 01.04.2024
* 
* Burada sınıf mı değil mi kontrol ettirdim sonucu 
* true/false olarak depo analiz kısmına gönderdim
*/
package git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContainsOnlyClasses {

    public static boolean containsOnlyClasses(File javaFile) {
    	try (BufferedReader reader = new BufferedReader(new FileReader(javaFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Satırda "class" veya "public class" geçiyorsa, sınıf tanımı var demektir
                if (line.contains("class") || line.contains("public class")) {
                    return true;
                }
            }
            // Sınıf tanımı bulunamadı
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
}
}