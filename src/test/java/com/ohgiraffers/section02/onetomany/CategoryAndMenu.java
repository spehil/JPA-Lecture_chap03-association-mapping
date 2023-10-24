package com.ohgiraffers.section02.onetomany;

import javax.persistence.*;
import java.util.List;

@Entity(name = "category_and_menu")
@Table(name = "tbl_category")
public class CategoryAndMenu {

    @Id
    private int categoryCode;
    private  String categoryName;

    private Integer refCategoryCode;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoryCode")//FK의 명칭을 적어준다
    private List<Menu> menuList;//하나의 카테고리느 여러개의 메뉴를 참고한다는 의미
    //onetomany ,manytoone의 관계를 잘 설정하는게 중요하고 join할때 FK작성을 잘해주는 것이 중요하다!!!


    public CategoryAndMenu() {
    }

    public CategoryAndMenu(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menuList = menuList;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "CategoryAndMenu{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menuList=" + menuList +
                '}';
    }
}
