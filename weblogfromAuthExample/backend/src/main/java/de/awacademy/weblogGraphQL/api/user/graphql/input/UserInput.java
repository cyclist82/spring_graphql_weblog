package de.awacademy.weblogGraphQL.api.user.graphql.input;

import org.mindrot.jbcrypt.BCrypt;

public class UserInput {

    private String email;
    private String password;


    public UserInput(String email, String password) {
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(9));

    }

    public UserInput() {

    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(9));
    }



}
