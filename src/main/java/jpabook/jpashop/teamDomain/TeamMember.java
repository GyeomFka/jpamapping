package jpabook.jpashop.teamDomain;

import javax.persistence.*;

@Entity
public class TeamMember {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne // 하나의 Team이 여러개의 Member를 갖고있다.
    @JoinColumn(name ="TEAM_ID") //관계를 맺어주고 Join하는 컬럼이 뭔지 설정을 해줘야한다.
    private Team team; //JPA에게 이 둘의 관계가 1:1 인지 N:1인지 알려줘야한다.

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    //Team_Id를 그대로 갖고있다 ? -> 참조가 아니라 테이블에 맞춰 외래키값을 그대로 갖고잇는것이다.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

