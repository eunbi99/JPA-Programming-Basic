package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 데이터베이스 트랜잭션 시작
        try {
           /* Member 저장
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
           */

            /* Member 조회
            Member findMember = em.find(Member.class,1L);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            */

            /* Member 삭제
            Member findMember = em.find(Member.class,1L);
            em.remove(findMember);
             */

            /* Member 수정
            Member findMember = em.find(Member.class,3L);
            findMember.setName("HelloJPA");
            */

            /* Member객체를 전부 가져온다.
           List<Member> result = em.createQuery("select m from Member as m",Member.class)
                   .setFirstResult(5)
                   .setMaxResults(8)
                   .getResultList();
           for (Member member : result) {
               System.out.println("member.name" + member.getName());
           }
           */

            /*
            // 비영속
            Member member = new Member();
            member.setId(101L); // 1차 캐시에 저장
            member.setName("Hello JPA!");

            // 영속
            System.out.println("==== BEFORE ====");
            em.persist(member);
            System.out.println("==== AFTER ====");
            */

            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L); // 영속 컨텍스트에 저장되어있는 1차 캐시를 먼저 확인하고 반환! - select 쿼리 x

            System.out.println("result = " + (findMember1 == findMember2)); // true

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
