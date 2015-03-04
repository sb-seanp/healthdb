package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class Author {
    private int authorId;
    private String authorTitle;
    private String authorFirstName;
    private String authorLastName;
    private String ParticipatingRole;

    public Author(int id, String title, String firstName, String lastName, String role) {
        authorId = id;
        authorTitle = title;
        authorFirstName = firstName;
        authorLastName = lastName;
        ParticipatingRole = role;
    }
}
