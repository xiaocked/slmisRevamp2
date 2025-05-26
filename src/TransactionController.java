import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TransactionController implements Initializable{
    
      @FXML
       private ListView<String> myListView;


        @FXML
        private Label mylabel;

        String[] paymentMethod = {"Bank Transfer", "E-Wallet", "Credit Card", "Finance Hours 9AM - 5 PM", };

        String currentpaymentMethod;

        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
        
           myListView.getItems().addAll(paymentMethod);
myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
        currentpaymentMethod = myListView.getSelectionModel().getSelectedItem();
        mylabel.setText("You have selected: " + currentpaymentMethod);
    }
});
        
        }
}