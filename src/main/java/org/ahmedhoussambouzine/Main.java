package org.ahmedhoussambouzine;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String getPath(String command) {
        String[] paths = System.getenv("PATH").split(":");
        for (String dir : paths) {
            Path fullPath = Path.of(dir, command);
            if (fullPath.toFile().exists()) {
                return fullPath.toString();
            }
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner;
        String ss, type;
        List<String> commands = new ArrayList<>();
        commands.add("echo");
        commands.add("type");
        commands.add("exit");
        commands.add("pwd");
        while (true) {
            System.out.print("$ ");
            scanner = new Scanner(System.in);
            ss = scanner.nextLine();
            String[] input = ss.split(" ");
            String command = input[0];
            StringBuilder parameter = new StringBuilder();
            for(int i = 1; i < input.length; i++) {
                if(i<input.length-1) {
                    parameter.append(input[i]).append(" ");
                }else {
                    parameter.append(input[i]);
                }
            }
            switch (command) {
                case "exit":
                    if (parameter.toString().equals("0")) {
                        System.exit(0);
                    } else {
                        System.out.println(ss + ": command not found");
                    }
                    break;
                case "echo":
                    System.out.println(parameter);
                    break;
                case "pwd":
                    System.out.println(Paths.get("").toAbsolutePath());
                    break;
                case "type":
                    type = parameter.toString();
                    if (commands.contains(type)) {
                        System.out.println(type + " is a shell builtin");
                    } else {
                        String path = getPath(String.valueOf(parameter));
                        if (path != null) {
                            System.out.println(parameter + " is " + path);
                        } else {
                            System.out.println(parameter + ": not found");
                        }
                    }
                    break;
                default:
                    String path = getPath(command);
                    if (path == null) {
                        System.out.printf("%s: command not found%n", command);
                    } else {
                        try {
                            String[] fullCommand = new String[input.length];
                            fullCommand[0] = command;
                            System.arraycopy(input, 1, fullCommand, 1, input.length - 1);
                            // Execute the external program with arguments

                            Process process = Runtime.getRuntime().exec(fullCommand);
                            process.getInputStream().transferTo(System.out);
                            process.getErrorStream().transferTo(System.err);
                            process.waitFor();
                        } catch (Exception e) {
                            System.out.println("Error while executing command: " + e.getMessage());
                        }
                    }
                    break;
            }
        }
    }
}