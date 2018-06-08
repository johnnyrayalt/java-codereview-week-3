package models;

import java.util.Objects;

public class TeamMember {
    private String teamMemberName;
    private int id;

    public String getName() { return teamMemberName; }
    public void setName(String teamMemberName) { this.teamMemberName = teamMemberName; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return id == that.id &&
                Objects.equals(teamMemberName, that.teamMemberName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamMemberName, id);
    }
}
