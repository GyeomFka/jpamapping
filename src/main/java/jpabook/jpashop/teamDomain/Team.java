package jpabook.jpashop.teamDomain;

import jpabook.jpashop.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class  Team {

    @Id
    @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> members = new ArrayList<>();//관례사항 : ADD할때 NullPointerException이 발생하지 않도록!

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }
}
