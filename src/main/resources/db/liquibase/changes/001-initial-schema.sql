CREATE TABLE IF NOT EXISTS products (
    id SERIAL8 PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CHECK (price >= 0.01),
    CHECK (stock_quantity >= 0)
)
