package src.main.java.homework5.view;

import src.main.java.homework5.model.User;

public interface UserView<T extends User> {

    void sendOnConsole(String sortType);  // GET request
    void create(String fullName, Integer age, String phoneNumber);  // POST request
    void createRandom(Integer quantity);
    void remove(String fullName);  // DELETE request
    void edit(String fullName, Integer age, String phoneNumber);  // PUT request
}