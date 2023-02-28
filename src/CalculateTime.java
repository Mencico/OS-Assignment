import java.util.Arrays;
import java.util.Random;

public class CalculateTime {



        public static void main(String[] args) {
            int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int[] arrCopy = Arrays.copyOf(array, array.length);

            // Serial calculation
            long startTimeSerial = System.currentTimeMillis();
            double sumSerial = multiplySum(arrCopy);
            long endTimeSerial = System.currentTimeMillis();

            System.out.println("Serial sum: " + sumSerial);

            // Compute time difference1
            long timeDifference = endTimeSerial - startTimeSerial;
            System.out.println("Time difference1: " + timeDifference + " ms");

            // Parallel calculation
            long startTimeParallel = System.currentTimeMillis();
            double sumParallel = multiplySumParallel(array);
            long endTimeParallel = System.currentTimeMillis();

            System.out.println("Parallel sum: " + sumParallel);

            // Compute time difference2
            long timeDifference2 = endTimeParallel - startTimeParallel;
            System.out.println("Time difference2: " + timeDifference + " ms");

        }

         static double multiplySum(int[] array) {

            double sum = 0.0;
            Random random = new Random();

            for (int i = 0; i < array.length; i++) {
                double randomValue = 0.1 + (0.9 - 0.1) * random.nextDouble();
                array[i] = (int) (array[i] * randomValue);
                sum += array[i];
            }

            return sum;
        }

         static double multiplySumParallel(int[] array) {
            double sum = Arrays.stream(array)
                    .parallel()
                    .mapToDouble(value -> {
                        Random random = new Random();
                        double randomValue = 0.1 + (0.9 - 0.1) * random.nextDouble();
                        return value * randomValue;
                    })
                    .sum();

            return sum;
        }
    }

