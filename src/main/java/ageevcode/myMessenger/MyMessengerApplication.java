package ageevcode.myMessenger;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyMessengerApplication {
    public static void main(String[] args) {
        Sentry.captureMessage("Application started");
        SpringApplication.run(MyMessengerApplication.class, args);
    }
}
