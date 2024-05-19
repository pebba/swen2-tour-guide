package technikum.bohrffer.swen2tourguide.controllers;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import technikum.bohrffer.swen2tourguide.models.TourLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TourLogModifyControllerTest extends TestBase {

    private static TourLogModifyController controller;
    private static Stage stage;
    private static TourLog tourLog;
    private TextField comment;
    private TextField difficulty;
    private TextField totalDistance;
    private TextField totalTime;
    private TextField rating;

    @BeforeAll
    static void setUpOnce() {
        tourLog = new TourLog(LocalDateTime.now(), "Original Comment", "Medium", 15.0, 2.0, 4);
        TableView<TourLog> tourLogsTable = new TableView<>();
        controller = new TourLogModifyController(tourLog, tourLogsTable);
        stage = mock(Stage.class);
        controller.setStage(stage);
    }

    @BeforeEach
    void setUp() throws Exception {
        comment = new TextField();
        difficulty = new TextField();
        totalDistance = new TextField();
        totalTime = new TextField();
        rating = new TextField();

        setField(controller, "comment", comment);
        setField(controller, "difficulty", difficulty);
        setField(controller, "totalDistance", totalDistance);
        setField(controller, "totalTime", totalTime);
        setField(controller, "rating", rating);
    }

    @Test
    void testValidateForm_EmptyFields() throws Exception {
        comment.setText("");
        difficulty.setText("");
        totalDistance.setText("");
        totalTime.setText("");
        rating.setText("");

        Method validateFormMethod = controller.getClass().getDeclaredMethod("validateForm");
        validateFormMethod.setAccessible(true);
        boolean result = (boolean) validateFormMethod.invoke(controller);

        assertFalse(result);
    }

    @Test
    void testValidateForm_ValidFields() throws Exception {
        comment.setText("Updated Comment");
        difficulty.setText("Hard");
        totalDistance.setText("20.0");
        totalTime.setText("3.0");
        rating.setText("5");

        Method validateFormMethod = controller.getClass().getDeclaredMethod("validateForm");
        validateFormMethod.setAccessible(true);
        boolean result = (boolean) validateFormMethod.invoke(controller);

        assertTrue(result);
    }

    @Test
    void testSubmit() throws Exception {
        comment.setText("Updated Comment");
        difficulty.setText("Hard");
        totalDistance.setText("20.0");
        totalTime.setText("3.0");
        rating.setText("5");

        Method submitMethod = controller.getClass().getDeclaredMethod("submit");
        submitMethod.setAccessible(true);
        submitMethod.invoke(controller);

        assertEquals("Updated Comment", tourLog.getComment());
        assertEquals("Hard", tourLog.getDifficulty());
        assertEquals(20.0, tourLog.getTotalDistance());
        assertEquals(3.0, tourLog.getTotalTime());
        assertEquals(5, tourLog.getRating());
        verify(stage).close();
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
