package com.internshala.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public Label WelcomeLabel;

	@FXML
	public ChoiceBox <String> choiceBox;

	private static final String C_TO_F_TEXT = "Celsius to Fahrenheit";
	private static final String F_T0_C_TEXT = "Fahrenheit to Celsius";

	@FXML
	public TextField textField;

	@FXML
	public Button convertButton;
	private boolean isC_To_F_TEXT = true;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		choiceBox.getItems().add(C_TO_F_TEXT);
		choiceBox.getItems().add(F_T0_C_TEXT);

		choiceBox.setValue(C_TO_F_TEXT);


		choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

				if(newValue.equals(C_TO_F_TEXT)) // If user is selected "Celsius to farenheit "
				{
					isC_To_F_TEXT = true;
				}
				else                    // Else User is selected "Fahrenheit to Celsius"
				{
					isC_To_F_TEXT = false;
				}

			}
		});


		convertButton.setOnAction(event ->
		{
			convert();
		});

	}

	private void convert() {
		String input = textField.getText();
		float enteredTemperature = 0.0f;
		try{
			enteredTemperature = Float.parseFloat(input);
		}
		catch(Exception exception)
		{
			warmuser();
			return;
		}

		float newTemperature = 0.0f;
		if(isC_To_F_TEXT)       // If user is selected "Celsius to farenheit "
		{
			newTemperature = (enteredTemperature * 9/5) + 32;
		}
		else                    // Else User is selected "Fahrenheit to Celsius"
		{
			newTemperature = (enteredTemperature - 32) * 5/9;
		}
		display(newTemperature);
	}

	private void warmuser() {
		Alert alertwarning = new Alert(Alert.AlertType.ERROR);
		alertwarning.setTitle("Error");
		alertwarning.setHeaderText("Invalid Temperature Entered");
		alertwarning.setContentText("Enter The Valid Temperature");
		alertwarning.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alertwarning.show();
	}

	private void display(float newTemperature) {
		String unit = isC_To_F_TEXT ? "F" : "C";

		Alert alerDialog = new Alert(Alert.AlertType.INFORMATION);
		alerDialog.setTitle("Result");
		alerDialog.setHeaderText("Temperature Convertor Tool");
		alerDialog.setContentText("The new Temperature is : " + newTemperature + " " + unit);
		alerDialog.show();
		alerDialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	}
}
