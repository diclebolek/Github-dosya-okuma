/**
*
* Dicle Bölek dicle.bolek@ogr.sakarya.edu.tr
* 01.04.2024
* 
* Burada dosyalarda yapmamız istenen analizleri yaptım
* 
*/
package git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AnalyzeJavaFile {
	private static boolean isCommentLine(String line) {
	    // Satırın başında veya içinde // geçiyorsa veya /* işareti içeriyorsa yorum satırıdır
	    return line.trim().startsWith("//") || line.contains("//") || (line.contains("/*") && !line.trim().startsWith("/**"));
	}

	public static void analyzeJavaFile(File javaFile) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(javaFile))) {
	        int javadocLines = 0;
	        int otherComments = 0;
	        int codeLines = 0;
	        int lineOfCode = 0;
	        int functionCount = 0;
	        boolean inJavadocBlock = false;

	        String line;
	        while ((line = reader.readLine()) != null) {
	            lineOfCode++;

	            // /** ile başlayan satır mı kontrol et
	            if (line.trim().startsWith("/**")) {
	                // Eğer bir Javadoc bloğunun içine girildiyse, bu bloğun içindeki her satırı Javadoc olarak say
	                inJavadocBlock = true;
	            }else if (inJavadocBlock && line.trim().startsWith("*") && !line.trim().startsWith("*/")) {
                    javadocLines++;
                }	            

	            // Eğer */ ile biten bir Javadoc bloğu bulunduysa, Javadoc bloğunun dışına çık
	            if (line.trim().endsWith("*/")) {
	                inJavadocBlock = false;
	            }

	            // Diğer yorum satırlarını kontrol et
	            if (isCommentLine(line)) {
	                otherComments++;
	            }

	            // Kod satırlarını kontrol et
	            if (!line.trim().startsWith("//") && !line.trim().startsWith("/**") && !line.trim().startsWith("/*") && !inJavadocBlock && !line.trim().isEmpty()) {
	                // Kod satırı olarak kabul edilir
	                codeLines++;
	            }


	            // Fonksiyon sayısını kontrol et
	            if (line.contains("(") && line.contains(")") && !inJavadocBlock) {
	                // Süslü parantezleri kontrol et
	                if (line.contains("{")) {
	                    functionCount++; // Fonksiyon başladı
	                } else {
	                    String nextLine1 = reader.readLine();
	                    lineOfCode++;

	                    // Sonraki satırı kontrol et
	                    if (nextLine1 != null && nextLine1.trim().startsWith("{")) {
	                        functionCount++; // Fonksiyon başladı
	                    }
	                }
	            }

	        }


	        // Yorum sapma yüzdesi hesaplaması
	        double YG = ((double) (javadocLines + otherComments) * 0.8) / functionCount;
	        double YH = ((double) codeLines / functionCount) * 0.3;
	        double commentDeviation = ((100 * YG) / YH) - 100;

	        // Sonuçları yazdır
	        System.out.println("Sınıf: " + javaFile.getName());
	        System.out.println("Javadoc Satır Sayısı: " + javadocLines);
	        System.out.println("Yorum Satır Sayısı: " + otherComments);
	        System.out.println("Kod Satır Sayısı (Yorum ve Boşluklar Hariç): " + codeLines);
	        System.out.println("LOC (Line of Code): " + lineOfCode);
	        System.out.println("Fonksiyon Sayısı: " + functionCount);
	        System.out.println("Yorum Sapma Yüzdesi: %" + commentDeviation);
	        System.out.println("---------------------------------------------");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
