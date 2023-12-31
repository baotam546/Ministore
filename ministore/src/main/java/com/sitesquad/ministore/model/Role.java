package com.sitesquad.ministore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    private Long roleId;
    
    @Column(name = "name")
    private String name;

    @Column(name = "base_salary")
    private Double baseSalary;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @OneToMany(mappedBy = "role")
    @JsonIgnore
    @ToString.Exclude
    private Collection<User> users;
}
