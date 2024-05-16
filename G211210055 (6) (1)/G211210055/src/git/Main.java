/**
*
* Dicle Bölek dicle.bolek@ogr.sakarya.edu.tr
* 01.04.2024
* 
* Burada klonlama işlemini başlattım
* 
*/
package git;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lütfen GitHub deposunun URL'sini girin: ");
        String repoUrl = scanner.nextLine();
        //String gitPath = "C:\\Program Files\\Git\\cmd\\git.exe";
        scanner.close(); // Scanner kapatılmalı

     // Klonlanmış repository'nin dizinini tanımla
        

        File clonedRepoDir = new File("C:\\Users\\test\\eclipse-workspace\\Githubdeneme", "myClonedRepository");

        // Klonlanmış dizinin oluşturulup oluşturulmadığını kontrol et
        if (!clonedRepoDir.exists()) {
            // Dizin yoksa oluştur
            if (clonedRepoDir.mkdir()) {
                System.out.println("Klonlanmış repository dizini başarıyla oluşturuldu.");
            } else {
                System.out.println("Klonlanmış repository dizini oluşturulurken bir hata oluştu.");
                return; // Programı sonlandır
            }
        }

        // Git clone komutunu oluşturun
        ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", repoUrl, "C:\\Users\\test\\eclipse-workspace\\Githubdeneme\\myClonedRepository");



        // Klonlama işlemini başlatın
        try {
            Process process = processBuilder.start();

            // Klonlama işleminin tamamlanmasını bekleyin
            process.waitFor();
            
            System.out.println("Repository başarıyla klonlandı.");

            // Klonlanmış dizinin içeriğini analiz etmek için devam edin
            AnalyzeRepository.analyzeRepository(clonedRepoDir);
        } catch (IOException | InterruptedException e) {
        	//System.err.println("Bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }

    }

//https://github.com/mfadak/Odev1Ornek.git
    


   
    
    
}