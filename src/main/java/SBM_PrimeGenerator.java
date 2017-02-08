/**
 * Created by sumit on 26/01/17.
 *
 * This class generates prime numbers from 1 - N and stores them in the array.  (Using sieve or er
 * You can then query the class to know whether a number ( < N) is a prime or not.
 */
public class SBM_PrimeGenerator {
    int N;
    int[] numbers;

    public SBM_PrimeGenerator(int n) {
        this.N = n;
        this.numbers = new int[N + 1];
        generatePrimeNumbers();
    }


    private void generatePrimeNumbers () {
        this.numbers[1] = 1;   //1 is not a prime number
        int i, p = 2;   //starting from smallest prime number.
        int currentNum = p;
        this.numbers[currentNum] = 0;   //Indicating prime number.
        while (p*p <= N) {
            currentNum += p;
            while (currentNum <= N) {
                this.numbers[currentNum] = 1;  //Indicating not prime.
                currentNum += p;
            }

            for (i = p + 1; i < N; i++) {
                if (this.numbers[i] == 0) {
                    p = i;
                    currentNum = p * p;
                    if (currentNum < N)
                        this.numbers[currentNum] = 1;
                    break;
                }
            }
            if (i == N) break;
        }
    }


    public boolean isPrimeNumber (int num) {
        return this.numbers[num] == 0;
    }

    public void printPrimeNumbers () {
        for (int i = 2; i < this.N; i++) {
            if (this.numbers[i] == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }


    //Test Driver method:
    public static void main(String[] args) {
        SBM_PrimeGenerator primeGenerator = new SBM_PrimeGenerator(20);
        primeGenerator.printPrimeNumbers();
    }
}
