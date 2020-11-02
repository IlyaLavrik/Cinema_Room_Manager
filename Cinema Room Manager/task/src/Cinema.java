import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        myArray();
    }
    public static void myArray(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int m = scanner.nextInt();
        n++;
        m++;
        System.out.println("Cinema:");
        String[][] array = new String[n][m];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == 0 && j == 0) {
                    array[i][j] = " ";
                } else if (i == 0) {
                    array[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    array[i][j] = String.valueOf(i);
                } else {
                    array[i][j] = "S";
                }
            }
        }
        int s;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            s = scanner.nextInt();
            switch (s) {
                case 1:
                    showArray(array);
                    break;
                case 2:
                    myShow(array, n, m);
                    break;
                case 3:
                    totalTicet(array, m, n);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Enter a seat number in that row:");
                    break;
            }
        } while (s != 0);
    }
    public static void myShow(String[][] array, int n, int m) {
        Scanner scanner = new Scanner(System.in);
        int seat;
        int row;
        do {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
            if ((seat <= 0 || seat >= m) || (row <= 0 || row >= n)) {
                System.out.println("Wrong input!");
            } else if (array[row][seat].equals("B")) {
                while (array[row][seat].equals("B")) {
                    System.out.println("That ticket has already been purchased");
                    do {
                        System.out.println("Enter a row number:");
                        row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seat = scanner.nextInt();
                    } while ((seat <= 0 || seat >= m) || (row <= 0 || row >= n));
                }
                priceTicet(n, m, row);
                array[row][seat] = "B";
            } else {
                priceTicet(n, m, row);
                array[row][seat] = "B";
            }
        } while ((seat <= 0 || seat > m - 1) && (row <= 0 || row > n - 1));
    }
    public static void priceTicet(int n, int m, int row) {
        n--;
        m--;
        int area = n * m;
        int price;
        if (area <= 60) {
            price = 10;
        } else {
            if (row > n / 2) {
                price = 8;
            } else {
                price = 10;
            }
        }
        System.out.println("Ticket price: " + "$" + price);
    }
    public static void totalTicet(String[][] array,int n, int m){
        int front = 0;
        int back = 0;
        double incom;
        double total;
        double perc;
        m--;
        n--;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++){
                if (array[i][j].equals("B") && i <= n / 2){
                    front++;
                } else if(array[i][j].equals("B") && i > n / 2) {
                    back++;
                }
            }
        }
        int sum = n * m;
        if(sum <= 60){
            total = sum * 10;
            incom = (front + back) * 10;
            perc =  incom / total * 100;
        } else {
            if (n%2 == 0) {
                total = sum * 9;
            } else total = ((n / 2) * m * 10) + ((n - (n / 2)) * m * 8);
            incom = front * 10 + back * 8;
            perc =  incom / total * 100;
        }
        int count = front + back;
        String str = String.format("%.2f",perc);
        System.out.printf("Number of purchased tickets:%d%n",count);
        System.out.println("Percentage: " + str + "%");
        System.out.printf("Current income:$%.0f%nTotal income:$%.0f%n",incom, total);
    }
    public static void showArray(String[][] array){
        System.out.println();
        System.out.println("Cinema:");
        for (String[] elements : array) {
            for (String element : elements)
                System.out.print(element + " ");
            System.out.println();
        }
        System.out.println();
    }
}