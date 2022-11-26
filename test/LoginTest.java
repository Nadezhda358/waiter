import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    @Test
    public void testIsRoleValidWaiterLowerCase() {
        assertTrue(Login.isRoleValid("waiter"));
    }
    @Test
    public void testIsRoleValidWaiterUpperCase() {
        assertTrue(Login.isRoleValid("WAITER"));
    }
    @Test
    public void testIsRoleValidCookLowerCase() {
        assertTrue(Login.isRoleValid("cook"));
    }
    @Test
    public void testIsRoleValidCookUpperCase() {
        assertTrue(Login.isRoleValid("COOK"));
    }
    @Test
    public void testIsRoleValidWithWrongInput() {
        assertFalse(Login.isRoleValid("bartender"));
    }
    @Test
    public void testIsNameTakenWithFreeName() {
        assertFalse(Login.isNameTaken("George,12345,waiter", new Restaurant()));
    }
    @Test
    public void testIsNameTakenWithNameTakenByUserWithDifferentRole() {
        assertFalse(Login.isNameTaken("Ivan,456,waiter", new Restaurant()));
    }
    @Test
    public void testIsNameTakenWithNameTakenByUserWithSameRole() {
        assertTrue(Login.isNameTaken("Ivan,456,cook", new Restaurant()));
    }
    @Test
    public void testSighUpWithNewUser() {
        Restaurant restaurant = new Restaurant();
        restaurant.usersInfoFileName = "testUsersInfo.txt";
        Random rand = new Random();

        String userInfo = rand.nextInt(1000) + ",user123,waiter";
        int usersCountBeforeAdding = restaurant.users.size();
        Login.signUp(userInfo, restaurant);
        int usersCountAfterAdding = restaurant.users.size();
        assertEquals(usersCountBeforeAdding + 1, usersCountAfterAdding);
    }
    //@Test
    //public void testSighUpWithUserWithNameIsAlreadyUsed() {
    //    Restaurant restaurant = new Restaurant();
    //    String userInfo = "Nadezhda,user123,waiter";
    //    Login.signUp(userInfo, restaurant);
    //    String actualUserInfo = restaurant.users.get(restaurant.users.size()-1).getUsername() + "," + restaurant.users.get(restaurant.users.size()-1).getPassword() + "," + Role.WAITER;
    //    assertNotEquals("Nadezhda,user123,WAITER", actualUserInfo);
    //}
}