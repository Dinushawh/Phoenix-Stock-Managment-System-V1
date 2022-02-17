-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 17, 2022 at 04:52 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `no` int(11) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `time` varchar(10) NOT NULL,
  `handled_user` varchar(50) NOT NULL,
  `activity` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `check`
--

CREATE TABLE `check` (
  `hello` varchar(25) NOT NULL,
  `dd` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `check`
--

INSERT INTO `check` (`hello`, `dd`) VALUES
('INV00001', 'ITM00001'),
('INV00001', 'ITM00002'),
('INV00001', 'ITM00003'),
('INV00001', 'ITM00004'),
('INV00001', 'ITM00005'),
('INV00001', 'ITM00006'),
('INV00001', 'ITM00007'),
('INV00001', 'ITM00008'),
('INV00001', 'ITM00009'),
('INV00001', 'ITM00001'),
('INV00001', 'ITM00002'),
('INV00001', 'ITM00003'),
('INV00001', 'ITM00004'),
('INV00001', 'ITM00005'),
('INV00001', 'ITM00006'),
('INV00001', 'ITM00007'),
('INV00001', 'ITM00008'),
('INV00001', 'ITM00009');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` varchar(12) NOT NULL,
  `date_created` date NOT NULL DEFAULT current_timestamp(),
  `created_user` varchar(20) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `address_line1` varchar(250) NOT NULL,
  `address_line2` varchar(250) NOT NULL,
  `zip` varchar(50) NOT NULL,
  `city` varchar(500) NOT NULL,
  `country` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `reference` varchar(50) NOT NULL DEFAULT 'No reference yet',
  `status` varchar(50) NOT NULL DEFAULT 'Raw'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_history`
--

CREATE TABLE `customer_history` (
  `id` int(11) NOT NULL,
  `customer_id` varchar(50) NOT NULL,
  `date_created` date NOT NULL,
  `created_user` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  `zip` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Raw'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_update_info`
--

CREATE TABLE `customer_update_info` (
  `no` int(11) NOT NULL,
  `customer_id` varchar(50) NOT NULL,
  `updated_user` varchar(50) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `time` varchar(50) NOT NULL,
  `reference` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_details`
--

CREATE TABLE `invoice_details` (
  `id` int(11) NOT NULL,
  `handle_user` varchar(20) NOT NULL,
  `invoice_number` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `due_date` date NOT NULL,
  `customer_id` varchar(20) NOT NULL,
  `fullname` varchar(150) NOT NULL,
  `address` varchar(150) NOT NULL,
  `phone_number` text NOT NULL,
  `invoice_total` float NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `invoiced_time` varchar(15) NOT NULL,
  `sub_total` float NOT NULL,
  `total_discounted_ammount` float NOT NULL,
  `total_taxed_ammount` float NOT NULL,
  `invoice_status` varchar(10) NOT NULL,
  `outstand_balance` float NOT NULL DEFAULT 0,
  `outstand_status` varchar(20) NOT NULL DEFAULT 'paid',
  `reference` varchar(20) NOT NULL DEFAULT 'No Reference'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_details_history`
--

CREATE TABLE `invoice_details_history` (
  `id` int(11) NOT NULL,
  `handle_user` varchar(20) NOT NULL,
  `invoice_number` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `due_date` varchar(20) NOT NULL,
  `customer_id` varchar(20) NOT NULL,
  `fullname` varchar(150) NOT NULL,
  `address` varchar(150) NOT NULL,
  `phone_number` text NOT NULL,
  `invoice_total` float NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `invoiced_time` varchar(50) NOT NULL,
  `sub_total` float NOT NULL,
  `total_discounted_ammount` float NOT NULL,
  `total_taxed_ammount` float NOT NULL,
  `invoice_status` varchar(50) NOT NULL,
  `outstand_balance` float NOT NULL,
  `outstand_status` varchar(50) NOT NULL,
  `reference` varchar(20) NOT NULL DEFAULT 'updating'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_item`
--

CREATE TABLE `invoice_item` (
  `no` int(10) NOT NULL,
  `invoice_no` varchar(20) NOT NULL,
  `item_id` varchar(50) NOT NULL,
  `item_name` varchar(120) NOT NULL,
  `unit_price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `discount` float NOT NULL,
  `tax` float NOT NULL,
  `note` varchar(200) NOT NULL DEFAULT 'No Note',
  `net_amount` float NOT NULL,
  `disconted_ammount` float NOT NULL,
  `taxed_ammount` float NOT NULL,
  `finale_ammount` float NOT NULL,
  `row_id` int(10) NOT NULL,
  `status` varchar(20) DEFAULT 'raw',
  `reference` varchar(20) NOT NULL DEFAULT 'No reference yet'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_item_history`
--

CREATE TABLE `invoice_item_history` (
  `no` int(11) NOT NULL,
  `invoice_no` varchar(20) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `item_name` varchar(120) NOT NULL,
  `unit_price` float NOT NULL,
  `quantity` float NOT NULL,
  `discount` float NOT NULL,
  `tax` float NOT NULL,
  `note` varchar(500) NOT NULL,
  `net_amount` float NOT NULL,
  `disconted_ammount` float NOT NULL,
  `taxed_ammount` float NOT NULL,
  `finale_ammount` float NOT NULL,
  `row_id` varchar(10) NOT NULL,
  `status` varchar(12) DEFAULT 'updated',
  `updated_date` date NOT NULL DEFAULT current_timestamp(),
  `updated_time` time NOT NULL DEFAULT current_timestamp(),
  `reference` varchar(30) NOT NULL DEFAULT 'processing '
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_reverse_info`
--

CREATE TABLE `invoice_reverse_info` (
  `no` int(11) NOT NULL,
  `invoice_number` varchar(50) NOT NULL,
  `balance` float NOT NULL,
  `balance_status` varchar(50) NOT NULL,
  `reversed_user` varchar(50) NOT NULL,
  `reversed_date` date NOT NULL DEFAULT current_timestamp(),
  `reversed_time` varchar(50) NOT NULL,
  `reason` varchar(500) NOT NULL,
  `reversed_reference` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_update_info`
--

CREATE TABLE `invoice_update_info` (
  `no` int(11) NOT NULL,
  `invoice_no` varchar(11) NOT NULL,
  `updated_user` varchar(11) NOT NULL,
  `updated_date` varchar(20) NOT NULL DEFAULT current_timestamp(),
  `updated_time` varchar(20) NOT NULL,
  `update_reference` varchar(30) NOT NULL,
  `total` float NOT NULL,
  `outstand_balance` float NOT NULL,
  `outstand_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `iuh`
--

CREATE TABLE `iuh` (
  `invoice_no` varchar(20) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `item_name` varchar(20) NOT NULL,
  `unit_price` varchar(20) NOT NULL,
  `quantity` varchar(20) NOT NULL,
  `discount` varchar(20) NOT NULL,
  `tax` varchar(20) NOT NULL,
  `note` varchar(20) NOT NULL,
  `net_amount` varchar(20) NOT NULL,
  `disconted_ammount` varchar(20) NOT NULL,
  `taxed_ammount` varchar(20) NOT NULL,
  `finale_ammount` varchar(20) NOT NULL,
  `row_id` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `master`
--

CREATE TABLE `master` (
  `id` int(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `username` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `master`
--

INSERT INTO `master` (`id`, `password`, `username`) VALUES
(1, '123456789', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(10) NOT NULL,
  `item_listed_date` date NOT NULL DEFAULT current_timestamp(),
  `created_user` varchar(12) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT 'Electronic',
  `name` varchar(50) NOT NULL,
  `supplier_id` varchar(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `currency` varchar(4) DEFAULT 'LKR',
  `supply_price` float NOT NULL,
  `retail_price` float NOT NULL,
  `manufacturer_or_brand` varchar(50) NOT NULL,
  `weight` float NOT NULL,
  `size` varchar(50) NOT NULL,
  `manufacture_date` date NOT NULL DEFAULT current_timestamp(),
  `expiry date` date NOT NULL DEFAULT current_timestamp(),
  `description` varchar(1500) DEFAULT 'Details not added yet...',
  `total_quntity` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL DEFAULT 'No reference yet',
  `status` varchar(50) NOT NULL DEFAULT 'Raw'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products_history`
--

CREATE TABLE `products_history` (
  `id` int(11) NOT NULL,
  `item_listed_date` date NOT NULL,
  `created_user` varchar(15) NOT NULL,
  `product_id` varchar(15) NOT NULL,
  `type` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `supplier_id` varchar(15) NOT NULL,
  `quantity` int(11) NOT NULL,
  `currency` varchar(5) NOT NULL,
  `supply_price` float NOT NULL,
  `retail_price` float NOT NULL,
  `manufacturer_or_brand` varchar(50) NOT NULL,
  `weight` float NOT NULL,
  `size` varchar(50) NOT NULL,
  `manufacture_date` date NOT NULL,
  `expiry date` date NOT NULL,
  `description` varchar(500) NOT NULL,
  `total_quntity` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `status` varchar(11) NOT NULL DEFAULT 'updating'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products_update_info`
--

CREATE TABLE `products_update_info` (
  `no` int(11) NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `updated_user` varchar(20) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `time` varchar(20) NOT NULL,
  `reference` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` int(11) NOT NULL,
  `date_created` date NOT NULL DEFAULT current_timestamp(),
  `created_user` varchar(50) NOT NULL,
  `supplier_id` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `website` varchar(100) NOT NULL,
  `address_line_1` varchar(100) NOT NULL,
  `address_line_2` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `zip_code` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `reference` varchar(20) NOT NULL DEFAULT 'No reference yet'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `supplier_update_history`
--

CREATE TABLE `supplier_update_history` (
  `no` int(11) NOT NULL,
  `created_date` varchar(50) NOT NULL,
  `created_user` varchar(50) NOT NULL,
  `supplier_id` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `website` varchar(50) NOT NULL,
  `address_line_1` varchar(50) NOT NULL,
  `address_line_2` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `zip_code` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `supplier_update_info`
--

CREATE TABLE `supplier_update_info` (
  `no` int(11) NOT NULL,
  `supplier_id` varchar(20) NOT NULL,
  `updated_user` varchar(20) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `time` varchar(10) NOT NULL,
  `reference` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_id` varchar(11) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(300) NOT NULL,
  `user_role` varchar(10) NOT NULL DEFAULT 'user',
  `login_time` int(11) NOT NULL DEFAULT 1,
  `security_question` varchar(200) NOT NULL DEFAULT 'No security question yet',
  `security_answer` varchar(200) NOT NULL DEFAULT 'No security question answer yet'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user_id`, `fname`, `lname`, `email`, `username`, `password`, `user_role`, `login_time`, `security_question`, `security_answer`) VALUES
(4, 'ID00000001', 'Dinusha', 'Weerakoon', 'support@dinusha.com', 'Dinusha', '123456', 'master', 1, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `a` int(10) NOT NULL,
  `b` int(10) NOT NULL,
  `c` int(10) NOT NULL,
  `d` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_history`
--
ALTER TABLE `customer_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_update_info`
--
ALTER TABLE `customer_update_info`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `invoice_details`
--
ALTER TABLE `invoice_details`
  ADD PRIMARY KEY (`id`,`invoice_number`);

--
-- Indexes for table `invoice_details_history`
--
ALTER TABLE `invoice_details_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice_item`
--
ALTER TABLE `invoice_item`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `invoice_item_history`
--
ALTER TABLE `invoice_item_history`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `invoice_reverse_info`
--
ALTER TABLE `invoice_reverse_info`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `invoice_update_info`
--
ALTER TABLE `invoice_update_info`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `master`
--
ALTER TABLE `master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products_history`
--
ALTER TABLE `products_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products_update_info`
--
ALTER TABLE `products_update_info`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier_update_history`
--
ALTER TABLE `supplier_update_history`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `supplier_update_info`
--
ALTER TABLE `supplier_update_info`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customer_history`
--
ALTER TABLE `customer_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `customer_update_info`
--
ALTER TABLE `customer_update_info`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `invoice_details`
--
ALTER TABLE `invoice_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=186;

--
-- AUTO_INCREMENT for table `invoice_details_history`
--
ALTER TABLE `invoice_details_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `invoice_item`
--
ALTER TABLE `invoice_item`
  MODIFY `no` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=244;

--
-- AUTO_INCREMENT for table `invoice_item_history`
--
ALTER TABLE `invoice_item_history`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=875;

--
-- AUTO_INCREMENT for table `invoice_reverse_info`
--
ALTER TABLE `invoice_reverse_info`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `invoice_update_info`
--
ALTER TABLE `invoice_update_info`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT for table `master`
--
ALTER TABLE `master`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `products_history`
--
ALTER TABLE `products_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `products_update_info`
--
ALTER TABLE `products_update_info`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `supplier_update_history`
--
ALTER TABLE `supplier_update_history`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `supplier_update_info`
--
ALTER TABLE `supplier_update_info`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
