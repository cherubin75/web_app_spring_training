package fr.lernejo.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoListController {
    private TodoRepository repository;

    public TodoListController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/todo")
    public ResponseEntity<Iterable<TodoEntity>> getTodos() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/api/todo", consumes = {"application/json"})
    public ResponseEntity<TodoEntity> addTodo(@RequestBody Todo todo) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.message = todo.getMessage();
        todoEntity.author = todo.getAuthor();
        return new ResponseEntity<TodoEntity>(repository.save(todoEntity), HttpStatus.CREATED);
    }
}
