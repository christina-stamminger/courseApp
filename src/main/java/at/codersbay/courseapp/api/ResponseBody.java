package at.codersbay.courseapp.api;

import java.util.ArrayList;

// Represents response body structure for API response
public class ResponseBody {

    // Initial capacity for message and error message arrays
    private static final int ARRAY_INITIAL_CAPACITY = 3;

    // ArrayList to store success messages
    private ArrayList<String> message = new ArrayList<>(ARRAY_INITIAL_CAPACITY);

    // ArrayList to store error messages
    private ArrayList<String> errorMessage = new ArrayList<>(ARRAY_INITIAL_CAPACITY);

    // Method to add a success message to the response
    public boolean addMessage(String message) {
        return this.message.add(message);
    }

    // Method to add error message to response
    public boolean addErrorMessage(String message) {
        return this.errorMessage.add(message);
    }

    // Getter and setter
    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    public ArrayList<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ArrayList<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}

