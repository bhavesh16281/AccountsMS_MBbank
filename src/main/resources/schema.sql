CREATE TABLE IF NOT EXISTS customer (
    customer_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
    customer_id BIGINT NOT NULL,
    account_number BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_type VARCHAR(50) NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    updated_by VARCHAR(255) DEFAULT NULL,
    CONSTRAINT fk_accounts_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);