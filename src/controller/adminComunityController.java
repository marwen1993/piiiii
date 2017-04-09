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

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mohamed on 3/26/2017.
 */

public class adminComunityController implements Initializable {

    @FXML
    private RecyclerView<recycleViewModel>recycleView;



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
        comunityList=comunityService.selectAllIntoAdminList();

        Adapter adapter=new Adapter();
        recycleView.setAdapter(adapter);

        for (int i=0;i<comunityList.size();i++){
            recycleViewModel recycleViewModel =comunityList.get(i);
            recycleView.getItems().addAll(recycleViewModel);
        }


    }

    @FXML
    void BackAction(ActionEvent event){
        main.closeComunity();
        main.mainWindow();
    }



    public  class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @Override
        public RecyclerView.ViewRow call(ListView listView) {
            return new RippleViewRow(this);
        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader fxmlLoader) {
            fxmlLoader.setLocation(adminComunityController.class.getResource("/view/adminComunity_CardView.fxml"));
            Holder holder = new Holder(fxmlLoader);
            return holder;
        }


        @Override
        public void onBindViewHolder(Holder holder, Object o) {

            recycleViewModel recycleViewModel = (recycleViewModel) o;

            holder.name.setText(recycleViewModel.getName());
            holder.description.setText(recycleViewModel.getComment());
            holder.warning.setText(recycleViewModel.getWarning());
            holder.hide.setText(recycleViewModel.getFollow());

            holder.hide.setOnAction(event -> {

            comunityService.changeComunityPermission(holder.name.getText(),holder.description.getText(),signInController.userName,holder.hide);

            });
        }

        public class Holder extends RecyclerView.ViewHolder {
            @FXML
            private Label name,warning;

            @FXML
            private JFXButton hide;

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



