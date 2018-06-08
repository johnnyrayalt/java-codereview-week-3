package dao;

import models.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oTeamMemberDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oTeamMemberDao teamMemberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamMemberDao = new Sql2oTeamMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingTeamMemberSetsId() throws Exception{
        TeamMember newTeamMember = setUpNewTeamMemberName();
        newTeamMember.setName("jeff");
        int originalTeamMemberId = newTeamMember.getId();
        teamMemberDao.add(newTeamMember);
        assertNotEquals(originalTeamMemberId, newTeamMember.getId());
    }

    public TeamMember setUpNewTeamMemberName() throws Exception {
        return new TeamMember();
    }

}