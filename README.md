# Assessment_MTO
This project simulates a Patriot air defense system, consisting of three basic components: Radar, IFF (Identification Friend or Foe), and Missile Launcher. The simulation lasts for 20 seconds, during which each timestep (1 second) the radar module scans for incoming threats. The IFF checks if the object is friendly or hostile. If it is hostile, a misile is launched to take it down. 

To run the simulation, ensure that Java version 17 or higher is installed on your system. Then, follow these steps:
  - Clone or download this repository to your local machine.
  - Navigate to the project directory.
  - Place the radar_data.csv file in the same directory as the Main.java file.
  - Compile the Java source files:
      javac Main.java
  - Run the simulation:
      java Main 
