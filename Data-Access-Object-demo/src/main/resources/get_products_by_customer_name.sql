SELECT o.product_name
FROM netology.orders  o
JOIN netology.customers  c ON o.customer_id = c.id
WHERE LOWER(c.name) = :name;