package com.amigoscode.basicspring.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.amigoscode.basicspring.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }
}
