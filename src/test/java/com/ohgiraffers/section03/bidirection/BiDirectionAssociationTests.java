package com.ohgiraffers.section03.bidirection;

import org.junit.jupiter.api.*;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiDirectionAssociationTests {



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
    public void 양방향_연관관계_매핑_조회_테스트(){

        //given
        int menuCode = 10;//DB상에 데이터가 존재하는걸로 데이터를 작성한다.

        int categoryCode = 10;//DB상에 데이터가 존재하는걸로 데이터를 작성한다.

        //when
        //진짜 연관관계는 최초 조회시 조인결과를 인출한다.
        Menu foundMenu = entityManager.find(Menu.class, menuCode);//양뱡향으로 어노테이션 작성했는데 find()시 어떻게 동작하는지 확인
       //가짜 연관관계는 Category 엔티티만 조회하고 필요시 연관관계 엔티티를 조회하는 쿼리를 다시 실행하게 된다.
        Category foundCategory = entityManager.find(Category.class, categoryCode);

        //then
        assertEquals(menuCode, foundMenu.getMenuCode());
        assertEquals(categoryCode, foundCategory.getCategoryCode());

        //주의사항
        /*toString 오버라이딩 시 양방향 연관관계는 재귀호출이 일어나기 때문에 stackOverflowError가 발생한다.
        * 엔티티의 주인이 아닌쪽(category)의 toString에서 연관 객체 부분이 출력되지 않도록 삭제한다.
        * 특히 자동완성 or 롬북 라이브러리 사용시 주의한다. */
        System.out.println(foundMenu);
        System.out.println(foundCategory);//menu객체의 toString할때 Category를 참조하고 카테고리에 toString에서 menu를 참조해서 계속 서로를 불러오게됨.

        //메뉴리스트가 필요한 순간 다시 조회 쿼리가 동작하며 참조한다.
        foundCategory.getMenuList().forEach(System.out::println);
        //양방향 참조시 메뉴->카테고리->메뉴리스트 참조도 가능하다.
        foundMenu.getCategory().getMenuList().forEach(System.out::println);

    }
    @Test
    public void 양방향_연관관계_주인_객체를_이용한_삽입_테스트(){
        //given
        Menu menu = new Menu();
        menu.setMenuCode(2222);
        menu.setMenuName("연관관계주인메뉴");
        menu.setMenuPrice(10000);
        menu.setOrderableStatus("Y");
        //어떤카테고리에 대한 메뉴인지가 필요하므로
        menu.setCategory(entityManager.find(Category.class, 4));//신규카테고리가 아니라 기존카테고리일때, 인티티를 설정해준다. 카테고리 4번으로 찾아옴
        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(menu);
        entityTransaction.commit();
        //then
        Menu foundmenu = entityManager.find(Menu.class, menu.getMenuCode());
        assertEquals(menu.getMenuCode(), foundmenu.getMenuCode());//찾은메뉴코드가 일치하는지
        System.out.println(foundmenu);// 메뉴코드4번으로 잘들어갔는지 출력

    }

    @Test
    public void 양뱡향_연관관계_주인이_아닌_객체를_이용한_삽입_테스트(){

        //카테고리를 이용한 삽입을 하겠다는 의미


        //given
        Category category = new Category();
        category.setCategoryCode(1004);
        category.setCategoryName("양방향카테고리");
        category.setRefCategoryCode(1);
        //menuList에 대한 정보를 insert를 하지 않는다면 작성안해도됨(필수 작성정보가 아니므로)
        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(category);
        entityTransaction.commit();
        //then
        Category foundCategory = entityManager.find(Category.class, category.getRefCategoryCode());
        assertEquals(category.getCategoryCode(), foundCategory.getCategoryCode());
        System.out.println(foundCategory);

    }


}
