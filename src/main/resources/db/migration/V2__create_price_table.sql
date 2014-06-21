CREATE TABLE Price (
  id SERIAL PRIMARY KEY,
  timestamp VARCHAR(32) NOT NULL,
  price INT NOT NULL,
  modified_by VARCHAR(32),
  product_id INT NOT NULL,
  Foreign Key (product_id) REFERENCES Product(id)
)