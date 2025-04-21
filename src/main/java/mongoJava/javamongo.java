package mongoJava;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Date;

public class javamongo {

    public static void main(String[] args) {
        // MongoDB Atlas connection string
        String connectionString = "mongodb+srv://bharat:kushwah@cluster0.79jr2.mongodb.net/dataStudy?retryWrites=true&w=majority";
        
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            System.out.println("Connected to MongoDB Atlas successfully!");
            
            // Get the database - make sure this matches what you want
            MongoDatabase db = mongoClient.getDatabase("CompanyDB");
            System.out.println("Using database: " + db.getName());
            
            // Get the collection
            MongoCollection<Document> employees = db.getCollection("employees");
            
            // Create employee document with unique emp_id
            Document employee = new Document()
                .append("emp_id", 799)  // Changed to 799 to avoid duplicate
                .append("first_name", "Bharat")
                .append("last_name", "Kushwah")
                .append("email", "bk@company.in")
                .append("hire_date", new Date(1561939200000L))
                .append("salary", 950000)
                .append("department", "IT");
            
            // Insert document
            employees.insertOne(employee);
            System.out.println("Document inserted successfully!");
            
            // Verify insertion
            System.out.println("\nAll documents in employees collection:");
            employees.find().forEach(doc -> System.out.println(doc.toJson()));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}