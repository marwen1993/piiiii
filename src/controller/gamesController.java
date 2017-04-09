package controller;



import Main.Main;
import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.gamesModel;
import model.recycleViewModel;
import org.kairos.components.RippleViewRow;
import org.kairos.layouts.RecyclerView;
import java.net.URL;
import java.util.ResourceBundle;



public class gamesController implements Initializable {

    @FXML
    private RecyclerView<gamesModel> recycleView;

    Main main;
    Stage stage;

    public void main(Main main, Stage stage){
        this.main=main;
        this.stage=stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Adapter adapter=new Adapter();
        recycleView.setAdapter(adapter);

        if (MainWindowController.x==0) {
            for (int i = 0; i < MainWindowController.actionImages.length; i++) {
                gamesModel gamesModel = new gamesModel();
                System.out.println(MainWindowController.actionImages[i]);
                gamesModel.setName(MainWindowController.actionNames[i]);
                gamesModel.setImageSrc(MainWindowController.actionImages[i]);
                recycleView.getItems().addAll(gamesModel);
            }
        } else if (MainWindowController.x==1) {
            for (int i = 0; i < MainWindowController.adventureImages.length; i++) {
                gamesModel gamesModel = new gamesModel();
                System.out.println(MainWindowController.adventureImages[i]);
                gamesModel.setName(MainWindowController.adventureNames[i]);
                gamesModel.setImageSrc(MainWindowController.adventureImages[i]);
                recycleView.getItems().addAll(gamesModel);
            }
        }
        else if (MainWindowController.x==2) {
            for (int i = 0; i < MainWindowController.StealthShooterImages.length; i++) {
                gamesModel gamesModel = new gamesModel();
                System.out.println(MainWindowController.StealthShooterImages[i]);
                gamesModel.setName(MainWindowController.StealthShooterNames[i]);
                gamesModel.setImageSrc(MainWindowController.StealthShooterImages[i]);
                recycleView.getItems().addAll(gamesModel);
            }
        }
    }

    @FXML
    void BackAction(ActionEvent event){
         main.closeGames();
        main.mainWindow();
    }


    public  class Adapter extends RecyclerView.Adapter<gamesController.Adapter.Holder> {

        @Override
        public RecyclerView.ViewRow call(ListView listView) {
            return new RippleViewRow(this);
        }

        @Override
        public gamesController.Adapter.Holder onCreateViewHolder(FXMLLoader fxmlLoader) {
            fxmlLoader.setLocation(comunityController.class.getResource("/view/gamesCardView.fxml"));
            gamesController.Adapter.Holder holder = new gamesController.Adapter.Holder(fxmlLoader);
            return holder;
        }

        @Override
        public void onBindViewHolder(gamesController.Adapter.Holder holder, Object o) {
            gamesModel recycleViewModel = (gamesModel) o;
            holder.name.setText(recycleViewModel.getName());
            holder.imageView.setImage(new Image("/src/"+recycleViewModel.getImageSrc()));


        }

        public class Holder extends RecyclerView.ViewHolder {
            @FXML
            private Label name;


            @FXML
            private AnchorPane rootPane;

            @FXML
            private ImageView imageView;


            public Holder(FXMLLoader loader) {
                super(loader);

            }

        }
    }

}
