package com.sitesquad.ministore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "shift")
public class Shift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Long shiftId;
    
    @Column(name = "name")
    private String type;
    
    @Column(name = "start_work_hour")
    private Double startWorkHour;
    
    @Column(name = "end_work_hour")
    private Double endWorkHour;
    
    @Column(name = "coefficient")
    private Double coefficient;

    @OneToMany(mappedBy = "shift")
    @JsonIgnore
    @ToString.Exclude
    private Collection<UserShift> userShifts;

}
