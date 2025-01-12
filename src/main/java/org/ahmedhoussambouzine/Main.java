package org.ahmedhoussambouzine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("exit")) {
                break;
            }
            if(input.startsWith("echo")) {
                System.out.println(input.substring(5));
            }else{
                System.out.println(input + ": command not found");
            }
        }
    }
}