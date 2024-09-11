package goal;

public class Task extends AbstractTask{


        Task(String title, String description){
                super(title, description);

        }



        public void setStatus(Status status) {
                this.status = status;
        }
}
