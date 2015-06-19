package com.webshop.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the ROLES database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */
@Entity
@NamedQueries({ @NamedQuery(name = "findAllRoles", query = "SELECT r FROM Role r"), @NamedQuery(name = "findRoleById", query = "SELECT r FROM Role r WHERE r.roleId = :roleid"),
      @NamedQuery(name = "findRoleByRoleName", query = "SELECT r FROM Role r WHERE UPPER(r.roleName) = UPPER(:rolename)"),
      @NamedQuery(name = "findRoleByRoleDescription", query = "SELECT r FROM Role r WHERE r.roleDesc like :roledesc"),
      @NamedQuery(name = "findRoleByCriteria", query = "SELECT r FROM Role r WHERE UPPER(r.roleName) = UPPER(:rolename) and r.roleDesc like :roledesc") })
@Table(name = "ROLES")
public class Role implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ROLE_ID", unique = true, nullable = false)
   private int roleId;

   @Column(name = "LAST_UPDATED_DATE", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private Timestamp lastUpdatedDate;

   @Column(name = "ROLE_DESC", nullable = false, length = 100)
   private String roleDesc;

   @Column(name = "ROLE_NAME", nullable = false, length = 50)
   private String roleName;

   public Role()
   {
   }

   public int getRoleId()
   {
      return this.roleId;
   }

   public void setRoleId(int roleId)
   {
      this.roleId = roleId;
   }

   public Timestamp getLastUpdatedDate()
   {
      return this.lastUpdatedDate;
   }

   public void setLastUpdatedDate(Timestamp lastUpdatedDate)
   {
      this.lastUpdatedDate = lastUpdatedDate;
   }

   public String getRoleDesc()
   {
      return this.roleDesc;
   }

   public void setRoleDesc(String roleDesc)
   {
      this.roleDesc = roleDesc;
   }

   public String getRoleName()
   {
      return this.roleName;
   }

   public void setRoleName(String roleName)
   {
      this.roleName = roleName;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Role [roleId=" + roleId + ", lastUpdatedDate="
      /* + lastUpdatedDate */+ ", roleDesc=" + roleDesc + ", roleName=" + roleName + "]";
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
      result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
      if (getClass() != obj.getClass())
      {
         return false;
      }
      Role other = (Role) obj;
      if (roleName == null)
      {
         if (other.roleName != null)
         {
            return false;
         }
      }
      else if (!roleName.equals(other.roleName))
      {
         return false;
      }
      return true;
   }

}