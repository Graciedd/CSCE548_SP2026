use food_delivery_app;

INSERT INTO users (name, email, address) VALUES
('Alice Smith','alice@email.com','123 Main St'),
('Bob Jones','bob@email.com','456 Oak Ave'),
('Carol Lee','carol@email.com','789 Pine Rd'),
('David Kim','david@email.com','321 Cedar Ln'),
('Emma Brown','emma@email.com','654 Maple St'),
('Frank White','frank@email.com','777 Elm St'),
('Grace Hall','grace@email.com','999 Birch Rd'),
('Henry Adams','henry@email.com','111 Spruce Dr'),
('Ivy Clark','ivy@email.com','222 Aspen Way'),
('Jack Miller','jack@email.com','333 Willow Ct');

INSERT INTO restaurants (name, address) VALUES
('Pizza Palace','12 Pepperoni Rd'),
('Burger Barn','45 Greene Blvd'),
('Sushi Spot','88 Ocean Ave'),
('Taco Town','77 Main St'),
('Pasta Place','99 Howard Ln');

INSERT INTO menu_items (restaurant_id, name, price) VALUES
(1,'Cheese Pizza',9.99),(1,'Pepperoni Pizza',11.99),
(2,'Classic Burger',8.99),(2,'Bacon Burger',10.49),
(3,'California Roll',7.99),(3,'Spicy Tuna Roll',8.99),
(4,'Chicken Taco',3.99),(4,'Beef Taco',4.49),
(5,'Spaghetti',10.99),(5,'Fettuccine Alfredo',12.99),
(1,'Veggie Pizza',10.49),(2,'Fries',2.99),
(3,'Miso Soup',2.49),(4,'Chips & Salsa',3.49),
(5,'Lasagna',13.99),
(1,'Garlic Bread',4.99),(2,'Milkshake',3.99),
(3,'Salmon Roll',9.49),(4,'Fish Taco',4.99),
(5,'Ravioli',11.49),
(1,'BBQ Pizza',12.99),(2,'Chicken Sandwich',7.99),
(3,'Tempura',6.99),(4,'Nachos',6.49),
(5,'Penne Arrabiata',10.49);

INSERT INTO orders (user_id, restaurant_id, status, total_amount) VALUES
(1,1,'Delivered',21.98),
(2,2,'Preparing',18.48),
(3,3,'Pending',15.98),
(4,4,'Delivered',12.48),
(5,5,'Cancelled',10.99),
(6,1,'Delivered',14.98),
(7,2,'Pending',8.99),
(8,3,'Delivered',19.47),
(9,4,'Preparing',9.98),
(10,5,'Delivered',23.98);

SELECT COUNT(*) FROM users;
SELECT COUNT(*) FROM restaurants;
SELECT COUNT(*) FROM menu_items;
SELECT COUNT(*) FROM orders;
