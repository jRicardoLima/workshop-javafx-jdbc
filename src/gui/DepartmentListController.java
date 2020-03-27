package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtnNewAction() {
		System.out.println("btn action");
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

}
