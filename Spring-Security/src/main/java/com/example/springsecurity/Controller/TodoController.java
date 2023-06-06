package com.example.springsecurity.Controller;


import com.example.springsecurity.Model.MyUser;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {


    private  final TodoService todoService;


    //هذا الشخص يسحب الاي دي حق الشخص نفسه
    //جاني عن طريق تسجيل الدخول id


    @GetMapping("/get")
    public ResponseEntity getTodos(@AuthenticationPrincipal MyUser myUser){

        List<Todo> todoList=todoService.getTodos(myUser.getId());
        return ResponseEntity.status(200).body(todoList);
    }

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody Todo todo){
        todoService.addTodo(myUser.getId(),todo);
        return ResponseEntity.status(200).body("todo added");

    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser,@RequestBody Todo todo,@PathVariable Integer todoId ){

        todoService.updateTodo(myUser.getId(),todo,todoId);
        return ResponseEntity.status(200).body("todo updated");
    }

    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer todoId) {
        todoService.deleteTodo(myUser.getId(),todoId);
        return ResponseEntity.status(200).body("todo deleted");
    }




}
