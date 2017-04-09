package controller;



import DataBase.DataSource;
import Main.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.recycleViewModel;
import org.kairos.components.RippleViewRow;
import org.kairos.layouts.RecyclerView;
import service.comunityService;
import service.userService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class comunityController implements Initializable {

    @FXML
    private RecyclerView<recycleViewModel>recycleView;

    @FXML
    private JFXTextField gameNameTF,descTF;

    @FXML
    private AnchorPane rootPane;

    DataSource dataSource;
    PreparedStatement statement;
    ResultSet result;
    public static String gameName="",gameDesc="",follow="";
    Main main;
    Stage stage;
    public void main(Main main, Stage stage){
        this.main=main;
        this.stage=stage;
    }

    comunityService comunityService;
    ArrayList<recycleViewModel>comunityList ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataSource=new DataSource();
        comunityService=new comunityService();
        Adapter adapter=new Adapter();
        recycleView.setAdapter(adapter);
        comunityList=new ArrayList<>();

        comunityList=comunityService.selectAllIntoList();
        recycleView.getSelectionModel().selectedItemProperty().addListener((observable ,oldValue,newValue) ->{
            follow=newValue.getFollow();
            gameName=newValue.getName();
            gameDesc=newValue.getComment();
            main.closeComunity();
            Main.gameName=newValue.getName();
            new Main().commentsWindow();
        });

        for (int i=0;i<comunityList.size();i++){
            comunityService.addDataToRecyclerView(comunityList.get(i).getName(),comunityList.get(i).getComment(),recycleView);
        }

    }

    @FXML
    void BackAction(ActionEvent event){
        main.closeComunity();
        main.mainWindow();
    }

    @FXML
    void addComunityAction(ActionEvent event) {
        try {

            if (gameNameTF.getText().isEmpty()) {
                showSnackBar("Please enter the name of the game");
            } else if (descTF.getText().isEmpty()) {
                showSnackBar("Please enter the description of the game");
            } else {
                if (signInController.createPer.equals("1") == true) {
                    recycleViewModel recycleViewModel = new recycleViewModel();
                    recycleViewModel.setName(gameNameTF.getText());
                    recycleViewModel.setComment(descTF.getText());
                    recycleViewModel.setWarning("");
                    recycleViewModel.setFollow("Follow");
                    comunityService.insertComunity(recycleViewModel);
                    recycleView.getItems().addAll(recycleViewModel);
                    gameNameTF.setText("");
                    descTF.setText("");
                } else if (signInController.createPer.equals("0") == true) {

                    recycleViewModel recycleViewModel = new recycleViewModel();
                    recycleViewModel.setName(gameNameTF.getText());
                    recycleViewModel.setComment(descTF.getText());
                    recycleViewModel.setFollow("Follow");
                    recycleViewModel.setWarning("this comunity will apear when the admin give you a permision");
                    comunityService.insertComunity(recycleViewModel);
                    recycleView.getItems().addAll(recycleViewModel);
                    gameNameTF.setText("");
                    descTF.setText("");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @FXML
    void cancelCommentAction(ActionEvent event) {
        gameNameTF.setText("");
        descTF.setText("");

    }


    public  class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @Override
        public RecyclerView.ViewRow call(ListView listView) {
            return new RippleViewRow(this);
        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader fxmlLoader) {
            fxmlLoader.setLocation(comunityController.class.getResource("/view/comunity_CardView.fxml"));
            Holder holder = new Holder(fxmlLoader);
            return holder;
        }


        @Override
        public void onBindViewHolder(Holder holder, Object o) {

            recycleViewModel recycleViewModel = (recycleViewModel) o;

            holder.name.setText(recycleViewModel.getName());
            holder.description.setText(recycleViewModel.getComment());
            holder.warning.setText(recycleViewModel.getWarning());
            holder.follow.setText(recycleViewModel.getFollow());

            holder.follow.setOnAction(event -> {
               comunityService.follow(holder.follow,holder.name,holder.description,comunityService);
            });
        }

        public class Holder extends RecyclerView.ViewHolder {
            @FXML
            private Label name,warning;

            @FXML
            private JFXButton follow;

            @FXML
            private AnchorPane rootPane;

            @FXML
            private Label description;


            public Holder(FXMLLoader loader) {
                super(loader);
            }


        }
    }

    public void showSnackBar(String msg){
        JFXSnackbar snackbar=new JFXSnackbar(rootPane);
        snackbar.setAlignment(Pos.BOTTOM_RIGHT);

        snackbar.setPrefHeight(30);
        snackbar.setPrefWidth(rootPane.getPrefWidth());
        snackbar.show(msg,4000);

    }
}



