package similarity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import similarity.algorithm.CosineSimilarity;
import similarity.algorithm.EuclidSimilarity;
import similarity.algorithm.PearsonSimilarity;
import similarity.util.GetSheet;
import javafx.scene.control.Label;
import similarity.util.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author BB小天使
 * @author Yumen
 * @version 1.0
 */
public class MainController implements Initializable {
    @FXML
    private ComboBox Algorithmic_options;
    @FXML
    private ListView results;
    @FXML
    private CategoryAxis statistics_CateGoryAxis;
    @FXML
    private BarChart statistics;
    @FXML
    private Label path_L;
    @FXML
    private Label max;

    private ObservableList<String> collectlist = FXCollections.observableArrayList();
    EuclidSimilarity euclidSimilarity = new EuclidSimilarity();
    CosineSimilarity cosineSimilarity = new CosineSimilarity();
    PearsonSimilarity pearsonSimilarity = new PearsonSimilarity();
    String[] AL_list = new String[]{"欧几里得距离算法", "余弦相似度算法", "皮尔逊相似度算法"};

    /**
     * @param Algorithmic_options     the comboBox
     * @param results                 display the data
     * @param statistics_CateGoryAxis the barChart
     * @throws Exception
     */
    public void Algorithmic_start() throws Exception {
        if (Algorithmic_options.getValue() == null) {
            similarity.util.Window window = new Window();
            window.alert("请选择算法！");
        } else {
            if (Algorithmic_options.getValue() == "欧几里得距离算法") {
                //Select EculidSimilarity;
                collectlist.clear();
                collectlist.add("欧几里得距离算法得出的结果为:\n\n" + euclidSimilarity.getInfo()[0]);
                //On statistical charts
                statistics.getData().clear();

                XYChart.Series<String, Double> series = new XYChart.Series<>();
                for (int i = 0; i < euclidSimilarity.getData().size(); i++) {
                    series.getData().add(new XYChart.Data<>(euclidSimilarity.getName().get(i), euclidSimilarity.getData().get(i)));
                }
                series.setName("欧几里得距离算法");
                statistics.getData().add(series);
                max.setText(euclidSimilarity.getInfo()[1]);
            } else if (Algorithmic_options.getValue() == "余弦相似度算法") {
                //Select ConsineSimilarity;
                collectlist.clear();
                collectlist.add("余弦相似度算法得出的结果为:\n\n" + cosineSimilarity.getInfo()[0]);
                //On statistical charts
                statistics.getData().clear();
                XYChart.Series<String, Double> series = new XYChart.Series<>();
                for (int i = 0; i < cosineSimilarity.getData().size(); i++) {
                    series.getData().add(new XYChart.Data<>(cosineSimilarity.getName().get(i), cosineSimilarity.getData().get(i)));
                }
                series.setName("余弦相似度算法");
                statistics.getData().add(series);
                max.setText(cosineSimilarity.getInfo()[1]);
            } else if (Algorithmic_options.getValue() == "皮尔逊相似度算法") {
                //Select PearsonSimilarity;
                collectlist.clear();
                collectlist.add("皮尔逊相似度算法得出的结果为:\n\n" + pearsonSimilarity.getInfo()[0]);
                //On statistical charts
                statistics.getData().clear();
                XYChart.Series<String, Double> series = new XYChart.Series<>();
                for (int i = 0; i < pearsonSimilarity.getData().size(); i++) {
                    series.getData().add(new XYChart.Data<>(pearsonSimilarity.getName().get(i), pearsonSimilarity.getData().get(i)));
                }
                series.setName("皮尔逊相似度算法");
                statistics.getData().add(series);
                max.setText(pearsonSimilarity.getInfo()[1]);
            }

        }
    }

    public void setpath() {
        //Select file path
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("我要一个EXCEL文件", "*.xls");
            fileChooser.getExtensionFilters().add(filter);
            Stage stage = new Stage();
            File file = fileChooser.showOpenDialog(stage);
            GetSheet.FILE_PATH = file.toString();
            path_L.setText("路径是：" + GetSheet.FILE_PATH);
        }
        catch (Exception e){
            Window window=new Window();
            window.alert("请选择一个xlsx文件！");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
//            path_L.setText("当前の文件路径是：" + GetSheet.FILE_PATH);
//            Algorithmic_options.setItems(FXCollections.observableArrayList(AL_list));
//            results.setItems(collectlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
