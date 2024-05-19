package technikum.bohrffer.swen2tourguide.controllers;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import technikum.bohrffer.swen2tourguide.models.Tour;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TourAddControllerTest extends TestBase {

    private static TourAddController controller;
    private static ListView<Tour> tourList;
    private static Stage stage;
    private TextField name;
    private TextField description;
    private TextField from;
    private TextField to;
    private TextField transport;
    private TextField distance;
    private TextField time;

    @BeforeAll
    static void setUpOnce() {
        tourList = new ListView<>();
        controller = new TourAddController(tourList);
        stage = mock(Stage.class);
        controller.setStage(stage);
    }

    @BeforeEach
    void setUp() throws Exception {
        name = new TextField();
        description = new TextField();
        from = new TextField();
        to = new TextField();
        transport = new TextField();
        distance = new TextField();
        time = new TextField();

        setField(controller, "name", name);
        setField(controller, "description", description);
        setField(controller, "from", from);
        setField(controller, "to", to);
        setField(controller, "transport", transport);
        setField(controller, "distance", distance);
        setField(controller, "time", time);
    }

    @Test
    void testValidateForm_EmptyFields() throws Exception {
        name.setText("");
        description.setText("");
        from.setText("");
        to.setText("");
        transport.setText("");
        distance.setText("");
        time.setText("");

        Method validateFormMethod = controller.getClass().getDeclaredMethod("validateForm");
        validateFormMethod.setAccessible(true);
        boolean result = (boolean) validateFormMethod.invoke(controller);

        assertFalse(result);
    }

    @Test
    void testValidateForm_ValidFields() throws Exception {
        name.setText("Test Tour");
        description.setText("Description");
        from.setText("From");
        to.setText("To");
        transport.setText("Transport");
        distance.setText("10.0");
        time.setText("1.5");

        Method validateFormMethod = controller.getClass().getDeclaredMethod("validateForm");
        validateFormMethod.setAccessible(true);
        boolean result = (boolean) validateFormMethod.invoke(controller);

        assertTrue(result);
    }

    @Test
    void testSubmit() throws Exception {
        name.setText("Test Tour");
        description.setText("Description");
        from.setText("From");
        to.setText("To");
        transport.setText("Transport");
        distance.setText("10.0");
        time.setText("1.5");

        Method submitMethod = controller.getClass().getDeclaredMethod("submit");
        submitMethod.setAccessible(true);
        submitMethod.invoke(controller);

        assertEquals(1, tourList.getItems().size());
        assertEquals("Test Tour", tourList.getItems().getFirst().getName());
        verify(stage).close();
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
