package ua.lv.halya.services;

import ua.lv.halya.entity.User;


public interface UserService {

    void add(User user);

    void edit(User user);

    void delete(int id);

    User findByLogin(String login);

    void sendLinkToRestorePassword(String email, String link);

    void restorePassword(String email, String oldPassword, String newPassword);
}
