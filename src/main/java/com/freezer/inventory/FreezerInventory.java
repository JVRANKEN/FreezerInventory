package com.freezer.inventory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class FreezerInventory {
	private static final String FIREBASE_FILE_NAME = "serviceAccountKey.json";

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = FreezerInventory.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(FIREBASE_FILE_NAME)).getFile());
		String abspath = file.getAbsolutePath();
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		if(FirebaseApp.getApps().isEmpty()) { //<--- check with this line
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(FreezerInventory.class, args);
	}

}
