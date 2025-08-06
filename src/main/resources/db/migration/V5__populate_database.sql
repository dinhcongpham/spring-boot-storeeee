INSERT INTO categories (id, name)
VALUES (1, 'Fruits'),
       (2, 'Dairy'),
       (3, 'Meat'),
       (4, 'Beverages'),
       (5, 'Snacks');
INSERT INTO products (name, price, description, category_id)
VALUES
-- Fruits
('Banana', 0.30, 'Fresh ripe bananas, sold per piece.', 1),
('Apple', 0.50, 'Crisp red apples, perfect for snacking.', 1),
('Mango', 1.20, 'Sweet tropical mangoes.', 1),
('Blueberries', 2.99, 'Fresh organic blueberries, 125g pack.', 1),

-- Dairy
('Milk', 1.50, 'Whole milk, 1-liter bottle.', 2),
('Cheddar Cheese', 3.80, 'Aged cheddar cheese block, 250g.', 2),
('Yogurt', 0.90, 'Strawberry flavored yogurt cup.', 2),
('Butter', 2.30, 'Salted butter stick, 200g.', 2),

-- Meat
('Chicken Breast', 5.50, 'Boneless skinless chicken breasts, 500g.', 3),
('Ground Beef', 6.80, 'Lean ground beef, 500g.', 3),
('Pork Chops', 5.25, 'Tender pork chops, 500g.', 3),
('Bacon', 4.99, 'Smoked pork bacon, 200g.', 3),

-- Beverages
('Orange Juice', 2.20, '100% pure orange juice, 1-liter carton.', 4),
('Coffee', 4.50, 'Ground Arabica coffee, 250g pack.', 4),
('Green Tea', 3.20, 'Organic green tea bags, box of 20.', 4),
('Coca-Cola', 1.10, 'Classic Coke can, 330ml.', 4),

-- Snacks
('Potato Chips', 1.70, 'Salted potato chips, 150g bag.', 5),
('Chocolate Bar', 1.20, 'Milk chocolate bar, 100g.', 5),
('Granola Bar', 0.95, 'Healthy oat granola bar.', 5),
('Popcorn', 1.50, 'Butter-flavored microwave popcorn, 3-pack.', 5);
