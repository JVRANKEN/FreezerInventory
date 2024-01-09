package com.freezer.inventory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@SpringBootApplication
public class FreezerInventory {
//	private static final String FIREBASE_FILE_NAME = "serviceAccountKey.json";

    public static void main(String[] args) throws IOException {
        // TODO change the path dynamically??
        try (InputStream input = new FileInputStream("src/main/resources/dbconnections/jvrahome.properties")) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            String fileName = prop.getProperty("firebase.serviceAccountKey");
            ClassLoader classLoader = FreezerInventory.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) { //<--- check with this line
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        SpringApplication.run(FreezerInventory.class, args);
    }

}
