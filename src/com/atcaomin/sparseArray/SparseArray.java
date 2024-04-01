package com.atcaomin.sparseArray;

public class SparseArray {
    //1.五子棋 二维数组->稀疏数组->二维数组
    //思路：
    //二维数组转稀疏数组
    //1.遍历原始的二位数组，得到有效数据的个数sum
    //2.根据sum创建稀疏数组 spareArr int[sum+3][3]
    //3.将二维数组的有效数据存入到稀疏数组中
    //稀疏数组转二维数组
    //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如chessArr2=int[11][11]
    //2.再读取稀疏数组后几行数据，并赋给原始的二维数组即可
    public static void main(String[] args) {
        //创建一个原始的11*11
        //0表示没有棋子   1表示黑子，2表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][3] = 2;
        //输出原始的二维数组
        for(int[] row : chessArr1) {
            for(int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转成稀疏数组,第一列表示行，第二列表示列，第三列表示值
        int sum = 0;
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArr = new int[sum + 1][3];
        //第一行写棋盘有多少行多少列
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        //赋值
        int count  = 1;
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }
        System.out.println();
        //显示稀疏数组
        for (int i = 0; i < count; i++) {
            System.out.printf("%d\t%d\t%d\t",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            System.out.println();
        }
    }
}