import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class TaskTest {

     @Rule
     public DatabaseRule database = new DatabaseRule();

     @Test
     public void all_emptyAtFirst() {
       assertEquals(Task.all().size(), 0);
     }

     @Test
     public void equals_returnsTrueIfDescriptionsAreTheSame() {
       Task firstTask = new Task("Mow the lawn", 1);
       Task secondTask = new Task("Mow the lawn", 1);
       assertTrue(firstTask.equals(secondTask));
     }

     @Test
     public void equals_returnsFalseIfDescriptionsAreTheSame() {
       Task firstTask = new Task("Mow the lawn", 2);
       Task secondTask = new Task("Mow the lawn", 1);
       assertTrue(!firstTask.equals(secondTask));
     }

     @Test
      public void save_returnsTrueIfDescriptionsAretheSame() {
       Task myTask = new Task("Mow the lawn", 1);
       myTask.save();
       assertTrue(Task.all().get(0).equals(myTask));
      }

      @Test
      public void save_assignsIdToObject() {
        Task myTask = new Task("Mow the lawn", 1);
        myTask.save();
        Task savedTask = Task.all().get(0);
        assertEquals(myTask.getId(), savedTask.getId());
      }

      @Test
      public void find_findsTaskInDatabase_true() {
        Task myTask = new Task("Mow the lawn", 1);
        myTask.save();
        Task savedTask = Task.find(myTask.getId());
        assertTrue(myTask.equals(savedTask));
      }

      @Test
      public void delete_deletesTaskFromDatabase_true() {
        Category myCategory = new Category("Banking");
        myCategory.save();
        Task myTask = new Task("Mow the lawn", 1);
        myTask.save();
        myTask.delete();
        assertEquals(Task.all().size(), 0);
      }

      @Test
      public void update_changesTaskNameInDatabase_true() {
        Category myCategory = new Category("Morping");
        myCategory.save();
        Task myTask = new Task("Mow the lawn", 1);
        myTask.save();
        String description = "Fart";
        myTask.update(description);
        assertTrue(Task.all().get(0).getDescription().equals(description));

      }
 }
