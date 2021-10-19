package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private List<String> messages = new ArrayList<>();
    // curl -X GET http://localhost:8080/messages
    @GetMapping("messages")
    public List<String> getMessages() {
        return messages;
    }
    // -X GET http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public int getCount() {
        return messages.size();
    }
    // curl -X POST http://localhost:8080/messages
    @PostMapping("messages")
    public void addMessage(@RequestBody String text) {
        messages.add(text);
    }
    // curl -X POST http://localhost:8080/messages/2/create
    @PostMapping("messages/{index}/create")
    public void addMessage(@PathVariable("index") Integer i,
                           @RequestBody String message){
        messages.add(i,message);

    }
    // curl -X GET http://localhost:8080/messages/37
    @GetMapping("messages/{index}")
    public String getMessage(@PathVariable("index") Integer index) {
        return messages.get(index);
    }
    // curl -X GET http://localhost:8080/messages/search/gg
    @GetMapping("messages/search/{text}")
    public int getMessage(@PathVariable("text") String message){
        int a=0;
        for(int i=0;i<messages.size();i++){
            if (messages.get(i).contains(message)){
                a=i;
                break;
            }
        }
        return a;
    }

    // curl -X DELETE http://localhost:8080/messages/19
    @DeleteMapping("messages/{index}")
    public void deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
    }
    //curl -X DELETE http://localhost:8080/messages/search/info
    @DeleteMapping("messages/search/{text}")
    public void deleteText(@PathVariable("text") String message){
        for(int i=0;i<messages.size();i++){
            if (messages.get(i).contains(message)){
                messages.remove((int) i);
            }
        }
    }
    // curl -X PUT http://localhost:8080/messages/23
    @PutMapping("messages/{index}")
    public void updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
    }
}

