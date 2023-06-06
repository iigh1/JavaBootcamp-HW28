package com.example.springsecurity.Service;

import com.example.springsecurity.ApiException.ApiException;
import com.example.springsecurity.Model.MyUser;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Repository.AuthRepository;
import com.example.springsecurity.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;
    public List<Todo> getTodos(Integer userId) {
        return todoRepository.findTodoByUserID(userId);
    }

    public void addTodo(Integer userId, Todo todo) {
        MyUser myUser = authRepository.findMyUserById(userId);
        todo.setMyUser(myUser);
        todoRepository.save(todo);

    }

    public void updateTodo(Integer userId, Todo todo, Integer todoId){
       Todo todo1 = todoRepository.findTodoById(todoId);
       MyUser myUser=authRepository.findMyUserById(userId);

       if (todo1==null || myUser ==null || userId != todo1.getId()){
           throw new ApiException("Invalid");
       }
       todo1.setMessage(todo.getMessage());
       todoRepository.save(todo1);
    }

    public void deleteTodo(Integer userId,Integer todoId) {

        Todo todo1 = todoRepository.findTodoById(todoId);
        MyUser myUser = authRepository.findMyUserById(userId);

        if (todo1==null || myUser ==null || userId != todo1.getId()){
            throw new ApiException("Invalid");
        }
        todoRepository.delete(todo1);

    }

    }

