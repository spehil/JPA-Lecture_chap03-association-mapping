package com.ohgiraffers.section01.manytoone;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManyToOneAssociationTests {

    private  static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public  static void initFactoty(){

        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");

    }

    @BeforeEach//EntityManager는 매번 만들어져야하므로 test하나가 수행되기 전마다 수행되는 BeforeEach 작성
    public void initManager(){

        entityManager = entityManagerFactory.createEntityManager();

    }

    @AfterAll //BeforeAll과 AfterAll은 static으로 작성한다.
    public  static  void closeFactory(){

        entityManagerFactory.close();
    }


    @AfterEach
    public void closeManager(){
        entityManager.close();
    }


    @Test
    public void 다대일_연관관계_객체_그래프_탐색을_이용한_조회_테스트(){

        //given
        int menuCode = 15;
        //when
        //조회시 조인 구문이 실행되면 연관 테이블을 함께 조회해온다.
        MenuAndCategory foundMenu = entityManager.find(MenuAndCategory.class, menuCode);
        Category menuCategory = foundMenu.getCategory();
        //then
        assertNotNull(menuCategory);
        System.out.println("menuCategory = " + menuCategory);

    }

    @Test
    public void 다대일_연관관계_객체지향쿼리_사용한_카테고리_이름_조회_테스트(){
        //객체지향쿼리가 JQL을 말한다.

        //given
        String jpql = "SELECT c.categoryName FROM menu_and_category m JOIN m.category c WHERE m.menuCode = 15";

        //when
        String categoryName = entityManager.createQuery(jpql, String.class).getSingleResult();//(수행하고자하는 jqpl문자, 반환타입)
        //then
        assertNotNull(categoryName);
        System.out.println("categoryName = " + categoryName);
    }


    @Test
    public void 다대일_연관관계_객체_삽입_테스트(){//연관관계의 데이터들 모두 insert할수 있게 작성
        //given
        MenuAndCategory menuAndCategory = new MenuAndCategory();
        menuAndCategory.setMenuCode(9999);
        menuAndCategory.setMenuName("죽방멸치빙수");
        menuAndCategory.setMenuPrice(30000);

        menuAndCategory.setOrderableStatus("Y");

        //카테고리에도 데이터를 채워서 같이insert되는것을 해보기
        Category category = new Category();
        category.setCategoryCode(444);
        category.setCategoryName("신규카테고리");
        category.setRefCategoryCode(1);
        menuAndCategory.setCategory(category);
        menuAndCategory.setOrderableStatus("Y");

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(menuAndCategory);
        entityTransaction.commit();
        //then
        MenuAndCategory foundMenuAndCategory = entityManager.find(MenuAndCategory.class, 9999);
        assertEquals(9999, foundMenuAndCategory.getMenuCode());
        assertEquals(444, foundMenuAndCategory.getCategory().getCategoryCode());    }
}
