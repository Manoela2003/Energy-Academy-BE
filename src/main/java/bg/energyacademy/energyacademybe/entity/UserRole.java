package bg.energyacademy.energyacademybe.entity;

public enum UserRole {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRoleValue() {
        return role;
    }

    public static UserRole fromString(String text) {
        for (UserRole b : UserRole.values()) {
            if (b.role.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}