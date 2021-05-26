import service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("misha","ivanov",(byte) 25);
        userService.saveUser("vasya","vasiliev",(byte) 24);
        userService.saveUser("dmitrii","antonov",(byte) 23);
        userService.saveUser("andrei","andreev",(byte) 22);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
