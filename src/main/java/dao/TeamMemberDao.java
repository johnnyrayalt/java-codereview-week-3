package dao;

import models.TeamMember;

import java.util.List;

public interface TeamMemberDao {


    void add(TeamMember name);

    TeamMember findById(int id);
    List<TeamMember> getAll();

//    void update(int id, String name);

//    void deleteById(int id);
//    void clearAllTeamMembers();
}
