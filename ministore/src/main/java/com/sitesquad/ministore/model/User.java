package com.sitesquad.ministore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "user_img")
    private String userImg;

    @Column(name = "role_id" , insertable = false, updatable = false)
    private Long roleId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;



    @ManyToOne(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ToString.Exclude
    @JsonIgnore
    private Role roles;

    @OneToMany(mappedBy = "orderUser")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Order> orders;
    
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Payslip> payslips;


}
