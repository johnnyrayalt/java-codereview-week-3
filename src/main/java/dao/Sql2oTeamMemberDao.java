package dao;

import models.Team;
import models.TeamMember;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeamMemberDao implements TeamMemberDao {

    private final Sql2o sql2o;

    public Sql2oTeamMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(TeamMember teamMember) {
        String sql = "INSERT INTO team_members (name, teamid) VALUES (:name, :teamId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(teamMember)
                    .executeUpdate()
                    .getKey();
            teamMember.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public TeamMember findById(int id) {
        String sql = "SELECT * FROM team_members WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(TeamMember.class);
        }
    }

    @Override
    public List<TeamMember> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM team_members")
                    .executeAndFetch(TeamMember.class);
        }
    }

    @Override
    public void update(int id, String newName, int newTeamId) {
        String sql = "UPDATE team_members SET (name, teamid) = (:name, :teamid) WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", newName)
                    .addParameter("teamid", newTeamId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM team_members WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTeamMembers() {
        String sql = "DELETE FROM team_members";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
