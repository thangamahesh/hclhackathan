package com.hclhackathon.dto;

import java.time.Instant;

// ...existing code...

public class CustomerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Instant createdAt;
    private Instant updatedAt;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Long id, String firstName, String lastName, String email, String phone, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "CustomerResponseDTO{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }

    /**
     * Map a Customer entity to this DTO.
     * Adjust the import below to match your project's Customer entity package.
     */
    public static CustomerResponseDTO fromEntity(Object customerEntity) {
        // Replace Object with your entity type, e.g. com.hclhackathon.model.Customer
        // and adapt getters below to match the entity's getter names.
        // Example replacement once import is available:
        // public static CustomerResponseDTO fromEntity(Customer customer) { ... }
        if (customerEntity == null) return null;

        // ...existing code...
        // The following uses reflection to avoid tying to a specific package in this snippet.
        CustomerResponseDTO dto = new CustomerResponseDTO();
        try {
            java.lang.reflect.Method m;
            m = customerEntity.getClass().getMethod("getId");
            dto.setId((Long) m.invoke(customerEntity));
        } catch (Exception ignored) {}
        try {
            java.lang.reflect.Method m = customerEntity.getClass().getMethod("getFirstName");
            dto.setFirstName((String) m.invoke(customerEntity));
        } catch (Exception ignored) {}
        try {
            java.lang.reflect.Method m = customerEntity.getClass().getMethod("getLastName");
            dto.setLastName((String) m.invoke(customerEntity));
        } catch (Exception ignored) {}
        try {
            java.lang.reflect.Method m = customerEntity.getClass().getMethod("getEmail");
            dto.setEmail((String) m.invoke(customerEntity));
        } catch (Exception ignored) {}
        try {
            java.lang.reflect.Method m = customerEntity.getClass().getMethod("getPhone");
            dto.setPhone((String) m.invoke(customerEntity));
        } catch (Exception ignored) {}
        try {
            java.lang.reflect.Method m = customerEntity.getClass().getMethod("getCreatedAt");
            dto.setCreatedAt((Instant) m.invoke(customerEntity));
        } catch (Exception ignored) {}
        try {
            java.lang.reflect.Method m = customerEntity.getClass().getMethod("getUpdatedAt");
            dto.setUpdatedAt((Instant) m.invoke(customerEntity));
        } catch (Exception ignored) {}

        return dto;
    }
}

