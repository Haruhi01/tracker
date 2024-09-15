package manager;

import goal.AbstractTask;
import java.util.HashMap;

public interface Manager {

    HashMap<Integer, AbstractTask> getAll();
    AbstractTask getBuyId(int id);

    void removeAll();
    void removeBuyId(int id);


    void add(String title, String description);

    void update(AbstractTask abstractTask);
}
