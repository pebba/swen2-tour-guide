package technikum.bohrffer.swen2tourguide.controllers;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import technikum.bohrffer.swen2tourguide.models.Tour;
import technikum.bohrffer.swen2tourguide.models.TourLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TourLogAddControllerTest extends TestBase {

    private static TourLogAddController controller;
    private static TableView<TourLog> tourLogsTable;
    private static Stage stage;
    private static Tour tour;
    private TextField comment;
    private TextField difficulty;
    private TextField totalDistance;
    private TextField totalTime;
    private TextField rating;

    @BeforeAll
    static void setUpOnce() {
        tour = new Tour("Test Tour", "Description", "From", "To", "Transport", 10.0, 1.5, "https://via.placeholder.com/150");
        tourLogsTable = new TableView<>();
        controller = new TourLogAddController(tour, tourLogsTable);
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
        comment.setText("Comment");
        difficulty.setText("Easy");
        totalDistance.setText("10.0");
        totalTime.setText("1.5");
        rating.setText("5");

        Method validateFormMethod = controller.getClass().getDeclaredMethod("validateForm");
        validateFormMethod.setAccessible(true);
        boolean result = (boolean) validateFormMethod.invoke(controller);

        assertTrue(result);
    }

    @Test
    void testSubmit() throws Exception {
        comment.setText("Comment");
        difficulty.setText("Easy");
        totalDistance.setText("10.0");
        totalTime.setText("1.5");
        rating.setText("5");

        Method submitMethod = controller.getClass().getDeclaredMethod("submit");
        submitMethod.setAccessible(true);
        submitMethod.invoke(controller);

        assertEquals(1, tour.getTourLogs().size());
        assertEquals(1, tourLogsTable.getItems().size());
        assertEquals("Comment", tourLogsTable.getItems().getFirst().getComment());
        verify(stage).close();
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
