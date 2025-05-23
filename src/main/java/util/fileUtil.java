package util;

import entities.User;
import entities.Agent;
import entities.Ticket;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for handling file operations related to users, agents, and tickets.
 * Provides methods for reading and writing data to/from files.
 */
public class fileUtil {

    /**
     * Reads user data from a file and returns an array of User objects.
     *
     * @param filename the name of the file to read from
     * @return an array of User objects read from the file
     */
    public static User[] readUsers(String filename) {
        User[] result;
        try {
            Scanner file = new Scanner(new FileReader(filename));
            User[] temp = new User[100];
            int count = 0;

            while (file.hasNextLine()) {
                String[] parts = file.nextLine().split("%%");
                if (parts.length == 2) {
                    temp[count++] = new User(parts[0], parts[1]);
                }
            }

            result = new User[count];
            System.arraycopy(temp, 0, result, 0, count);
            file.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(fileUtil.class.getName()).log(Level.SEVERE, null, e);
            return new User[0];
        }

        return result;
    }

    /**
     * Reads agent data from a file and returns an array of Agent objects.
     *
     * @param filename the name of the file to read from
     * @return an array of Agent objects read from the file
     */
    public static Agent[] readAgents(String filename) {
        Agent[] result;
        try {
            Scanner file = new Scanner(new FileReader(filename));
            Agent[] temp = new Agent[100];
            int count = 0;

            while (file.hasNextLine()) {
                String[] parts = file.nextLine().split("%%");
                if (parts.length == 4) {
                    temp[count++] = new Agent(parts[0], parts[1], parts[2], parts[3]);
                }
            }

            result = new Agent[count];
            System.arraycopy(temp, 0, result, 0, count);
            file.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(fileUtil.class.getName()).log(Level.SEVERE, null, e);
            return new Agent[0];
        }

        return result;
    }

    /**
     * Reads ticket data from a file and returns an array of Ticket objects.
     *
     * @param filename the name of the file to read from
     * @return an array of Ticket objects read from the file
     */
    public static Ticket[] readTickets(String filename) {
        Ticket[] result;
        try {
            Scanner file = new Scanner(new FileReader(filename));
            Ticket[] temp = new Ticket[100];
            int count = 0;

            while (file.hasNextLine()) {
                String[] parts = file.nextLine().split("%%");
                if (parts.length == 7) {
                    LocalDateTime date = LocalDateTime.parse(parts[3]);
                    temp[count++] = new Ticket(parts[0], parts[1], Integer.parseInt(parts[2]), date, parts[4], parts[5], parts[6]);
                }
            }

            result = new Ticket[count];
            System.arraycopy(temp, 0, result, 0, count);
            file.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(fileUtil.class.getName()).log(Level.SEVERE, null, e);
            return new Ticket[0];
        }

        return result;
    }

    /**
     * Saves an array of User objects to a file.
     *
     * @param filename the name of the file to write to
     * @param users the array of User objects to save
     */
    public static void saveUsers(String filename, User[] users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (User u : users) {
                writer.println(u.getUsername() + "%%" + u.getPassword());
            }
        } catch (IOException e) {
            Logger.getLogger(fileUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Saves an array of Agent objects to a file.
     *
     * @param filename the name of the file to write to
     * @param agents the array of Agent objects to save
     */
    public static void saveAgents(String filename, Agent[] agents) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Agent a : agents) {
                writer.println(a.getUsername() + "%%" + a.getPassword() + "%%" + a.getAgentId() + "%%" + a.getAgentName());
            }
        } catch (IOException e) {
            Logger.getLogger(fileUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Saves an array of Ticket objects to a file.
     *
     * @param filename the name of the file to write to
     * @param tickets the array of Ticket objects to save
     */
    public static void saveTickets(String filename, Ticket[] tickets) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Ticket t : tickets) {
                writer.println(t.getTicketId() + "%%" + t.getIssueDescription() + "%%" + t.getPriorityLevel() + "%%" +
                        t.getCreationDate() + "%%" + t.getReportingUsername() + "%%" + t.getAllocatedAgentId() + "%%" + t.getStatus());
            }
        } catch (IOException e) {
            Logger.getLogger(fileUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

