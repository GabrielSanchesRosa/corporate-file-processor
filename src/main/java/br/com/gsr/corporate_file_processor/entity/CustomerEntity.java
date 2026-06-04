package br.com.gsr.corporate_file_processor.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "document", length = 18, nullable = false)
    private String document;

    @Column(name = "email", length = 255, nullable = true)
    private String email;
}
