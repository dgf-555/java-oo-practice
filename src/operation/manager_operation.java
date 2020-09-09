package operation;

import characters.*;
import com.twu.*;

import java.util.List;
import java.util.Scanner;

public class manager_operation {
    public void initial(List<news> newsArrayList){
        System.out.println("输入管理员账号:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("输入管理员密码:");
        Scanner scanner1 = new Scanner(System.in);
        String key = scanner1.next();
        //用户名密码校验
        if((user.managername.equals(name))&&(user.managerkey.equals(key)))
            managermenu(newsArrayList);
        else{
            System.out.println("管理员校验失败,返回主界面！");
            Main.mainmenus(newsArrayList);
        }
    }
    //管理员菜单，和用户区别见用户菜单注释
    public void managermenu(List<news> newsArrayList){
        System.out.println("你可以进行如下操作:\n1、查看热搜排行榜\n2、添加热搜\n3、添加超级热搜\n4、退出:");
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        switch (num1){
            case 1:
                user_operation.show_hotSearch(newsArrayList);
                managermenu(newsArrayList);
                break;
            case 2:
                user_operation.add_hotSearch(newsArrayList,false);
                managermenu(newsArrayList);
                break;
            case 3:
                user_operation.add_hotSearch(newsArrayList,true);
                managermenu(newsArrayList);
                break;
            case 4:
                Main.mainmenus(newsArrayList);
                break;
        }
    }


}
