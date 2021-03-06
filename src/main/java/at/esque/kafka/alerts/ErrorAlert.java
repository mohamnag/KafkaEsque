package at.esque.kafka.alerts;

import at.esque.kafka.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorAlert {

    public static void show(Throwable ex){
        show(ex, true);
    }

    public static void show(Throwable ex, boolean expandable) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ex.getClass().getSimpleName());
        alert.setHeaderText(ex.getClass().getName());
        alert.setContentText(ex.getMessage());
        Main.applyIcon(alert);
        Main.applyStylesheet(alert.getDialogPane().getScene());

        if (expandable) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(textArea, 0, 0);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
        }

        alert.showAndWait();
    }
}
