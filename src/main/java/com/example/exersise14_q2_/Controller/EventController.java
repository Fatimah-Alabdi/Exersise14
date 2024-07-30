package com.example.exersise14_q2_.Controller;

import com.example.exersise14_q2_.Api.ApiRespons;
import com.example.exersise14_q2_.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();
    @GetMapping("/get")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(200).body(events);
    }
    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiRespons("add success"));
    }
    @PutMapping("/update/{index}")
   public ResponseEntity updateEvent(@PathVariable int index ,@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiRespons("update success"));

   }
   @DeleteMapping("/delete/{index}")
   public ResponseEntity deleteEvent(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiRespons("delete success"));
   }
    @GetMapping("/serchbyid/{id}")
    public ResponseEntity getEventsById(@PathVariable String id) {
        ArrayList<Event> searchEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getId().equals(id)) {
                searchEvents.add(event);
                return ResponseEntity.status(200).body(searchEvents);
            }

        }
        return ResponseEntity.status(400).body(new ApiRespons("search not found"));

    }
    @PutMapping("/change/{index}")
    public ResponseEntity changeCapacity(@PathVariable int index,@Valid@RequestBody Event capacity,Errors errors) {
        if (errors.hasErrors()) {

            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        Event event = events.get(index);
        event.setCapacity(capacity.getCapacity());

       return ResponseEntity.status(200).body(new ApiRespons("change success"));
    }
}
