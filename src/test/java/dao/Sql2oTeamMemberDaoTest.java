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
        TeamMember teamMember = new TeamMember();
        teamMember.setName("jeff");
        int originalTeamMemberId = teamMember.getId();
        teamMemberDao.add(teamMember);
        assertNotEquals(originalTeamMemberId, teamMember.getId());
    }

    @Test
    public void getAllReturnsAllTeamMembers() throws Exception {
        TeamMember newTeamMember = setUpNewTeamMemberName();
        TeamMember newTeamMember1 = setUpNewTeamMemberName();
        assertEquals(2, teamMemberDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectTeamMember() {
        TeamMember teamMember = setUpNewTeamMemberName();
        TeamMember teamMember1 = setUpNewTeamMemberName();
        teamMemberDao.findById(teamMember.getId());
        assertEquals(2, teamMember1.getId());
    }



    public TeamMember setUpNewTeamMemberName() {
        TeamMember teamMember = new TeamMember();
        teamMember.setName("jeff");
        teamMemberDao.add(teamMember);
        return teamMember;
    }

    public Team setUpNewTeam() {
        Team testTeam = new Team();
        testTeam.setTeamName("Team 1");
        testTeam.setTeamDescription("Team 1 Description");
        teamDao.add(testTeam);
        return testTeam;
    }

}