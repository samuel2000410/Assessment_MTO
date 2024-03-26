import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Read CSV-file 
        List<String> data = readCSV("radar_data.csv");

        System.out.println("Simulation is starting"); 

        // Run simulation by looping over input
        Integer iteration = 0;
        while(data.size() != 0) {
            System.out.println("Timestep " + iteration); 

            // Radar observes new object
            String object = radar(data);
            System.out.println("Radar observes new object: " + object);

            // IFF checks if object is friend or foe
            boolean isFoe = iff(object);

            // Missile launcher activated if object is foe
            if (isFoe){
                System.out.println("Object is foe");
                boolean hit = missileLauncher();
                if (hit) {System.out.println("Foe object is hit");}
                else {System.out.println("Foe object is missed");}
                
            }
            else {System.out.println("Object is friendly, so no missile is launched");}

            // End of iteration
            iteration ++;
            System.out.println("");

            // Make simulation sleep for 1 second before goint to next time step
            try {
                Thread.sleep(1000); 
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }

    // Function to read CSV-file and return it as list of strings
    private static List<String> readCSV(String filePath) {
        List<String> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        
        return rows;
    }

    // Function to perceive incoming threat by picking a random row from CSV-file
    private static String radar(List<String> data){
        // Get random index within the bounds 
        Random random = new Random();
        int randomIndex = random.nextInt(data.size());

        // Return the element at the random index and remove it
        return data.remove(randomIndex);
    }

    // Function to identify if object is friend or foe
    private static boolean iff(String row){
        // Split input on semi colon 
        String[] substrings = row.split(";");
       
        // Declare counters and loop over substrings
        Integer evenCounter = 0;
        Integer unevenCounter = 0;
        for (String binary : substrings) {

            // Only look at last digit of binary to determine (un)even
            char lastDigit = binary.charAt(binary.length() - 1);
            if (lastDigit == '0') {
                evenCounter++;
            } else {
                unevenCounter++;
            }
        }
    
        // Return boolean that indicates if object is foe
        System.out.println("Even counter: " + evenCounter + ", uneven counter: " + unevenCounter);
        return unevenCounter > evenCounter;
    }

    // Function to launch missile 
    private static boolean missileLauncher(){
        System.out.println("Missile is launched");

        // Return boolean that indicates if object is hit
        double random = Math.random();
        return random <= 0.8;
    }
}