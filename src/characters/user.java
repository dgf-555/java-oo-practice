package characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.twu.*;
import operation.*;

public class user {
    String username;
    int tickets = 10;
    public static String managername = "dgf";
    public static String managerkey = "dgf19981229";

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getTickets(){
        return tickets;
    }
    public void setTickets(int tickets){
        this.tickets = tickets;
    }



}

