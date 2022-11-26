import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


class RoleTest {
    @Test
    public void testIsPrintRolesWorks() {
        assertEquals( "cook, waiter", Role.printRoles());
    }
}