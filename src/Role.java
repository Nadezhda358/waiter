public enum Role {
    COOK,
    WAITER;
    public static String printRoles() {
        Role[] roles = Role.values();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < roles.length; i++) {
            builder.append(roles[i]);
            if (i != roles.length - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
