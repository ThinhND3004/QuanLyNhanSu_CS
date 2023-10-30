package cb.utils;

import java.util.Scanner;

public class Utils {
    private static Scanner sc;

    public static String getString(String welcome) {
        String result = "";
        sc = new Scanner(System.in);
        boolean check = true;
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.length() > 0) {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getNumber(String welcome, int min, int max) {
        int result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                result = sc.nextInt();
                if (result >= min || result <= max) {
                    check = false;
                }
            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static double getNumber(String welcome, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double result = 0.0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.println(welcome);
                result = scanner.nextDouble();
                if (result >= min || result <= max) {
                    check = false;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldValue) {
        String result = "";
        System.out.print(welcome);
        sc = new Scanner(System.in);
        result = sc.nextLine();
        if (result.length() == 0) {
            result = oldValue;
        }

        return result;
    }

    public static int updateNumber(String welcome, int min, int max, int oldValue) {
        int result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String str = sc.nextLine();
                if (str.length() == 0) {
                    result = oldValue;
                    check = false;
                } else {
                    result = Integer.parseInt(str);
                    if (result > min && result < max) {
                        check = false;
                    }
                }

            } catch (Exception e) {
            }
        } while (check);
        return result;
    }


    public static double updateNumber(String welcome, double min, double max, double oldValue) {
        double result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String str = sc.nextLine();
                if (str.length() == 0) {
                    result = oldValue;
                    check = false;
                } else {
                    result = Integer.parseInt(str);
                    if (result > min && result < max) {
                        check = false;
                    }
                }

            } catch (Exception e) {
            }
        } while (check);
        return result;
    }


    public static boolean getBoolean(String welcome) {
        boolean check = false;
        System.out.print(welcome);
        sc = new Scanner(System.in);
        String str = sc.nextLine();
        if ("y".equalsIgnoreCase(str)) {
            check = true;
        }
        return check;
    }

    public static long getNumber(String welcome, long min, long max) {
        Scanner scanner = new Scanner(System.in);
        long result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.println(welcome);
                result = scanner.nextLong();
                if (result >= min || result <= max) {
                    check = false;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (check);
        return result;
    }
}
