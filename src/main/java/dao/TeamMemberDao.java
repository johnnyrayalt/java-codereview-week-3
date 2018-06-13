package dao;

import models.TeamMember;

import java.util.List;

public interface TeamMemberDao {

    void add(TeamMember name);

    TeamMember findById(int id);
    List<TeamMember> getAll();
    List<TeamMember> getAllMembersByTeamId(int teamId);

    void update(int id, String name, int teamId);

    void deleteById(int id);
    void clearAllTeamMembers();
}
