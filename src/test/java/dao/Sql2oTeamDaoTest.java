package dao;

import models.Team;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
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
        Team updatedTask = teamDao.findById(team.getId());
        assertNotEquals(initialTeamName, updatedTask.getTeamName());
    }

    @Test
    public void deleteByIdDeletesCorrectTeam() throws Exception {
        Team team = new Team ();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }
}