package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class Author {
    private int authorId;
    private String authorTitle;
    private String authorFirstName;
    private String authorLastName;

    public Author(int id, String title, String firstName, String lastName, String role) {
        this.authorId = id;
        this.authorTitle = title;
        this.authorFirstName = firstName;
        this.authorLastName = lastName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorTitle() {
        return authorTitle;
    }

    public void setAuthorTitle(String authorTitle) {
        this.authorTitle = authorTitle;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}
