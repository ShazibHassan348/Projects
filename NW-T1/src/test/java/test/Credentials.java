package test;

public class Credentials {
    private String email;
    private String password;

    public Credentials(){};
    public Credentials(String email,String password){
        this.password = password;
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String toString(){
        return "{Username= "+email+'\''+
                ", Password= "+password+"}";
    }

}
