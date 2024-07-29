package com.example.exersise14_q2_.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "id can not be empty")
    @Size(min=3)
    private String id;
    @NotEmpty(message = "describtion can not be empty")
    @Size(min=16)
    private String describtion;
    @NotNull(message = "capacity can not be null")
    @Min(value=26,message = "it has to be a number within the range of capacity")

    private int capacity;

    // @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    // @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
}
