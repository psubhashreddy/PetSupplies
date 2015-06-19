package com.webshop.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the USERS database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */
@Entity
@NamedQueries({
      @NamedQuery(name = "findAllUsers", query = "SELECT U FROM User U"),
      @NamedQuery(name = "findUserByUserNameAndPassWord", query = "SELECT U FROM User U WHERE UPPER(U.userName) = UPPER(:username) and U.password = :password and U.role.roleId = :roleId"),
      @NamedQuery(name = "findUsersByUserId", query = "SELECT U FROM User U WHERE U.userId = :userid"),
      @NamedQuery(name = "findUsersByUserName", query = "SELECT U FROM User U WHERE UPPER(U.userName) = UPPER(:username)"),
      @NamedQuery(name = "findUsersByFirstName", query = "SELECT U FROM User U WHERE UPPER(U.firstName) = UPPER(:firstname)"),
      @NamedQuery(name = "findUsersByLastName", query = "SELECT U FROM User U WHERE UPPER(U.lastName) = UPPER(:lastname)"),
      @NamedQuery(name = "findUsersByPhone", query = "SELECT U FROM User U WHERE U.phone = :phone"),
      @NamedQuery(name = "findUsersByEmail", query = "SELECT U FROM User U WHERE UPPER(U.email) = UPPER(:email)"),
      @NamedQuery(name = "findUsersBySearchCriteria", query = "SELECT U FROM User U WHERE UPPER(U.userName) = UPPER(:username) and UPPER(U.firstName) = UPPER(:firstname) and U.phone = :phone and UPPER(U.email) = UPPER(:email)") })
@Table(name = "USERS")
public class User implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "USER_ID", unique = true, nullable = false)
   private int userId;

   @Column(name = "FIRST_NAME", nullable = false, length = 30)
   private String firstName;

   @Column(name = "LAST_NAME", nullable = true, length = 30)
   private String lastName;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ROLE_ID", nullable = false)
   private Role role;

   @Column(name = "USER_NAME", nullable = false, length = 25)
   private String userName;

   @Column(name = "PASSWORD", nullable = false, length = 25)
   private String password;

   @Column(name = "PHONE", nullable = false, length = 15)
   private String phone;

   @Column(name = "EMAIL", nullable = false, length = 30)
   private String email;

   @Column(name = "ADDRESS", nullable = true, length = 200)
   private String address;

   @Column(name = "CITY", nullable = true, length = 30)
   private String city;

   @Column(name = "STATE", nullable = true, length = 30)
   private String state;

   @Column(name = "COUNTRY", nullable = true, length = 30)
   private String country;

   @Column(name = "POSTAL_CODE", nullable = true, length = 10)
   private String postalCode;

   @Column(name = "LAST_UPDATED_DATE", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private Timestamp lastUpdatedDate;

   // bi-directional many-to-one association to Order
   @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
   private Set<Order> orders;

   public User()
   {
   }

   public int getUserId()
   {
      return this.userId;
   }

   public void setUserId(int userId)
   {
      this.userId = userId;
   }

   public String getAddress()
   {
      return this.address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getCity()
   {
      return this.city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getCountry()
   {
      return this.country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getEmail()
   {
      return this.email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getFirstName()
   {
      return this.firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return this.lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public Timestamp getLastUpdatedDate()
   {
      return this.lastUpdatedDate;
   }

   public void setLastUpdatedDate(Timestamp lastUpdatedDate)
   {
      this.lastUpdatedDate = lastUpdatedDate;
   }

   public String getPassword()
   {
      return this.password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getPhone()
   {
      return this.phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getPostalCode()
   {
      return this.postalCode;
   }

   public void setPostalCode(String postalCode)
   {
      this.postalCode = postalCode;
   }

   public Role getRole()
   {
      return this.role;
   }

   public void setRole(Role role)
   {
      this.role = role;
   }

   public String getState()
   {
      return this.state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getUserName()
   {
      return this.userName;
   }

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   public Set<Order> getOrders()
   {
      return this.orders;
   }

   public void setOrders(Set<Order> orders)
   {
      this.orders = orders;
   }

   public Order addOrder(Order order)
   {
      getOrders().add(order);
      order.setUser(this);

      return order;
   }

   public Order removeOrder(Order order)
   {
      getOrders().remove(order);
      order.setUser(null);

      return order;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "User [userId=" + userId + ", address=" + address + ", city=" + city + ", country=" + country + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
            + ", lastUpdatedDate=" + lastUpdatedDate + ", phone=" + phone + ", postalCode=" + postalCode + ", state=" + state + ", userName=" + userName + "]";
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((phone == null) ? 0 : phone.hashCode());
      return result;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (obj == null)
      {
         return false;
      }
      if (!(obj instanceof User))
      {
         return false;
      }
      User other = (User) obj;
      if (email == null)
      {
         if (other.email != null)
         {
            return false;
         }
      }
      else if (!email.equals(other.email))
      {
         return false;
      }
      if (phone == null)
      {
         if (other.phone != null)
         {
            return false;
         }
      }
      else if (!phone.equals(other.phone))
      {
         return false;
      }
      return true;
   }

}