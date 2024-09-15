package org.haruhi.tracker.goal;

public class Task extends AbstractTask{


        public Task(String title, String description){
                super(title, description);

        }
        public Task (int id, String title, String description, Status status){
                super(id, title, description, status);
        }


        public void setStatus(Status status) {
                this.status = status;
        }
}
