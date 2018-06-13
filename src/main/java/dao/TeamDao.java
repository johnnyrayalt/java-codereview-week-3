package dao;

import models.Team;
import models.TeamMember;

import java.util.List;

public interface TeamDao {

    List<Team> getAll();

    void add(Team team);

    Team findById(int id);
    List<TeamMember> getAllTeamMembersByTeam(int teamId);

    void update(int id, String teamName, String teamDescription);

    void deleteById(int id);
    void clearAllTeams();

}
