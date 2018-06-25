/*select * from employees;


select count(*) from employees;


select count(*) from employees where birth_date >= str_to_date('01/02/1965employees','%d/%m/%Y');
*/
delimiter //
CREATE PROCEDURE employees.list_employees_birthdate(IN p_fecha varchar(10)) 
BEGIN
	SELECT * FROM employees WHERE birth_date >= str_to_date(p_fecha,'%d/%m/%Y');
END//



call employees.list_employees_birthdate('01/02/1965');