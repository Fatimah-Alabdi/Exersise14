package com.example.exersise14_q1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "id can not be null")
    @Size(min = 3)
    private String id;
    @NotEmpty(message = "title can not be null")
    @Size(min = 9)
    private String title;
    @NotEmpty(message = "description can not be null")
    @Size(min = 16)
    private String description;
    @NotEmpty(message = "status can not be null")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "Status must be Not Started, In Progress, or Completed only")
    private String status;
    @NotEmpty(message = "companyName can not be null")
    @Size(min = 9)
    private String companyName;
}
