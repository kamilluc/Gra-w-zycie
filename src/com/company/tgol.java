package com.company;

import java.util.Random;

/**
 * Created by Kamil on 24.04.2017.
 */
public class tgol {
    public boolean[][] map,newmap;
    private int width,height;
    private int density;

    public tgol(int width, int height, int density) {
        this.width = (width+2);
        this.height = (height+2);
        this.density=density;
        map=new boolean[height+2][width+2];
        newmap=new boolean[height+2][width+2];
    }

    public void start(){
        Random rng=new Random();
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++) {

                if((rng.nextInt(100-0))<=density) {
                    map[i][j] = true;
                    newmap[i][j] = true;
                }
                else
                {
                    map[i][j] = false;
                    newmap[i][j] = false;
                }
                   // map[i][j] = rng.nextBoolean();
                //newmap[i][j]=map[i][j];
            }
        }
    }

    private void updateMaps(){
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++)
                map[i][j]=newmap[i][j];
        }
    }

    public void show(){
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++){
                if(map[i][j])
                    System.out.print("#  ");
                else
                    System.out.print("   ");
            }
            System.out.println();
        }
       // System.out.println("");
        for(int i=0;i<width;i++)
            System.out.print("===");
        System.out.println("");
    }

    private int logic(int i, int j){
        int alive=0;

        //if(i>0 && i<height-1 && j>0 && j<width-1) {
            //gora
            if (map[i - 1][j - 1]) alive++;
            if (map[i - 1][j]) alive++;
            if (map[i - 1][j + 1]) alive++;
            //boki
            if (map[i][j + 1]) alive++;
            if (map[i][j - 1]) alive++;
            //dol
            if (map[i + 1][j - 1]) alive++;
            if (map[i + 1][j]) alive++;
            if (map[i + 1][j + 1]) alive++;
        //}
        return alive;
    }

    public void next(){
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++) {
                int tmp=logic(i,j);
                if(map[i][j] && (tmp==2 || tmp==3))
                    newmap[i][j]=true;
                else
                    newmap[i][j]=false;
                if(!map[i][j] && tmp==3)
                    newmap[i][j]=true;
            }
        }
        updateMaps();
    }

    public void setPoint(int x, int y){
        map[x][y]=!map[x][y];
    }
}
