package technikum.bohrffer.swen2tourguide.controllers;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;

public abstract class TestBase {

    private static boolean toolkitInitialized = false;

    @BeforeAll
    static void initToolkit() throws InterruptedException {
        if (!toolkitInitialized) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(latch::countDown);
            toolkitInitialized = true;
            latch.await();
        }
    }
}
