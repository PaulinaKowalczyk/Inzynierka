package com.sentimentanalysis.sentimentanalysis;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="USER")
public class User {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private @NonNull String username;
    private @NonNull String password;
    private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull String token;

    @OneToMany(mappedBy="user")
    private Set<Entry> entrySet;

}
