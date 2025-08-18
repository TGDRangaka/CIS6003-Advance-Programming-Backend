package com.pahanaedu.bookshopmanagement.business.user.dto;

public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private String password;

    public static class Builder {
        private int userId;
        private String name;
        private String email;
        private String password;

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(userId, name, email, password);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserDTO() {
    }

    public UserDTO(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
