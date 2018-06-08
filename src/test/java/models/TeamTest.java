package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {
    @Test
    public void teamClassInstantiatesCorrectly_isTrue() throws Exception {
        Team testTeam = setUpNewTeam();
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void teamClassInstantiatesCorrectly_withTeamName() throws Exception {
        Team testTeam = setUpNewTeam();
        assertEquals(null, testTeam.getTeamName());
    }


    @Test
    public void teamClassInstantiatesCorrectly_withDescription() throws Exception {
        Team testTeam = setUpNewTeam();
        assertEquals(null, testTeam.getTeamDescription());
    }

    @Test
    public void setTeamName_userCanSetTeamName_isTest() throws Exception {
        Team testTeam = setUpNewTeam();
        testTeam.setTeamName("test");
        assertEquals("test", testTeam.getTeamName());
    }

    @Test
    public void setTeamDescription_userCanSetTeamDescription_isTest() throws Exception {
        Team testTeam = setUpNewTeam();
        testTeam.setTeamDescription("test");
        assertEquals("test", testTeam.getTeamDescription());
    }


    public Team setUpNewTeam() {
        return new Team();
    }




}