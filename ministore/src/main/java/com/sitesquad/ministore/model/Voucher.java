package com.sitesquad.ministore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "voucher")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private Long voucherId;

    @Column(name = "description")
    private String description;
    
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "min_item")
    private Integer minItem;

    @Column(name = "min_total")
    private Integer minTotal;

    @Column(name = "max_price")
    private Double maxPrice;

    @Column(name = "percent_discount")
    private Double percentDiscount;

    @Column(name = "expired_date")
    private Date expiredDate;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    
    @Column(name = "voucher_img")
    private String voucherImg;
    
    @Column(name = "is_apply_all")
    private Boolean isApplyAll;

    @OneToMany(mappedBy = "voucher")
//    @JsonIgnore
    @ToString.Exclude
    private Collection<ProductVoucher> productVouchers;
    
    @OneToMany(mappedBy = "voucher")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Order> orders;

}
