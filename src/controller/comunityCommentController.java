package controller;



import DataBase.DataSource;
import Main.Main;
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
import service.commentService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mohamed on 3/26/2017.
 */

public class comunityCommentController implements Initializable {

    @FXML
    private RecyclerView<recycleViewModel>recycleView;

    @FXML
    private JFXTextField commentTF;

    DataSource dataSource;
    PreparedStatement statement;
    ResultSet result;

    Main main;
    Stage stage;
    public void main(Main main, Stage stage){
        this.main=main;
        this.stage=stage;
    }
    @FXML
    void BackAction(ActionEvent event){
        main.closeGComments();
        main.comunityWindow();
    }

    commentService commentService;

    @FXML
    private AnchorPane rootPane,CommentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataSource=new DataSource();
        commentService=new commentService();

        if (comunityController.follow.equals("Follow")){
            CommentPane.setDisable(true);
        }else if (comunityController.follow.equals("Unfollow")){
            CommentPane.setDisable(false);
        }

        recycleViewModel recycleViewModel1=new recycleViewModel();
        recycleViewModel1.setName("gggggggggggg");
        recycleView.getItems().addAll(recycleViewModel1);

        Adapter adapter=new Adapter();
        recycleView.setAdapter(adapter);
        System.out.println("xc ");

        ArrayList<recycleViewModel> arrayList=commentService.addDataToRecylcleView();
        for (int i=0;i<arrayList.size();i++){
            recycleViewModel recycleViewModel=arrayList.get(i);
            recycleView.getItems().addAll(recycleViewModel);
        }
    }


    public void showSnackBar(String msg){
        JFXSnackbar snackbar=new JFXSnackbar(rootPane);
        snackbar.setAlignment(Pos.BOTTOM_RIGHT);

        snackbar.setPrefHeight(30);
        snackbar.setPrefWidth(rootPane.getPrefWidth());
        snackbar.show(msg,4000);

    }
    @FXML
    void addCommentAction(ActionEvent event) {
        try {


            if (commentTF.getText().isEmpty()) {
                showSnackBar("Please enter your comment");
            } else {
                if (signInController.commentper==1) {
                    commentService.insertComment(commentTF.getText());
                    recycleViewModel recycleViewModel = new recycleViewModel();
                    recycleViewModel.setName(signInController.name);
                    recycleViewModel.setUsername(signInController.userName);
                    recycleViewModel.setComment(commentTF.getText());
                    recycleViewModel.setWarning("");
                    recycleView.getItems().addAll(recycleViewModel);
                    commentTF.setText("");
                } else if (signInController.commentper==0) {

                    commentService.insertComment(commentTF.getText());
                    recycleViewModel recycleViewModel = new recycleViewModel();
                    recycleViewModel.setName(signInController.name);
                    recycleViewModel.setUsername(signInController.userName);
                    recycleViewModel.setComment(commentTF.getText());
                    recycleViewModel.setWarning("this comment will apear when the admin give you a permision");
                    recycleView.getItems().addAll(recycleViewModel);
                    commentTF.setText("");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void cancelCommentAction(ActionEvent event) {
        commentTF.setText("");
    }

    public  class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @Override
        public RecyclerView.ViewRow call(ListView listView) {

            return new RippleViewRow(this);

        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader fxmlLoader) {

            fxmlLoader.setLocation(comunityCommentController.class.getResource("/view/commentCardView.fxml"));
            Holder holder = new Holder(fxmlLoader);
            return holder;
        }


        @Override
        public void onBindViewHolder(Holder holder, Object o) {
            recycleViewModel recycleViewModel = (recycleViewModel) o;
            holder.name.setText(recycleViewModel.getName());
            holder.username.setText(recycleViewModel.getUsername());
            holder.comment.setText(recycleViewModel.getComment());
            holder.warning.setText(recycleViewModel.getWarning());
        }

        public class Holder extends RecyclerView.ViewHolder {
            @FXML
            private Label name;

            @FXML
            private Label username,warning;

            @FXML
            private AnchorPane rootPane;

            @FXML
            private Label comment;


            public Holder(FXMLLoader loader) {
                super(loader);
            }


        }
    }}



