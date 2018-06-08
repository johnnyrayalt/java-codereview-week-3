import java.util.*;

import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

//        post("/team/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String teamName = request.queryParams("teamName");
//            String teamDescription = request.queryParams("teamDescription");
//            String teamMembers = request.queryParams("teamMembers");
//            Team newTeam = new Team();
//            newTeam.setTeamName(teamName);
//            newTeam.setTeamDescription(teamDescription);
//            newTeam.setTeamMembers(teamMembers);
//            model.put("team", newTeam);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Team> team = Team.getAll();
//            model.put("team", team);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/team/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToFind = Integer.parseInt(request.params("id"));
//            Team foundTeam = Team.findById(idOfPostToFind);
//            model.put("team", foundTeam);
//            return new ModelAndView(model, "team-details.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/team/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToEdit = Integer.parseInt(request.params("id"));
//            Team editTeam = Team.findById(idOfPostToEdit);
//            model.put("editTeam", editTeam);
//            return new ModelAndView(model, "team-update-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("team/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfPostToEdit = Integer.parseInt(request.params("id"));
//            Team updateTeam = Team.findById(idOfPostToEdit);
//            String newTeamName = request.queryParams("teamName");
//            String newTeamMembers = request.queryParams("teamMembers");
//            updateTeam.setTeamName(newTeamName);
//            updateTeam.addTeamMembers(newTeamMembers);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
    }
}
