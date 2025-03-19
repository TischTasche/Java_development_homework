import java.util.Scanner;


public class Car_Fleet{

    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        String position = scanner.nextLine();
        String speed = scanner.nextLine();
        scanner.close();

        String[] positionArray = position.split(" ");
        String[] speedArray = speed.split(" ");
        int destination = Integer.parseInt(target);
        int[] positions = new int[positionArray.length];
        int[] speeds = new int[speedArray.length];

        for(int i = 0; i < positionArray.length; i++){
            positions[i] = Integer.parseInt(positionArray[i]);
            speeds[i] = Integer.parseInt(speedArray[i]);
        }

        double[][] carFleet = new double[positions.length][2];
        for(int i = 0; i < positions.length; i++){
            carFleet[i][0] = positions[i];
            carFleet[i][1] = (double)(destination - positions[i]) / speeds[i];
        }



        int fleets = fleetCount(sortPosition(positions, speeds, carFleet));
        System.out.println("Case 1: " + fleets + ".");
    }

    public static double[][] sortPosition(int[] position, int[] speed, double[][] carFleet){
        int positionLength = position.length;
        for(int i = 0; i < positionLength; i++){
            for(int j = i + 1; j < positionLength; j++){
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