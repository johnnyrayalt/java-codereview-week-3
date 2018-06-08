package dao;

import models.Team;

import java.util.List;

public interface TeamDao {

    List<Team> getAll();

    void add(Team team);

    Team findById(int id);

    void update(int id, String teamName);

    void deleteById(int id);
    void clearAllTeams();

}
