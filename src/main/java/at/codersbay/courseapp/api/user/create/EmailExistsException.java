package at.codersbay.courseapp.api.user.create;

public class EmailExistsException extends Exception{
    public EmailExistsException(String message) { super(message); }

}
