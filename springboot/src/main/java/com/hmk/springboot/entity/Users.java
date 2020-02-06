package com.hmk.springboot.entity;





import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



public class Users {
    private String id;
    @NotBlank(message = "姓名不能为空！")
    @Length(max = 15, min = 2,message = "姓名长度限制2~15字符")
    private String name;

    @NotNull(message = "密码不能为空！")
    @Length(max = 15, min = 6,message = "密码长度限制6~15字符")
    private String password;

    @Pattern(regexp = "^[1][3,4,5,7,8,9][0-9]{9}$",message = "请输入合法号码！")
    private String phone;

    @Email(message = "请输入正确的邮箱地址！")
    private String email;
    private String address;
    private String description;


    public Users() {
    }

    public Users(String id,  String name,  String password, String phone, String email, String address, String description) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password=" + password +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
