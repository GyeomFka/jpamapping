package jpabook.jpashop;

import jpabook.jpashop.teamDomain.Team;
import jpabook.jpashop.teamDomain.TeamMember;

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
            //객체를 Table에 맞춰서 모델링 할때의 문제점.
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            TeamMember teamMember = new TeamMember();
            teamMember.setUsername("member1");
            teamMember.setTeam(team);
            //teamMember.setTeamId(team.getId());//이게 참 애매하다... 객체지향 적인 느낌이 나질 않는다.
            em.persist(teamMember);

            /*
            * SELECT * FROM TEAMMEMBER M
             * JOIN TEAM T ON M.TEAM_ID = T.TEAM_ID //ANSI 표준문법법
           * */

            /*조희 할 때의 테이블 모델링의 한계점
            * 찾아온 멤버가 어느팀 소속인지 알고싶을경우 ?
            * -> 객체를 테이블에 맞춰서 데이터 중심으로 모델링하면, (객체간) 협력 관계를 만들 수 없다.
            *
            * TeamMember findTeamMember = em.find(TeamMember.class, teamMember.getTeamId());
            * ================================================================
            * Long findTeamId = findTeamMember.getTeamId();
            * Team findTeam = em.find(Team.class, findTeamId);
            * ================================================================
            * Team findTeam = findTeamMember.getTeam(); ->조회할때 이렇게 간단하게 조회가 가능하다.
            * 객체지향 스럽게 Ref를 다룰 수 있다.
            *
            * */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
