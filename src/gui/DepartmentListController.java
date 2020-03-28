package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentServices;

public class DepartmentListController implements Initializable{

	private DepartmentServices service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> columnId;
	
	@FXML
	private TableColumn<Department, String> columnName;
	
	@FXML
	private Button btnNew;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtnNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml",parentStage);
	}
	
	public void setDepartmentService(DepartmentServices service){
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}


	private void initializeNodes() {
		this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage =(Stage) Main.getMainScene().getWindow();
		
		this.tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
		
	}
	
	public void updateTableView() {
		
		if(this.service == null) {
			throw new IllegalStateException("Service null");
		}
		
		List<Department> list = this.service.findAll();
		
		this.obsList = FXCollections.observableArrayList(list);
		
		this.tableViewDepartment.setItems(obsList);
		
		
	}
	
	private void createDialogForm(String nameView,Stage parentStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nameView));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
				Alerts.showAlert("Error", "Error Loading view", e.getMessage(),AlertType.ERROR);
		}
	}

}
