package com.uvperez.splib;

import java.util.Date;

public class Employee {

    private Integer emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private Date hire_date;

    public Employee() {

    }

	/**
	 * @return the hire_date
	 */
	public Date getHire_date() {
		return hire_date;
	}

	/**
	 * @param hire_date the hire_date to set
	 */
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the birth_date
	 */
	public Date getBirth_date() {
		return birth_date;
	}

	/**
	 * @param birth_date the birth_date to set
	 */
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	/**
	 * @return the emp_no
	 */
	public Integer getEmp_no() {
		return emp_no;
	}

	/**
	 * @param emp_no the emp_no to set
	 */
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee[").append("emp_no: ").append(emp_no).append(", ");
        sb.append("birth_date: ").append(birth_date).append(", ");
        sb.append("first_name: ").append(first_name).append(", ");
        sb.append("last_name: ").append(last_name).append(", ");
        sb.append("gender: ").append(gender).append(", ");
        sb.append("hire_date: ").append(hire_date).append("]");
        return sb.toString();
    }


}