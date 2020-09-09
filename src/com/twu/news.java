package com.twu;



public class news {
    public String name;
    public int index =0;
    public int hot =0;
    public int price=0;
    public boolean isSuperHot=false;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHot() {
        return hot;
    }
    public void setHot(int hot) {
        this.hot = hot;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isSuperHot() {
        return isSuperHot;
    }
    public void setSuperHot(boolean isSuperHot) {
        this.isSuperHot = isSuperHot;
    }

}

