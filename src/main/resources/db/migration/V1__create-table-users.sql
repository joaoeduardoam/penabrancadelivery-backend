
CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    verification_code VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL,
    img LONGBLOB
);


