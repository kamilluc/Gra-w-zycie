package gui.company;

import com.company.tgol;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class myGame extends Application {

    private static final double fps=4;
    private static final int gameWidth=50;
    private static final int gameHeight=40;
    private static final int density=50;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Game of the Life");
        //  theStage.setWidth(1000);
//
//        Group root=new Group();
//        Scene theScene=new Scene(root);
//        theStage.setScene(theScene);
//
//        Canvas canvas=new Canvas(600,200);
//        root.getChildren().add(canvas);
//
//        GraphicsContext gc=canvas.getGraphicsContext2D();
//
//        gc.setFill(Color.ORANGE);
//        gc.setStroke(Color.DARKORANGE);
//        gc.setLineWidth(2);
//        Font theFont=Font.font("Times New Roman", FontWeight.BOLD, 48);
//        gc.setFont(theFont);
//        gc.fillText("Game of the Life", 60,50);
//        gc.strokeText("Game of the Life", 60,50);
//
//        theStage.show();
        tgol mygame = new tgol(gameWidth, gameHeight, density);
        mygame.start();

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( gameWidth*20, gameHeight*20 );
        root.getChildren().add( canvas );

        //new
        ArrayList<String> input = new ArrayList<String>();
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
//                        else
//                            input.remove(code);
                    }
                });

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove(code);
                        //input.clear();
                    }
                });
        //end

        theScene.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        int x=(int)(e.getX()/20);
                        int y=(int)(e.getY()/20);
                        System.out.println(x+" "+y);
                        mygame.setPoint(y,x);

//                        if ( targetData.containsPoint( e.getX(), e.getY() ) )
//                        {
//                            double x = 50 + 400 * Math.random();
//                            double y = 50 + 400 * Math.random();
//                            targetData.setCenter(x,y);
//                            points.value++;
//                        }
//                        else
//                            points.value = 0;
                    }
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image earth = new Image( "file:earth.png" );
        Image sun   = new Image( "file:sun.png" );
        Image space = new Image("file:space.png" );

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(Duration.seconds(1.0/fps), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae)
                    {
//                        if (input.contains("SPACE"))
//                            gameLoop.pause();
//                        if (input.contains("LEFT"))
//                            gameLoop.play();
                        if (input.contains("SPACE")) {
                            try {
                                gameLoop.pause();
                                TimeUnit.SECONDS.sleep(10);
                            } catch (Exception e) {
                            }
                            gameLoop.play();
                        }
                        gc.clearRect(0, 0, gameWidth*20,gameHeight*20);

                        gc.drawImage(space,0,0,gameWidth*20,gameHeight*20);

                        for(int i=0;i<gameHeight;i++){
                            for(int j=0;j<gameWidth;j++){
                                if (mygame.map[i][j]){
                                    gc.drawImage(earth,j*20,i*20,20,20);
                                }
                                else
                                    gc.drawImage(sun,j*20,i*20,20,20);
                            }
                        }
                        //System.out.println(input.get(0)+input.get(1)+input.get(2));
                        mygame.next();
                       // mygame.show();
                    }
                });
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();

        theStage.show();
    }
}