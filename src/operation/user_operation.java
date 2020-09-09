package operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

import characters.*;
import com.twu.*;

public class user_operation {
    static int order;
    //初始化用户
    public void initial(List<news> newsArrayList){
        System.out.println("设置您的名称：请输入！");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        user user1 = new user();
        user1.setUsername(name);
        System.out.println("设置成功，你的用户名是:"+user1.getUsername());
        usermenu(newsArrayList,user1);
    }
    //用户菜单,区别于管理员菜单保留了user参数是为了投票用，而管理员不需要投票
    public void usermenu(List<news> newsArrayList,user user){
        System.out.println("你可以进行如下操作:\n1、查看热搜排行榜\n2、给热搜事件投票\n3、购买热搜\n4、添加热搜\n5、退出:");
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        switch (num1){
            case 1:
                show_hotSearch(newsArrayList);
                usermenu(newsArrayList,user);
                break;
            case 2:
                vote_hotSearch(newsArrayList,user);
                usermenu(newsArrayList,user);
                break;
            case 3:
                buy_hotSearch(newsArrayList);
                usermenu(newsArrayList,user);
                break;
            case 4:
                add_hotSearch(newsArrayList,false);
                usermenu(newsArrayList,user);
                break;
            case 5:
                Main.mainmenus(newsArrayList);
                break;
        }
    }
    //展示热搜
    static void show_hotSearch(List<news> newsArrayList){
        sort_hotSearch(newsArrayList);
        if(newsArrayList!=null){
            for(int i=0;i<newsArrayList.size();i++) {
                System.out.println(String.format("%d   %s   %d",i+1,newsArrayList.get(i).getName(),newsArrayList.get(i).getHot()));
            }
        }
        if(newsArrayList.size()==0){
            System.out.println("当前排行榜没有热搜!");
        }
    }
    static void buy_hotSearch(List<news> newsArrayList){
        int order =0;
        //输入想购买热搜的名称
        System.out.println("请输入你要购买的热搜事件的名字:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        //查看是否存在该热搜
        String index = isExist(newsArrayList,name);
        //输入正确
        if(!index.equals("not")) {
            System.out.println("你想为其购买的位序是:");
            Scanner scanner1 = new Scanner(System.in);
            int num = scanner1.nextInt();
            //位序输入对不对
            if(num<1||num>newsArrayList.size()){
                System.out.println("您输入的位序错误!");
            }
            //位序输入正确
            else {
                //输入购买金额
                System.out.println("你的出价是:");
                Scanner scanner2 = new Scanner(System.in);
                int num1 = scanner2.nextInt();
                //这个位序还没人买
                if(newsArrayList.get(num-1).getPrice()==0){
                    //找到该热搜当前位序
                    for(int i=0;i<newsArrayList.size();i++){
                        if(newsArrayList.get(i).getName().equals(name)){
                            order = i;//设为静态的，每次都会变动
                            break;
                        }
                    }
                    newsArrayList.get(order).setIndex(num);
                    newsArrayList.get(order).setPrice(num1);
                    System.out.println("购买成功！");
                    //设置好了，进行排序
                    sort_hotSearch(newsArrayList);
                }
                //这个位序已经被人买了，要看钱够不够了
                else if(newsArrayList.get(num-1).getPrice()!=0){
                    //找到该热搜当前位序
                    for(int i=0;i<newsArrayList.size();i++){
                        if(newsArrayList.get(i).getName().equals(name)){
                            order = i;//设为静态的，每次都会变动
                            break;
                        }
                    }
                    //钱够了，删除之前那个
                    if(num1 > newsArrayList.get(num-1).getPrice()){
                        newsArrayList.get(order).setIndex(num);
                        newsArrayList.get(order).setPrice(num1);
                        newsArrayList.remove(num-1);
                        System.out.println("购买成功！");
                    }
                    //钱不够
                    else if(num1<1||newsArrayList.get(num-1).getPrice()>=num1){
                        System.out.println("金额不足，购买失败！");
                    }
                }
            }
        }
        //输入不存在的热搜
        else{
            System.out.println("不存在该热搜，输入错误！");
        }
    }
    //添加热搜or超级热搜
    static void add_hotSearch(List<news> newsArrayList,boolean flag) {
        System.out.println("请输入你要添加的热搜事件的名字：");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        //1.判断该热搜是否已经存在
        String index = isExist(newsArrayList,name);
        //2.如果存在的话不添加
        if(!index.equals("not")) {
            System.out.println("该热搜已存在于热搜列表中！");
        }else {
            //3.如果不存在的话添加新的热搜事件
            news event = new news();
            event.setIndex(newsArrayList.size());//设置热搜排名
            event.setName(name);//输入的热搜描述
            if(flag) {
                event.setSuperHot(true);//设计超级热搜
            }
            newsArrayList.add(event);
            System.out.println("添加成功");
        }
    }

    //给热搜投票
    static void vote_hotSearch(List<news> newsArrayList,user user){
        //输入想投票的热搜名称
        System.out.println("你想给哪个热搜投票？");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        //查看是否存在该热搜
        String index = isExist(newsArrayList,name);
        //输入正确
        if(!index.equals("not")) {
            System.out.println("你想给它投几票？:"+"("+"当前你拥有的票数:"+user.getTickets()+")");
            Scanner scanner1 = new Scanner(System.in);
            int num = scanner1.nextInt();
            //票够不够用
            if(num>user.getTickets()||num<0){
                System.out.println("您输入的票数超额或者错误!");
            }
            else {
                for(int i=0;i<newsArrayList.size();i++){
                    //根据名称找到list中的对象
                    if(newsArrayList.get(i).getName().equals(name)){
                        //超级热搜
                        if(newsArrayList.get(i).isSuperHot==true){
                            newsArrayList.get(i).setHot(newsArrayList.get(i).getHot()+2*num);//设置热度
                        }
                        //普通热搜
                        else
                            newsArrayList.get(i).setHot(newsArrayList.get(i).getHot()+num);//设置热度
                        user.setTickets(user.getTickets()-num);//投完票后设置票数
                        break;
                    }
                }
            }
        }
        //输入不存在的热搜
        else{
            System.out.println("不存在该热搜，输入错误！");
        }
    }
    //给热搜排序，包括index和hot两个点
    static void sort_hotSearch(List<news> newsArrayList) {
        //先取出来那些竞过价的，把剩下的按热度排一遍，再把购买的按照定好的位序插入
        //取出来那些竞过价的
        ArrayList< news > buy_list = new ArrayList < news > ();//新建列表
        for (int i = 0; i < newsArrayList.size(); i++) {
            if (newsArrayList.get(i).getPrice() != 0) {//把购买过的拿出来
                news event = new news();
                event.setName(newsArrayList.get(i).getName());//输入热搜名称
                event.setPrice(newsArrayList.get(i).getPrice());//输入热搜价格
                event.setIndex(newsArrayList.get(i).getIndex());//输入热搜位序
                event.setHot(newsArrayList.get(i).getHot());//输入热搜热度
                buy_list.add(event);//加入到新建列表中保存起来
                newsArrayList.remove(i);//删掉
            }
        }
        //竞过价的按照位序排一遍，等会好插入
        Collections.sort(buy_list, new Comparator<news>(){
            public int compare(news o1, news o2) {
                //排序属性
                if(o1.getIndex() < o2.getIndex()){
                    return 1;
                }
                if(o1.getIndex() == o2.getIndex()){
                    return 0;
                }
                return -1;
            }
        });
        //把列表中没有竞过价的按照热度排一遍
        Collections.sort(newsArrayList, new Comparator<news>(){
            public int compare(news o1, news o2) {
                //排序属性
                if(o1.getHot() < o2.getHot()){
                    return 1;
                }
                if(o1.getHot() == o2.getHot()){
                    return 0;
                }
                return -1;
            }
        });
        //插入
        for(int i=0;i<buy_list.size();i++){
            newsArrayList.add(buy_list.get(i).getIndex()-1,buy_list.get(i));
        }
    }

    //判断热搜是否存在
    static String isExist(List<news> newsArrayList, String name) {
        boolean isExist = false;
        if(newsArrayList!=null) {
            for(int i=0;i<newsArrayList.size();i++) {
                if(newsArrayList.get(i).getName().equals(name)) {
                    return i+"";
                }
            }
        }
        return "not";
    }
}

