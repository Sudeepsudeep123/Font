package dev.edmt.advancedsearchbar;

import java.util.ArrayList;

/**
 * Created by sudeepbajracharya on 11/30/17.
 */

public class GroupInfo {

    private String name;
    private String url;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public ArrayList<ChildInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<ChildInfo> productList) {
        this.list = productList;
    }

}