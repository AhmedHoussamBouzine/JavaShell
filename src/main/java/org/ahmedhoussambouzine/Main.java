package org.ahmedhoussambouzine;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner;
        String input, type;
        String[] commands = {"echo","type","exit"};
        while (true) {
            System.out.print("$ ");
            scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if(input.equals("exit 0") || input.equals("exit")) {
                break;
            }
            else if(input.startsWith("echo")) {
                System.out.println(input.substring(5));
            }else if(input.startsWith("type")) {
                type = input.substring(5);
                if(Arrays.asList(commands).contains(type)) {
                    System.out.println(type+ " is a shell builtin");
                }else{
                    System.out.println(type+ ": not found");
                }
            }else{
                System.out.println(input + ": command not found");
            }
        }
    }
}