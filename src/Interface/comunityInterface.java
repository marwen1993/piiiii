package Interface;

import Main.Main;
import com.jfoenix.controls.JFXButton;
import controller.comunityController;
import controller.signInController;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import model.recycleViewModel;
import model.userModel;
import org.kairos.layouts.RecyclerView;
import service.comunityService;

import java.util.ArrayList;


public interface comunityInterface {

   public void insertComunity(recycleViewModel rm);
    public void insertFollow(recycleViewModel rm);
    public void changeComunityPermission(String name,String description,String username,JFXButton button);
    public ArrayList<recycleViewModel> selectAllIntoList();
    public void addDataToRecyclerView(String name,String description,RecyclerView<recycleViewModel>recyclerView);
    public void follow(JFXButton follow, Label name, Label description, comunityService comunityService) ;
    public ArrayList<recycleViewModel> selectAllIntoAdminList();

}
