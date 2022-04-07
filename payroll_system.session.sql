-- @block
CREATE TABLE role (
    role_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_title TEXT NOT NULL,
    role_description VARCHAR(160)
);

-- @BLOCK
CREATE TABLE user (
    user_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_id INTEGER NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(160) NOT NULL,
    user_dob VARCHAR(160) NULL UNIQUE,
    user_address VARCHAR(160) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- @BLOCK
CREATE TABLE job (
    job_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    job_title TEXT NOT NULL,
    job_type VARCHAR(160) NOT NULL,
    job_description VARCHAR(160) NOT NULL
);

-- @BLOCK
CREATE TABLE permission (
    permission_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_id INTEGER NOT NULL,
    permission_title TEXT NULL,
    permission_module VARCHAR(160) NULL,
    permission_description VARCHAR(160) NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- @BLOCK
CREATE TABLE employee (
    employee_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_name TEXT NOT NULL,
    employee_mobile VARCHAR(160),
    employee_email VARCHAR(160) NOT NULL,
    employee_address VARCHAR(160) NOT NULL,
    employee_username VARCHAR(160) NOT NULL,
    employee_password VARCHAR(160) NOT NULL
);

-- @BLOCK
CREATE TABLE salary (
    salary_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_id INTEGER,
    salary_type VARCHAR(160) NOT NULL,
    salary_description VARCHAR(160) NOT NULL,
    salary_amount VARCHAR(160) NOT NULL,
    salary_total VARCHAR(160) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

--@BLOCK
CREATE TABLE payment (
    payment_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    payment_amount VARCHAR(160) NOT NULL,
    payment_description VARCHAR(160) NOT NULL,
    payment_date VARCHAR(160) NOT NULL,
    employee_id INTEGER,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);


--@BLOCK
CREATE TABLE payroll (
    payroll_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    payroll_title VARCHAR(160) NOT NULL,
    payroll_description VARCHAR(160) NOT NULL,
    payroll_type VARCHAR(160) NOT NULL,
    employee_id INTEGER,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);