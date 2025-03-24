import java.util.Scanner;


public class Car_Fleet{

    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the case(s) with each case being separated by a new line: ");

        String input = new String();
        int emptyLineCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                emptyLineCount++;
                if (emptyLineCount == 2) {
                    break;
                }
                input += "\n";
            } else {
                emptyLineCount = 0;
                input += line + "\n";
            }
        }
        scanner.close();

        String[] cases = input.split("\n\n");
        for(int caseIndex = 0; caseIndex < cases.length; caseIndex++){
            String[] lines = cases[caseIndex].split("\n");
            int destination = Integer.parseInt(lines[0]);
            String[] positionArray = lines[1].split(" "); 
            String[] speedArray = lines[2].split(" ");

            int[] positions = new int[positionArray.length];
            int[] speeds = new int[speedArray.length];

            for(int i = 0; i < positionArray.length; i++){
                positions[i] = Integer.parseInt(positionArray[i]);
                speeds[i] = Integer.parseInt(speedArray[i]);
            }

            double[][] carFleet = calculateCarFleet(positions, speeds, destination);
            int fleets = fleetCount(sortPosition(positions, speeds, carFleet));
            System.out.println("Case "+ (caseIndex + 1) + ": " + fleets + ".");
        }
    }

    /*
     * This method calculates the car fleet by dividing distance minus destination by the speed of the car
     * @param position: an array of integers representing the position of the cars
     * @param speed: an array of integers representing the speed of the cars
     * @param destination: an integer representing the destination
     * @return a 2D array of doubles representing the car fleet
     */
    public static double[][] calculateCarFleet(int[] position, int[] speed, int destination){
        double[][] carFleet = new double[position.length][2];
        for(int i = 0; i < position.length; i++){
            carFleet[i][0] = position[i];
            carFleet[i][1] = (double)(destination - position[i]) / speed[i];
        }
        return carFleet;
    }


    /*
     * This method sorts the car fleet based on the position of the cars
     * @param position: an array of integers representing the position of the cars
     * @param speed: an array of integers representing the speed of the cars
     * @param carFleet: a 2D array of doubles representing the car fleet
     * @return a 2D array of doubles representing the sorted car fleet
     */
    public static double[][] sortPosition(int[] position, int[] speed, double[][] carFleet){
        int carFleetLength = carFleet[0].length;
        for(int i = 0; i < carFleetLength; i++){
            for(int j = i + 1; j < carFleetLength; j++){
                if(carFleet[i][0] > carFleet[j][0]){
                    double temp = carFleet[i][0];
                    carFleet[i][0] = carFleet[j][0];
                    carFleet[j][0] = (double) temp;
                    temp = carFleet[i][1];
                    carFleet[i][1] = carFleet[j][1];
                    carFleet[j][1] = temp;
                }
            }
        }
        return carFleet;
    }


    /*
     * This method counts the number of fleets
     * @param carFleet: a 2D array of doubles representing the car fleet
     * @return an integer representing the number of fleets
     */
    public static int fleetCount(double[][] carFleet){
        int fleets = 0;
        double time = 0;
        int carFleetLength = carFleet.length;
        for(int i = 0; i < carFleetLength; i++){
            if(carFleet[i][1] > time){
                time = carFleet[i][1];
                fleets++;
            }
        }
        return fleets;
    }
}