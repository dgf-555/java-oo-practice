package com.twu;

import operation.manager_operation;
import operation.user_operation;
import com.twu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ArrayList<news> newsArrayList = new ArrayList <news> ();
        mainmenus(newsArrayList);
    }
    public static void mainmenus(List<news> newsArrayList){
        System.out.println("欢迎来到热搜排行榜！\n请选择你的人物角色：\n1、普通用户 2、管理员 3、退出:");
        user_operation u = new user_operation();
        manager_operation m = new manager_operation();
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        switch (num1){
            case 1:
                u.initial(newsArrayList);
                break;
            case 2:
                m.initial(newsArrayList);
                break;
            case 3:
                break;
        }
    }
}
