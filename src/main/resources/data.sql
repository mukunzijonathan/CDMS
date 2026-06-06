-- ========================================
-- Production-Ready Data Seeding Script
-- Rwanda Administrative Location Hierarchy
-- ========================================
-- This script inserts a complete Rwanda location hierarchy
-- Structure: PROVINCE > DISTRICT > SECTOR > CELL > VILLAGE
-- Then adds sample customers, employees, cars, and sales

-- Clean up existing data (only on development)
DELETE FROM sale_car CASCADE;
DELETE FROM sale CASCADE;
DELETE FROM customer CASCADE;
DELETE FROM employee CASCADE;
DELETE FROM car CASCADE;
DELETE FROM location CASCADE;

-- ========================================
-- LEVEL 1: PROVINCES (UUID Generated)
-- ========================================
INSERT INTO location (id, name, type, parent_id) VALUES
  ('550e8400-e29b-41d4-a716-446655440000', 'Kigali City', 'PROVINCE', NULL),
  ('550e8400-e29b-41d4-a716-446655440001', 'Northern Province', 'PROVINCE', NULL),
  ('550e8400-e29b-41d4-a716-446655440002', 'Southern Province', 'PROVINCE', NULL),
  ('550e8400-e29b-41d4-a716-446655440003', 'Eastern Province', 'PROVINCE', NULL),
  ('550e8400-e29b-41d4-a716-446655440004', 'Western Province', 'PROVINCE', NULL);

-- ========================================
-- LEVEL 2: DISTRICTS (Kigali City)
-- ========================================
INSERT INTO location (id, name, type, parent_id) VALUES
  ('550e8400-e29b-41d4-a716-446655450000', 'Gasabo District', 'DISTRICT', '550e8400-e29b-41d4-a716-446655440000'),
  ('550e8400-e29b-41d4-a716-446655450001', 'Kicukiro District', 'DISTRICT', '550e8400-e29b-41d4-a716-446655440000'),
  ('550e8400-e29b-41d4-a716-446655450002', 'Nyarugenge District', 'DISTRICT', '550e8400-e29b-41d4-a716-446655440000');

-- ========================================
-- LEVEL 3: SECTORS (Gasabo District)
-- ========================================
INSERT INTO location (id, name, type, parent_id) VALUES
  ('550e8400-e29b-41d4-a716-446655460000', 'Gisozi Sector', 'SECTOR', '550e8400-e29b-41d4-a716-446655450000'),
  ('550e8400-e29b-41d4-a716-446655460001', 'Kimironko Sector', 'SECTOR', '550e8400-e29b-41d4-a716-446655450000'),
  ('550e8400-e29b-41d4-a716-446655460002', 'Ndera Sector', 'SECTOR', '550e8400-e29b-41d4-a716-446655450000');

-- ========================================
-- LEVEL 4: CELLS (Gisozi Sector)
-- ========================================
INSERT INTO location (id, name, type, parent_id) VALUES
  ('550e8400-e29b-41d4-a716-446655470000', 'Kimisagara Cell', 'CELL', '550e8400-e29b-41d4-a716-446655460000'),
  ('550e8400-e29b-41d4-a716-446655470001', 'Kiyovu Cell', 'CELL', '550e8400-e29b-41d4-a716-446655460000'),
  ('550e8400-e29b-41d4-a716-446655470002', 'Nyabugogo Cell', 'CELL', '550e8400-e29b-41d4-a716-446655460000');

-- ========================================
-- LEVEL 5: VILLAGES (Kimisagara Cell)
-- ========================================
INSERT INTO location (id, name, type, parent_id) VALUES
  ('550e8400-e29b-41d4-a716-446655480000', 'Kimisagara Village 1', 'VILLAGE', '550e8400-e29b-41d4-a716-446655470000'),
  ('550e8400-e29b-41d4-a716-446655480001', 'Kimisagara Village 2', 'VILLAGE', '550e8400-e29b-41d4-a716-446655470000'),
  ('550e8400-e29b-41d4-a716-446655480002', 'Kiyovu Village 1', 'VILLAGE', '550e8400-e29b-41d4-a716-446655470001');

-- ========================================
-- SAMPLE DATA: CUSTOMERS
-- ========================================
INSERT INTO customer (id, first_name, last_name, email, phone_number, location_id) VALUES
  ('650e8400-e29b-41d4-a716-446655440000', 'Jean', 'Rwamasirabo', 'jean.rwa@example.com', '+250788123456', '550e8400-e29b-41d4-a716-446655480000'),
  ('650e8400-e29b-41d4-a716-446655440001', 'Marie', 'Uwizeye', 'marie.uwizeye@example.com', '+250789654321', '550e8400-e29b-41d4-a716-446655480001'),
  ('650e8400-e29b-41d4-a716-446655440002', 'Pierre', 'Nshimiyimana', 'pierre.nsh@example.com', '+250790111222', '550e8400-e29b-41d4-a716-446655480002');

-- ========================================
-- SAMPLE DATA: EMPLOYEES
-- ========================================
INSERT INTO employee (first_name, last_name, email, phone_number, salary, location_id) VALUES
  ('Jane', 'Mukamana', 'jane.mukamana@cardealership.com', '+250781234567', 1500.00, '550e8400-e29b-41d4-a716-446655480000'),
  ('Joseph', 'Habimana', 'joseph.habimana@cardealership.com', '+250782345678', 1800.00, '550e8400-e29b-41d4-a716-446655480001');

-- ========================================
-- SAMPLE DATA: CARS
-- ========================================
INSERT INTO car (brand, model, year, price) VALUES
  ('Toyota', 'Corolla', 2023, 25000.00),
  ('Toyota', 'Camry', 2022, 30000.00),
  ('Honda', 'Civic', 2023, 26000.00),
  ('Honda', 'Accord', 2021, 32000.00),
  ('Hyundai', 'Elantra', 2023, 22000.00),
  ('Mazda', 'CX-5', 2022, 28000.00),
  ('Nissan', 'Altima', 2021, 27000.00),
  ('Kia', 'Cerato', 2023, 21000.00);

-- ========================================
-- SAMPLE DATA: SALES
-- ========================================
INSERT INTO sale (sale_date, final_price, payment_method, customer_id, employee_id) VALUES
  ('2024-06-07', 25000.00, 'CASH', '650e8400-e29b-41d4-a716-446655440000', 1),
  ('2024-06-07', 30000.00, 'CARD', '650e8400-e29b-41d4-a716-446655440001', 2),
  ('2024-06-06', 26000.00, 'CARD', '650e8400-e29b-41d4-a716-446655440002', 1);

-- ========================================
-- SAMPLE DATA: SALE_CAR (Many-to-Many Relationship)
-- ========================================
INSERT INTO sale_car (sale_id, car_id) VALUES
  (1, 1),  -- Sale 1: Toyota Corolla
  (2, 2),  -- Sale 2: Toyota Camry
  (2, 3),  -- Sale 2: Honda Civic (multiple cars in one sale)
  (3, 5);  -- Sale 3: Hyundai Elantra

-- ========================================
-- Verify Data Insertion
-- ========================================
-- Location hierarchy count
SELECT 'Location hierarchy loaded:' as message, COUNT(*) as total FROM location;

-- Customer count
SELECT 'Customers loaded:' as message, COUNT(*) as total FROM customer;

-- Employee count
SELECT 'Employees loaded:' as message, COUNT(*) as total FROM employee;

-- Car count
SELECT 'Cars loaded:' as message, COUNT(*) as total FROM car;

-- Sale count
SELECT 'Sales loaded:' as message, COUNT(*) as total FROM sale;
