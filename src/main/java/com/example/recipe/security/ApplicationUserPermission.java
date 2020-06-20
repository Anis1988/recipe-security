package com.example.recipe.security;

public enum ApplicationUserPermission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    GUEST_READ("guest:read");


    private final String permission;


    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
