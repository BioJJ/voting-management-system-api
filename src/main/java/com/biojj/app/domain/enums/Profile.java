package com.biojj.app.domain.enums;

import lombok.Getter;

@Getter
public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),
    USER(1, "ROLE_USER");

    private final Integer code;
    private final String description;

    Profile(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalAccessError("Perfil Invalido");
    }
}
