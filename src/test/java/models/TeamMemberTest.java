package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamMemberTest {

    @Test
    public void getName() {
        TeamMember teamMember = setUpNewTeamMember();
        assertEquals("Jeff", teamMember.getName());
    }

    //helper
    public TeamMember setUpNewTeamMember() {
        TeamMember teamMember = new TeamMember();
        teamMember.setName("Jeff");
        return teamMember;
    }
}