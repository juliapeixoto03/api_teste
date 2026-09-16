package api_teste.ds.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api_teste.ds.models.Task;
import api_teste.ds.models.User;
import api_teste.ds.repositories.TaskRepository;
import jakarta.persistence.Id;

@Service 
public class TaskService {
    
    @Autowired 
    private TaskRepository taskRepository;


    @Autowired 
    private UserService userservice;
    
    public Task findById(Long Id){
        Optional<Task> task = this.taskRepository.findById(Id);

        return task.orElseThrow(()-> new RuntimeException(
            "Tarefa não encontrada! Id:" + Id + ", Tipo:" + Task.class.getName()
        ));

    }

    public List<Task> findByUserId(Long UserId){

        this.userService.findById(UserId);

        List<Task> tasks = this.taskRepository.findByUserId(UserId);

        return tasks;
    }
        @Transactional 
        public Task create(Task obj){

            User user = this.userService.findById(obj.getUser().getId());

            obj.setId(null);

            obj = this.taskRepository.save(obj);
            
            return obj;
        }

        @Transactional 
        public Task update(Task obj){

            
        }

    


}
