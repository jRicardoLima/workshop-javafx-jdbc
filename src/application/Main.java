package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;


public class Main extends Application {
	private static Scene mainScene;//Criando uma variavel estatica para outras views abrir dentro da janela
	
	@Override
	public void start(Stage stage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollParent = loader.load();
			
			scrollParent.setFitToHeight(true);//Colocando a altura maxima no scrollPane
			scrollParent.setFitToWidth(true);//Colocando a largura maxima no scrollPane
			
			mainScene = new Scene(scrollParent);
			stage.setScene(mainScene);
			stage.setMaximized(true);
			stage.setTitle("Sample JavaFx application");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
