public class User{
    private String name;
    private int password;

    public User(String name,int password){
        this.name=name;
        this.password=password;
    }
    public String getName(){
        return this.name;
    }
    public int getPassword(){
        return this.password;
    }
}