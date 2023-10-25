package com.ohgiraffers.section03.bidirection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "bidirection-category")
@Table(name = "tbl_category")
public class Category {

    @Id
    private  int categoryCode;
    private  String categoryName;

    private  Integer refCategoryCode;
    @OneToMany(mappedBy = "category")//mappedBy :진짜 연관관계의 필드명을 작성해준다.
    private List<Menu> menuList;//카테고리쪽에서 메뉴를 참조한다는 필드 작성

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
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
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
