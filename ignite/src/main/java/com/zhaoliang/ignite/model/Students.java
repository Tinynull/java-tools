package com.zhaoliang.ignite.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.apache.ignite.cache.query.annotations.QueryTextField;

import java.io.Serializable;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/12.
 */
public class Students implements Serializable {

    /**
     * Person ID (indexed).
     */
    @QuerySqlField(index = true)
    private long id;
    /**
     * Organization ID (indexed).
     */
    @QuerySqlField(index = true)
    private long orgId;
    /**
     * First name (not-indexed).
     */
    @QuerySqlField
    private String firstName;
    /**
     * Last name (not indexed).
     */
    @QuerySqlField
    private String lastName;
    /**
     * Resume text (create LUCENE-based TEXT index for this field).
     */
    @QueryTextField
    private String resume;
    /**
     * Salary (indexed).
     */
    @QuerySqlField(index = true)
    private double salary;

    public Students(long id,
                    long orgId,
                    String firstName,
                    String lastName,
                    String resume,
                    double salary) {
        this.id = id;
        this.orgId = orgId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.resume = resume;
        this.salary = salary;
    }

    public Students(Builder builder) {
        this.id = builder.id;
        this.orgId = builder.orgId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.resume = builder.resume;
        this.salary = builder.salary;
    }

    public static class Builder {
        public long id;
        public long orgId;
        public String firstName;
        public String lastName;
        public String resume;
        public double salary;

        Builder(long id) {
            this.id = id;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setOrgId(long orgId){
            this.orgId = orgId;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setResume(String resume) {
            this.resume = resume;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public Students build() {
            return new Students(this);
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Students{" +
            "id=" + id +
            ", orgId=" + orgId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", resume='" + resume + '\'' +
            ", salary=" + salary +
            '}';
    }
}
