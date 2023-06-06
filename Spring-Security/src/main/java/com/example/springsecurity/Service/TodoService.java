package com.example.springsecurity.Service;

import com.example.springsecurity.ApiException.ApiException;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public List<Todo> getTodos(Integer userId) {
        return todoRepository.findTodoByUserID(userId);
    }

    public void addTodo(Integer userId, Todo todo) {

        todo.setUserID(userId);
        todoRepository.save(todo);

    }

    public void updateTodo(Integer userId, Todo todo, Integer todoId){
       Todo todo1 = todoRepository.findTodoById(todoId);

       if (todo1==null){
           throw new ApiException("Not found");
       }
       if (todo1.getUserID() != userId){ //check if user have
           throw new ApiException("Error, Unauthorized");
       }
       todo1.setMessage(todo.getMessage());
       todoRepository.save(todo);
    }

    public void deleteTodo(Integer userId,Integer todoId) {

        Todo todo1 = todoRepository.findTodoById(todoId);

        if (todo1.getUserID() != userId){
            throw new ApiException("Error, Unauthorized");
        }
        todoRepository.delete(todo1);

    }

    }

