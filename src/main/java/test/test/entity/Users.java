package test.test.entity;



import java.sql.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @NotBlank(message = "Vui lòng nhập họ và tên")
    @Size(min = 10, max = 255, message = "Họ và tên phải từ 10 đến 255 ký tự")
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String fullName;

    @NotBlank(message = "Vui lòng nhập ngày sinh")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @NotBlank(message = "Chưa nhập số điện thoại")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không đúng định dạng")
    @Column(name = "phone", length = 10)
    private String phone;
    
    @NotBlank(message = "Chưa nhập email")
    @Email(message = "Email không đúng định dạng")
    @Column(name = "email", columnDefinition = "NVARCHAR(255)", unique = true)
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 8, max = 50, message = "Mật khẩu phải có ít nhất 8 ký tự và tối đa 50 ký tự")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$", message = "Mật khẩu phải có ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt")
    @Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String password;

    @NotBlank(message = "Vui lòng chọn giới tính")
    @Column(name ="gender",nullable = false)
    private Boolean gender;

    @NotBlank(message = "Vui lòng nhập địa chỉ")
    @Size(min = 10, max = 255, message = "Địa chỉ phải từ 10 đến 255 ký tự")
    @Column(columnDefinition = "TEXT")
    private String address;

    @NotBlank(message = "Vui lòng chọn vai trò")
    @Column(name = "role",nullable = false)
    private Boolean role;

    @Column(name = "is_active", nullable = false, columnDefinition = "BIT DEFAULT 1")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "positionID", nullable = true) // NULL nếu là Admin
    private Position position;

    // @OneToOne
    // @JoinColumn(name = "salaryID", nullable = true) // NULL nếu là Admin
    // private Salary salary;

    @Column(name = "reset_token", length = 255)
    private String resetToken;


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}


