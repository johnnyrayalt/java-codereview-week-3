package dao;

import models.Team;
import models.TeamMember;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oTeamMemberDao teamMemberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        teamMemberDao = new Sql2oTeamMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingTeamsSetsId() throws Exception{
        Team team = new Team();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    private void assertNotEquals(int originalTeamId, int id) {
    }

    @Test
    public void existingTeamsCanBeFoundById() throws Exception{
        Team team = new Team();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void addedTasksAreReturnedFromgetAll() throws Exception {
        Team team = new Team();
        teamDao.add(team);
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void noTeamsReturnsEmptyList() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateChangesTeamContent() throws Exception {
        String initialTeamName = "Testers";
        Team team = new Team ();
        team.setTeamName(initialTeamName);
        teamDao.add(team);
        teamDao.update(team.getId(),"Test");
        Team updatedTeam = teamDao.findById(team.getId());
        assertFalse(updatedTeam.getTeamName().equals(initialTeamName));
    }

    @Test
    public void deleteByIdDeletesCorrectTeam() throws Exception {
        Team team = new Team ();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Team team = new Team ();
        Team otherTeam = new Team();
        team.setTeamName("1");
        otherTeam.setTeamName("2");
        teamDao.add(team);
        teamDao.add(otherTeam);
        int daoSize = teamDao.getAll().size();
        teamDao.clearAllTeams();
        assertTrue(daoSize > 0 && daoSize > teamDao.getAll().size());
    }

    @Test
    public void getAllTeamMembersByTeamReturnsTeamMembersCorrectly() throws Exception {
        Team team = new Team();
        team.setTeamName("Testers");
        team.setTeamDescription("Testin things");
        teamDao.add(team);
        int teamId = team.getId();

        TeamMember teamMember = new TeamMember();
        teamMember.setName("jeff");
        teamMember.setTeamId(teamId);
        teamMemberDao.add(teamMember);


        TeamMember teamMember1 = new TeamMember();
        teamMember1.setName("jill");
        teamMember1.setTeamId(teamId);
        teamMemberDao.add(teamMember1);

        TeamMember teamMember2 = new TeamMember();
        teamMember2.setName("jim");
        teamMember2.setTeamId(teamId);

        assertEquals(2, teamDao.getAllTeamMembersByTeam(teamId).size());
        assertTrue(teamDao.getAllTeamMembersByTeam(teamId).contains(teamMember));
        assertTrue(teamDao.getAllTeamMembersByTeam(teamId).contains(teamMember1));
        assertFalse(teamDao.getAllTeamMembersByTeam(teamId).contains(teamMember2));
    }

}