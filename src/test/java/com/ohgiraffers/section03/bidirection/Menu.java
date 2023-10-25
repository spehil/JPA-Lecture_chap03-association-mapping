package com.ohgiraffers.section03.bidirection;

import javax.persistence.*;

@Entity(name = "bidirection-menu")
@Table(name = "tbl_menu")
public class Menu {//서로 참조하도록 작성 Menu랑 Category

    @Id
    private int menuCode;
    private  String menuName;
    private  int menuPrice;
    @ManyToOne
    @JoinColumn(name = "categoryCode")//진짜 연관관계 연관관계의 주인F :FK
    private  Category category;

    private String OrderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        OrderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return OrderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        OrderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", OrderableStatus='" + OrderableStatus + '\'' +
                '}';
    }
}
