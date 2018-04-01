package ua.lv.halya.services.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lv.halya.app.AdminData;
import ua.lv.halya.dao.UserDao;
import ua.lv.halya.entity.User;
import ua.lv.halya.services.UserService;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());


    private final String EMAIL_FROM = AdminData.ADMIN_EMAIL;
    private final String PASSWORD_FROM = AdminData.ADMIN_EMAIL_PASSWORD;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void add(User user) {

        String mes = "You are registered in My Finance: " +
                " http://localhost:8080/ " +
                "\n Thank you for being with us ;-)";
        try {
            sendEmail(mes, user.getEmail());
        } catch (Exception e) {
            logger.error("An exception occurred while sending email after register new User", e);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        logger.info("New User registered successfully. User's data: " + user.toString());
    }

    @Override
    public void edit(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User findByLogin(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername (String login) throws UsernameNotFoundException {
        User user = userDao.findByEmail(login);
        List<GrantedAuthority>authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    /**
     * Sends message with link to set new password, if user with this email exists.
     * The user's encrypted password from DB is added to the link.
     * @param email - user's email in DB
     * @param link - address to the page which will restore the password
     */
    @Override
    public void sendLinkToRestorePassword(String email, String link){
        try{
            String password = userDao.findByEmail(email).getPassword().replace('.', 'p');
            String mes = "Link to change password: " + link + password;
            sendEmail(mes, email);
        }catch (Exception e){
            logger.error("An exception occurred while sending email to restore password", e);
        }
    }

    /**
     * Checks coincidence user's password in DB and password from link. If they coincide - set new password to user with Encoder
     * ! Checking is done without a symbol '.'
     * @param email - user's email in DB
     * @param oldPassword - user's password in DB !!! WITHOUT '.', because with this symbol @PathVariable don't work correctly
     * @param newPassword - user's new password
     */
    @Override
    public void restorePassword(String email,String oldPassword, String newPassword){
        User user = userDao.findByEmail(email);
        if(oldPassword.equals(user.getPassword().replace('.', 'p'))){

            String newPas = bCryptPasswordEncoder.encode(newPassword);
            user.setPassword(newPas);

            userDao.save(user);
        }
    }

    private void sendEmail( String text, String emailTo) throws Exception{
        final Properties properties= new Properties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.user", EMAIL_FROM);
        properties.put("mail.smtps.auth","true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.password",PASSWORD_FROM);
        properties.put("mail.smtp.port", "587");

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        message.setSubject("Message from My Finance");
        message.setText(text);

        Transport tr = mailSession.getTransport();
        tr.connect(null, PASSWORD_FROM);
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }


}
