package com.tna.ssmdemo.entity;

/*
 * 求解迷宫路径 找出一条能走通的道路输出
 * */
import java.util.ArrayList;
import java.util.List;

public class ReCall {
    private int[][] map={
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,0,0,0,0},
            {0,1,1,1,0},
            {0,0,0,1,0}};
    private List<int[]> teps=new ArrayList<int[]>();
    public static void main(String[] args){
        ReCall demo=new ReCall();
        demo.dfs(0,0);
    }
    public boolean dfs(int x,int y){
        if(x==4&&y==4){
            for (int[] tep :
                    this.teps) {
                System.out.print("("+tep[0]+" "+tep[1]+")->");
            }
            System.out.println();
            return true;
        }
        else{
            if(x+1<5&&map[x+1][y]==0){
                teps.add(new int[]{x+1,y});
                map[x+1][y]=1;
                if(dfs(x+1,y)){
                    return true;
                }
                map[x+1][y]=0;
                teps.remove(teps.size()-1);
            }
            if(y+1<5&&map[x][y+1]==0){
                teps.add(new int[]{x,y+1});
                map[x][y+1]=1;
                if(dfs(x,y+1)){
                    return true;
                }
                map[x][y+1]=0;
                teps.remove(teps.size()-1);
            }
            if(y-1>=0&&map[x][y-1]==0){
                teps.add(new int[]{x,y-1});
                map[x][y-1]=1;
                if(dfs(x,y-1)){
                    return true;
                }
                map[x][y-1]=0;
                teps.remove(teps.size()-1);
            }
            if(x-1>=0&&map[x-1][y]==0){
                teps.add(new int[]{x-1,y});
                map[x-1][y]=1;
                if(dfs(x-1,y)){
                    return true;
                }
                map[x-1][y]=0;
                teps.remove(teps.size()-1);
            }
            return false;
        }
    }
}

