SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
  id int PRIMARY KEY auto_increment,
  teamName VARCHAR,
  teamDescription VARCHAR
);

CREATE TABLE IF NOT EXISTS newTeamMembers (
  id int PRIMARY KEY auto_increment,
  teamMemberName VARCHAR
);