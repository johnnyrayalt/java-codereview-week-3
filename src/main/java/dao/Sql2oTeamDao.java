package dao;

import models.Team;
import models.TeamMember;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeamDao implements TeamDao{

    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Team team) {
        String sql = "INSERT INTO teams (teamName, teamDescription) VALUES (:teamName, :teamDescription)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(team)
                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Team> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams") //raw sql
                    .executeAndFetch(Team.class); //fetch a list
        }
    }

    @Override
    public Team findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Team.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String newTeamName) {
        String sql = "UPDATE teams SET teamName = :teamName WHERE id=:id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("teamName", newTeamName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from teams WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTeams() {
        String sql = "DELETE from teams";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<TeamMember> getAllTeamMembersByTeam(int teamid) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM team_members WHERE teamid = :teamid")
                    .addParameter("teamid", teamid)
                    .executeAndFetch(TeamMember.class);
        }
    }
}
