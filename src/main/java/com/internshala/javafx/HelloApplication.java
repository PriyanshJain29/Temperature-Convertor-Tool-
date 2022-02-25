package com.internshala.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();

		MenuBar menubar = createMenu();
		rootNode.getChildren().add(0,menubar);

		Scene scene = new Scene(rootNode);

		primaryStage.setTitle("Temperature Convertor Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public MenuBar createMenu()
	{
		// Menu
		Menu filemenu = new Menu("File");
		MenuItem newItemMenu = new MenuItem("New");
		newItemMenu.setOnAction(event->
		{
			System.out.println("New --->");
		});

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

		MenuItem quitItemMenu = new MenuItem("Quit");
		quitItemMenu.setOnAction(event->
		{
			Platform.exit();
		});

		filemenu.getItems().addAll(newItemMenu,separatorMenuItem, quitItemMenu);


		// Help
		Menu helpmenu = new Menu("Help");
		MenuItem aboutmenu = new MenuItem("About");
		aboutmenu.setOnAction(event->
		{
			AboutUs();
		});
		helpmenu.getItems().addAll(aboutmenu);

		MenuBar menubar = new MenuBar();
		menubar.getMenus().addAll(filemenu, helpmenu);

		return menubar;
	}

	public void AboutUs() {
		Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("About");
		alertDialog.setHeaderText("Temperature Converter Tool");
		alertDialog.setContentText("This conversion tool will convert a temperature value from and to degC, degF or Kelvin measurement units. This tool will also display a conversion scale applicable to each converted temperature. The lowest possible temperature is zero Kelvin (K), -273.15 degC or -459.67 degF and this is called absolute zero.");
		alertDialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alertDialog.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}