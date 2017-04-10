package controller;

import DataBase.DataSource;
import Main.Main;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.userModel;
import service.userService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mohamed on 3/25/2017.
 */
public class adminScreenConroller implements Initializable {

    @FXML
    private TreeTableColumn<userModel, String> Role;

    @FXML
    private TreeTableColumn<userModel, String> Email;

    @FXML
    private TreeTableColumn<userModel, String> Username;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TreeTableColumn<userModel,String> FullName;

    @FXML
    private TreeTableColumn<userModel,String> Enabled;

    @FXML
    private TreeTableColumn<userModel,String> ID;

    @FXML
    private JFXTreeTableView<userModel> treeTableView;

    @FXML
    private TreeTableColumn<userModel, String> Password;
    @FXML
    private TreeTableColumn<userModel, String> comment;

    @FXML
    private TreeTableColumn<userModel, String> Create;

    ObservableList<userModel> userList;
    DataSource dataSource;
    PreparedStatement statement;
    ResultSet result;
    userService userService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSource=new DataSource();
         userService=new userService();
        userList= FXCollections.observableArrayList();
        ID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().id;
            }
        });

        FullName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().fullName;
            }
        });
        Username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().username;
            }
        });
        Email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().email;
            }
        });

        Password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().password;
            }
        });
        Role.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().role;
            }
        });
        Enabled.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().enabled;
            }
        });

        comment.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().comment;
            }
        });
        Create.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().create;
            }
        });

        TreeItem<userModel> root = new RecursiveTreeItem<userModel>(userList, RecursiveTreeObject::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

        ArrayList<userModel> arrayList=userService.selectAll();
        for (int i=0;i<arrayList.size();i++){
            userModel userModel=arrayList.get(i);
            userList.addAll(new userModel(userModel.getId(),userModel.getFullName(),userModel.getUsername(),userModel.getEmail(),userModel.getPassword(),userModel.getRole(),userModel.getEnabled(),userModel.getComment(),userModel.getCreate()));
        }



        treeTableView.getSelectionModel().selectedItemProperty().addListener((observable ,oldValue,newValue) ->{
         signInController.userName=newValue.getValue().getUsername();
        });
    }


    @FXML
    void DeleteuserAction(ActionEvent event) {
        userService.deleteUser(treeTableView.getSelectionModel().getSelectedIndex(),treeTableView.getSelectionModel().getSelectedItem(),userList);
    }

    @FXML
    void ChangePermissionEnabledAction(ActionEvent event) {
       userService.ChangePermissionEnabled(treeTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void ChangeRoleAction(ActionEvent event) {
        userService.ChangeRole(treeTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void ChangeComunityCreationPermissionAction(ActionEvent event) {
        userService.ChangeComunityCreationPermission(treeTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void userComunitiesAction(ActionEvent event) {
        new Main().comunityAdminWindow();
    }


    @FXML
    void ChangeCommentPermissionAction(ActionEvent event) {
     userService.ChangeCommentPermission(treeTableView.getSelectionModel().getSelectedItem());
    }


}
