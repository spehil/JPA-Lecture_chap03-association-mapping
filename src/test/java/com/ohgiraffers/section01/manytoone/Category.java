package com.ohgiraffers.section01.manytoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "category_section01")
@Table(name = "tbl_category")//테이블이름을 써준다.
public class Category {

    @Id//primaryKey
    //카멜케이스를 언더스로어로 자동 매핑해주는 설정을 넣어줬으므로 @Column 어노테이션을 작성하지 않아도 규칙에 맞게 작성만했으면 자동매핑된다. -><property name="hibernate.physical_naming_strategy" value="org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"/>
    private int categoryCode;
    private String categoryName;

    private Integer refCategoryCode;

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
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

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}

