package com.yezebi.pinpin.op.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Bean
    FirebaseApp firebaseApp(final AppProperties properties) throws IOException {
        final String serviceAccount = System.getenv("GOOGLE_SERVICE_ACCOUNT");
        final InputStream credentials = new ByteArrayInputStream(serviceAccount.getBytes());

        final FirebaseOptions options =
                FirebaseOptions.builder()
                        .setProjectId(properties.projectId())
                        .setCredentials(GoogleCredentials.fromStream(credentials))
                        .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    FirebaseAuth firebaseAuth(final FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
