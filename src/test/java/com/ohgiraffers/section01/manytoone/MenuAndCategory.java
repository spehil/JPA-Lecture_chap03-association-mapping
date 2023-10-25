package com.ohgiraffers.section01.manytoone;

import javax.persistence.*;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {//Category를 참조하는  MenuAndCategory 엔티티

    @Id
    private  int menuCode;

    private String menuName;

    private int menuPrice;

    //메뉴 클래스에서 카테고리를 참조하는게 다중성을 가지고있다.

    @ManyToOne(cascade = CascadeType.PERSIST)// CascadeType.PERSIST:저장시 연속성 전이 //AssociationMapping할때는 다중성에 대한 표기를 해줘야한다.
    @JoinColumn(name = "categoryCode") //조인할때 컬럼 명시. FK에 해당하는 컬럼명 //설정에서 자동으로 카멜케이스를 언더바로 바꿔주고 있으서 카멜케이스로 작성해준다.
    private  Category category;//카테고리 엔티티를 참조하도록 작성

    private String orderableStatus;

    public MenuAndCategory() {
    }

    public MenuAndCategory(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
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
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuAndCategory{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
