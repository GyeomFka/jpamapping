package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // -> Factory는 하나만 만들어야 한다.
        EntityManager em = emf.createEntityManager(); // -> connection 얻고 종료되는 일괄의 과정은 em단위로 실행한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
