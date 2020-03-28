package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import model.services.DepartmentServices;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("Seller action");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml",(DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentServices());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x-> {});
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized <T> void loadView(String nameView,Consumer<T> inicializingAction) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nameView));
			
			VBox vBox = loader.load();//Janela About
			
			Scene mainScene = Main.getMainScene();//Pegando a cena atual(janela atual)
			VBox mainVbox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();//pegando os elementos da janela main
			
			Node mainMenu = mainVbox.getChildren().get(0);//Pegando a posição do menu que está na janela atual
			mainVbox.getChildren().clear();// limpamdo todos os filhos da janela
			
			mainVbox.getChildren().add(mainMenu);//adicionando o menu na próxima janela 
			mainVbox.getChildren().addAll(vBox.getChildren());//adicionando todos os filhos da proxima janela na janela atual
			
			T controller = loader.getController();
			
			inicializingAction.accept(controller);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception","Error loading view",e.getMessage(),AlertType.ERROR);
		}
	}
	
}
