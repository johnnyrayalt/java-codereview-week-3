package dao;

import models.TeamMember;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oTeamMemberDao implements TeamMemberDao {

    private final Sql2o sql2o;

    public Sql2oTeamMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(TeamMember newTeamMember) {
        String sql = "INSERT INTO newTeamMembers (teamMemberName) VALUES (:teamMemberName)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(newTeamMember)
                    .executeUpdate()
                    .getKey();
            newTeamMember.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
