import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.image.*;
public class Navigation extends Application{
    //起点、终点文本框
    static Label start_Label = new Label("起点：");
    static TextArea start_Area = new TextArea();
    static Label destination_Label = new Label("终点：");
    static TextArea destination_Area = new TextArea();
    static TextArea result_Area = new TextArea();
    final int X = Integer.MAX_VALUE;
    int [][] matrixDefalt = {
            {0,6,X,X,X,X,X,X,X,X,X,X,X,X,X,12,X,X,X,X,X,X,X,X,X,X,X,X,X,X}, //0
            {6,0,X,X,X,3,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},   //1
            {X,X,0,6,X,X,X,X,X,X,4,X,13,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},  //2
            {X,X,6,0,8,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//3
            {X,X,X,8,0,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//4
            {X,3,X,X,X,0,X,5,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//5
            {X,X,X,X,X,X,0,X,6,8,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//6
            {X,X,X,X,X,5,X,0,X,X,X,4,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//7
            {X,X,X,X,X,X,6,X,0,5,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//8
            {X,X,X,X,X,X,2,X,6,0,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X},//9
            {X,X,4,X,X,X,X,X,X,X,0,X,X,X,X,X,5,X,X,X,X,X,X,X,X,X,X,X,X,X},//10
            {X,X,X,X,X,X,X,4,X,X,X,0,4,X,X,X,X,X,X,X,X,X,X,X,7,X,X,X,X,X},//11
            {X,X,13,X,X,X,X,X,X,X,X,4,0,X,X,X,13,X,5,X,X,X,X,X,4,X,X,X,X,X},//12
            {X,X,X,X,X,X,X,X,X,X,X,X,X,0,7,X,X,X,X,X,X,3,X,X,X,X,X,X,X,X},//13
            {X,X,X,X,X,X,X,X,X,X,X,X,X,7,0,X,X,X,X,X,X,X,4,X,X,X,X,X,X,X},//14
            {12,X,X,X,X,X,X,X,X,X,X,X,X,X,X,0,X,3,X,X,X,X,X,X,X,X,X,X,X,X},//15
            {X,X,X,X,X,X,X,X,X,X,5,X,13,X,X,X,0,X,X,X,3,X,X,X,X,X,X,X,X,X},//16
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,3,X,0,X,X,X,X,X,X,X,X,X,X,X,X},//17
            {X,X,X,X,X,X,X,X,X,X,X,X,5,X,X,X,X,X,0,4,X,X,X,X,3,X,X,X,X,X},//18
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,4,0,X,X,X,X,X,X,X,7,X,X},//19
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,3,X,X,X,0,3,X,X,X,X,X,X,X,X},//20
            {X,X,X,X,X,X,X,X,X,X,X,X,X,3,X,X,X,X,X,X,3,0,X,X,X,X,X,X,X,4},//21
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,4,X,X,X,X,X,X,X,0,X,X,X,X,X,X,X},//22
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,0,2,5,X,X,X,X},//23
            {X,X,X,X,X,X,X,X,X,X,X,7,4,X,X,X,X,X,3,X,X,X,X,2,0,X,6,X,X,X},//24
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,5,X,0,X,X,X,X},//25
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,6,X,0,X,X,X},//26
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,7,X,X,X,X,X,X,X,0,4,4},//27
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,7,X,X,X,X,X,X,X,4,0,4},//28
            {X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,4,X,X,X,X,X,X,4,X} //29
    };
    String[]name = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};


    public void start(Stage primaryStage) {
        Stage navigation = primaryStage;
        Group root = new Group();
        root.getChildren();
        Scene scene = new Scene(root, 850, 500);
        scene.setFill(Paint.valueOf("lightpink"));
        navigation.setTitle("Navigation：");
        navigation.setScene(scene);
        root.requestFocus();
        primaryStage.show();

        //插入地图图片
        Image img;
        img =  new Image(getClass().getResourceAsStream("Map.PNG"));
        ImageView imv = new ImageView();
        imv.setImage(img);
        imv.setFitHeight(500);
        imv.setFitWidth(600);
        root.getChildren().add(imv);

        //定义按钮
        final Button walk = new Button();
        walk.setText("我要徒步");
        final Button bus = new Button();
        bus.setText("公交优先");
        final Button restart = new Button();
        restart.setText("重置");
        final Button exit = new Button();
        exit.setText("退出导航");

        //添加按钮
        walk.setLayoutX(610);
        walk.setLayoutY(130);
        bus.setLayoutX(610);
        bus.setLayoutY(180);
        restart.setLayoutX(730);
        restart.setLayoutY(130);
        exit.setLayoutX(730);
        exit.setLayoutY(180);
        root.getChildren().addAll(walk,bus,restart,exit);

        //添加文本框
        start_Label.setLayoutX(610);
        start_Label.setLayoutY(20);
        start_Area.setWrapText(true);
        start_Area.setLayoutX(650);
        start_Area.setLayoutY(20);
        start_Area.setMaxWidth(150);
        start_Area.setMaxHeight(20);

        destination_Label.setLayoutX(610);
        destination_Label.setLayoutY(70);
        destination_Area.setWrapText(true);
        destination_Area.setLayoutX(650);
        destination_Area.setLayoutY(70);
        destination_Area.setMaxWidth(150);
        destination_Area.setMaxHeight(20);

        result_Area.setWrapText(true);
        result_Area.setEditable(false);
        result_Area.setLayoutX(605);
        result_Area.setLayoutY(215);
        result_Area.setPrefWidth(240);
        result_Area.setPrefHeight(280);
        root.getChildren().addAll(start_Label,start_Area,destination_Label,destination_Area,result_Area);

        //按钮点击事件
        //退出导航：
        exit.setOnMouseClicked(e -> {
            navigation.close();
        });

        //重置
        restart.setOnMouseClicked(e -> {
            start_Area.setText("");
            destination_Area.setText("");
            result_Area.setText("");
        });

        //步行
        walk.setOnMouseClicked(e -> {
            String a = start_Area.getText();
            String b = destination_Area.getText();
            if(a.equals("")){
                result_Area.setText("您还未输入起点");
                start_Area.setText("");
                destination_Area.setText("");
            }else if(b.equals("")){
                result_Area.setText("您还未输入终点");
                start_Area.setText("");
                destination_Area.setText("");
            }else{
                int start = Integer.parseInt(a);
                int destination = Integer.parseInt(b);
                if(start<0||start>29){
                    result_Area.setText("请输入正确的起点代码(0-29)");
                }else if(destination<0||destination>29){
                    result_Area.setText("请输入正确的终点代码(0-29)");
                }else{
                    int [][] matrix = new int [matrixDefalt.length][matrixDefalt.length];
                    for(int i =0;i<matrix.length;i++){
                        for(int j = 0;j<matrix.length;j++){
                            matrix[i][j] = matrixDefalt[i][j];
                        }
                    }
                    ShortestPath result = new ShortestPath(matrix,name);
                    String m = result.printPath(matrix,start,destination);
                    result_Area.setText(m);
                    start_Area.setText("");
                    destination_Area.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}
