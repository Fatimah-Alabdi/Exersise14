package com.example.exersise14_q1.Controller;

import com.example.exersise14_q1.Api.ApiRespons;
import com.example.exersise14_q1.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class TrackerController {

    ArrayList<Project> projects = new ArrayList<>();
    @GetMapping("/get")
    public ResponseEntity getProjects(){
        return ResponseEntity.status(200).body(projects);
    }
    @PostMapping("/add")
    public ResponseEntity addProject(@Valid@RequestBody Project project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiRespons(message));
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiRespons("projects added successfully"));

    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index ,@Valid@RequestBody Project project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiRespons(message));
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiRespons("projects updated successfully"));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiRespons("projects deleted successfully"));
    }
    @PutMapping("/changestatus/{index}")
    public ResponseEntity chngeStatus(@PathVariable int index,String status) {
        if(projects.get(index).getStatus().equalsIgnoreCase("Not Started")) {
            projects.get(index).setStatus("in Progress");
            return ResponseEntity.status(200).body(new ApiRespons("projects status changed successfully"));

        }
        else if(projects.get(index).getStatus().equalsIgnoreCase("in Progress")) {
            projects.get(index).setStatus("Completed");
            return ResponseEntity.status(200).body(new ApiRespons("projects status changed successfully"));
        }
return ResponseEntity.status(200).body(new ApiRespons("project is complete"));
    }
    @GetMapping("/search/{title}")
    public ResponseEntity searchProject(@PathVariable String title) {
        ArrayList<Project> searchprojects = new ArrayList<>();
        for(Project project : projects) {
            if(project.getTitle().equalsIgnoreCase(title)) {
                searchprojects.add(project);
                return ResponseEntity.status(200).body(searchprojects);
            }
        }
        return ResponseEntity.status(400).body(new ApiRespons("not found"));
    }
    @GetMapping("/getbycompany/{companyName}")
    public ResponseEntity getProjectsByCompanyName(@PathVariable String companyName ) {
        ArrayList<Project> searchbyCompany = new ArrayList<>();
        for(Project project : projects) {
            if(project.getCompanyName().equalsIgnoreCase(companyName)) {
                searchbyCompany.add(project);
                return ResponseEntity.status(200).body(searchbyCompany);
            }

        }
        return ResponseEntity.status(400).body(new ApiRespons("not found"));

    }
}
