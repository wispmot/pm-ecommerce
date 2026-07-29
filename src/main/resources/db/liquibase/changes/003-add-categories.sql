CREATE TABLE IF NOT EXISTS categories(
    id SERIAL8 PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

ALTER TABLE products
    ADD COLUMN IF NOT EXISTS
        category_id INT8;

ALTER TABLE products
    ADD CONSTRAINT product_categories_fk
        FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE;
