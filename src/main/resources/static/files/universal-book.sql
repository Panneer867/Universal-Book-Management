/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : universal-book

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 16/12/2022 17:58:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES (1, 'Axis Bank Ltd');
INSERT INTO `bank` VALUES (2, 'Bandhan Bank Ltd');
INSERT INTO `bank` VALUES (3, 'Bank of Baroda');
INSERT INTO `bank` VALUES (4, 'Bank of India');
INSERT INTO `bank` VALUES (5, 'Bank of Maharashtra');
INSERT INTO `bank` VALUES (6, 'Canara Bank');
INSERT INTO `bank` VALUES (7, 'Central Bank of India');
INSERT INTO `bank` VALUES (8, 'BANK Union Bank Ltd');
INSERT INTO `bank` VALUES (9, 'CSB Bank Ltd');
INSERT INTO `bank` VALUES (10, 'DCB Bank Ltd');
INSERT INTO `bank` VALUES (11, 'Dhanlaxmi Bank Ltd');
INSERT INTO `bank` VALUES (12, 'Federal Bank Ltd');
INSERT INTO `bank` VALUES (13, 'HDFC Bank Ltd');
INSERT INTO `bank` VALUES (14, 'ICICI Bank Ltd');
INSERT INTO `bank` VALUES (15, 'IDBI Bank Ltd');
INSERT INTO `bank` VALUES (16, 'IDFC First Bank Ltd');
INSERT INTO `bank` VALUES (17, 'Indian Bank');
INSERT INTO `bank` VALUES (18, 'Indian Overseas Bank');
INSERT INTO `bank` VALUES (19, 'Induslnd Bank Ltd');
INSERT INTO `bank` VALUES (20, 'Jammu & Kashmir Bank Ltd');
INSERT INTO `bank` VALUES (21, 'Karnataka Bank Ltd');
INSERT INTO `bank` VALUES (22, 'Karur Vysya Bank Ltd');
INSERT INTO `bank` VALUES (23, 'Kotak Mahindra Bank Ltd');
INSERT INTO `bank` VALUES (24, 'Nainital Bank Ltd');
INSERT INTO `bank` VALUES (25, 'Punjab & Sind Bank');
INSERT INTO `bank` VALUES (26, 'Punjab National Bank');
INSERT INTO `bank` VALUES (27, 'RBL Bank Ltd');
INSERT INTO `bank` VALUES (28, 'South Indian Bank Ltd');
INSERT INTO `bank` VALUES (29, 'State Bank of India');
INSERT INTO `bank` VALUES (30, 'Tamilnad Mercantile Bank Ltd');
INSERT INTO `bank` VALUES (31, 'UCO Bank');
INSERT INTO `bank` VALUES (32, 'Union Bank of India');
INSERT INTO `bank` VALUES (33, 'YES Bank Ltd');

-- ----------------------------
-- Table structure for branch
-- ----------------------------
DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch`  (
  `branch_id` bigint NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `designation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `admin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `phone_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pin_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `FK14f9k065wqeubl6tl0gdumcp5`(`company_id` ASC) USING BTREE,
  INDEX `FK152ji0vu2yhujdqi73atstbcy`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK14f9k065wqeubl6tl0gdumcp5` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK152ji0vu2yhujdqi73atstbcy` FOREIGN KEY (`user_id`) REFERENCES `user_management` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of branch
-- ----------------------------

-- ----------------------------
-- Table structure for branch_sequence
-- ----------------------------
DROP TABLE IF EXISTS `branch_sequence`;
CREATE TABLE `branch_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of branch_sequence
-- ----------------------------
INSERT INTO `branch_sequence` VALUES (101);

-- ----------------------------
-- Table structure for bundled_items
-- ----------------------------
DROP TABLE IF EXISTS `bundled_items`;
CREATE TABLE `bundled_items`  (
  `bundle_item_id` bigint NOT NULL AUTO_INCREMENT,
  `bundle_id` bigint NULL DEFAULT NULL,
  `item_id` bigint NULL DEFAULT NULL,
  `item_mrp` double NOT NULL,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` bigint NULL DEFAULT NULL,
  `sl_no` bigint NULL DEFAULT NULL,
  `total_cost` double NOT NULL,
  PRIMARY KEY (`bundle_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bundled_items
-- ----------------------------
INSERT INTO `bundled_items` VALUES (1, 1, 103, 950, 'ComSec', 1, 1, 950);
INSERT INTO `bundled_items` VALUES (2, 1, 115, 1000, 'The History of the Future', 1, 2, 1000);
INSERT INTO `bundled_items` VALUES (3, 1, 116, 1200, 'The Hype Machine', 1, 3, 1200);
INSERT INTO `bundled_items` VALUES (4, 1, 104, 700, 'How We Got to Now', 1, 4, 700);
INSERT INTO `bundled_items` VALUES (5, 1, 110, 300, 'Rage Inside the Machine', 1, 5, 300);
INSERT INTO `bundled_items` VALUES (6, 1, 113, 800, 'The Box', 1, 6, 800);
INSERT INTO `bundled_items` VALUES (7, 1, 117, 800, 'The Science of Storytelling', 1, 7, 800);
INSERT INTO `bundled_items` VALUES (8, 1, 102, 600, 'Big Tech\'s Battle to Erase the Trump Movement and Steal the Election', 1, 8, 600);
INSERT INTO `bundled_items` VALUES (9, 1, 114, 400, 'The Exponential Age', 1, 9, 400);
INSERT INTO `bundled_items` VALUES (10, 2, 105, 300, 'Java New Edition', 1, 1, 300);
INSERT INTO `bundled_items` VALUES (11, 2, 107, 600, 'Online Film Production in China Using Blockchain and Smart Contracts', 1, 2, 600);
INSERT INTO `bundled_items` VALUES (12, 2, 108, 900, 'Paradigm Shift in Technologies and Innovation Systems', 1, 3, 900);
INSERT INTO `bundled_items` VALUES (13, 2, 110, 300, 'Rage Inside the Machine', 1, 4, 300);
INSERT INTO `bundled_items` VALUES (14, 2, 104, 700, 'How We Got to Now', 1, 5, 700);
INSERT INTO `bundled_items` VALUES (15, 2, 106, 500, 'Life 3.0', 1, 6, 500);
INSERT INTO `bundled_items` VALUES (16, 2, 111, 1500, 'Stand out of our Light', 1, 7, 1500);
INSERT INTO `bundled_items` VALUES (17, 3, 113, 800, 'The Box', 5, 1, 4000);
INSERT INTO `bundled_items` VALUES (18, 3, 110, 300, 'Rage Inside the Machine', 12, 2, 3600);

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6p2u50v8fg2y0js6djc6xanit`(`state_id` ASC) USING BTREE,
  CONSTRAINT `FK6p2u50v8fg2y0js6djc6xanit` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 340 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'AGARTALA', 28);
INSERT INTO `city` VALUES (2, 'AGRA', 31);
INSERT INTO `city` VALUES (3, 'AHMEDABAD', 8);
INSERT INTO `city` VALUES (4, 'AIZWAL', 19);
INSERT INTO `city` VALUES (5, 'AJMER', 25);
INSERT INTO `city` VALUES (6, 'ALIBAUG', 16);
INSERT INTO `city` VALUES (7, 'ALLAHABAD', 31);
INSERT INTO `city` VALUES (8, 'ALLEPPEY', 14);
INSERT INTO `city` VALUES (9, 'ALMORA', 33);
INSERT INTO `city` VALUES (10, 'ALSISAR', 25);
INSERT INTO `city` VALUES (11, 'ALWAR', 25);
INSERT INTO `city` VALUES (12, 'AMBALA', 9);
INSERT INTO `city` VALUES (13, 'AMLA', 15);
INSERT INTO `city` VALUES (14, 'AMRITSAR', 24);
INSERT INTO `city` VALUES (15, 'ANAND', 8);
INSERT INTO `city` VALUES (16, 'ANKLESHWAR', 8);
INSERT INTO `city` VALUES (17, 'ASHTAMUDI', 14);
INSERT INTO `city` VALUES (18, 'AULI', 10);
INSERT INTO `city` VALUES (19, 'AURANGABAD', 16);
INSERT INTO `city` VALUES (20, 'BADDI', 10);
INSERT INTO `city` VALUES (21, 'BADRINATH', 33);
INSERT INTO `city` VALUES (22, 'BALASINOR', 8);
INSERT INTO `city` VALUES (23, 'BALRAMPUR', 31);
INSERT INTO `city` VALUES (24, 'BAMBORA', 25);
INSERT INTO `city` VALUES (25, 'BANDHAVGARH', 15);
INSERT INTO `city` VALUES (26, 'BANDIPUR', 13);
INSERT INTO `city` VALUES (27, 'BANGALORE', 13);
INSERT INTO `city` VALUES (28, 'BARBIL', 23);
INSERT INTO `city` VALUES (29, 'BAREILLY', 31);
INSERT INTO `city` VALUES (30, 'BEHROR', 25);
INSERT INTO `city` VALUES (31, 'BELGAUM', 13);
INSERT INTO `city` VALUES (32, 'BERHAMPUR', 23);
INSERT INTO `city` VALUES (33, 'BETALGHAT', 33);
INSERT INTO `city` VALUES (34, 'BHANDARDARA', 16);
INSERT INTO `city` VALUES (35, 'BHARATPUR', 25);
INSERT INTO `city` VALUES (36, 'BHARUCH', 8);
INSERT INTO `city` VALUES (37, 'BHAVANGADH', 8);
INSERT INTO `city` VALUES (38, 'BHAVNAGAR', 8);
INSERT INTO `city` VALUES (39, 'BHILAI', 5);
INSERT INTO `city` VALUES (40, 'BHIMTAL', 33);
INSERT INTO `city` VALUES (41, 'BHOPAL', 15);
INSERT INTO `city` VALUES (42, 'BHUBANESHWAR', 23);
INSERT INTO `city` VALUES (43, 'BHUJ', 8);
INSERT INTO `city` VALUES (44, 'BIKANER', 25);
INSERT INTO `city` VALUES (45, 'BINSAR', 33);
INSERT INTO `city` VALUES (46, 'BODHGAYA', 4);
INSERT INTO `city` VALUES (47, 'BUNDI', 25);
INSERT INTO `city` VALUES (48, 'CALICUT', 14);
INSERT INTO `city` VALUES (49, 'CANANNORE', 14);
INSERT INTO `city` VALUES (50, 'CHAIL', 10);
INSERT INTO `city` VALUES (51, 'CHAMBA', 33);
INSERT INTO `city` VALUES (52, 'CHANDIGARH', 24);
INSERT INTO `city` VALUES (53, 'CHENNAI', 27);
INSERT INTO `city` VALUES (54, 'CHIKMAGALUR', 13);
INSERT INTO `city` VALUES (55, 'CHIPLUN', 16);
INSERT INTO `city` VALUES (56, 'CHITRAKOOT', 15);
INSERT INTO `city` VALUES (57, 'CHITTORGARH', 25);
INSERT INTO `city` VALUES (58, 'COIMBATORE', 27);
INSERT INTO `city` VALUES (59, 'COONOOR', 27);
INSERT INTO `city` VALUES (60, 'COORG', 13);
INSERT INTO `city` VALUES (61, 'CORBETT NATIONAL PARK', 33);
INSERT INTO `city` VALUES (62, 'CUTTACK', 23);
INSERT INTO `city` VALUES (63, 'DABHOSA', 16);
INSERT INTO `city` VALUES (64, 'DALHOUSIE', 10);
INSERT INTO `city` VALUES (65, 'DAMAN', 29);
INSERT INTO `city` VALUES (66, 'DANDELI', 13);
INSERT INTO `city` VALUES (67, 'DAPOLI', 16);
INSERT INTO `city` VALUES (68, 'DARJEELING', 34);
INSERT INTO `city` VALUES (69, 'DAUSA', 25);
INSERT INTO `city` VALUES (70, 'DEHRADUN', 31);
INSERT INTO `city` VALUES (71, 'DHARAMSHALA', 10);
INSERT INTO `city` VALUES (72, 'DIBRUGARH', 3);
INSERT INTO `city` VALUES (73, 'DIGHA', 34);
INSERT INTO `city` VALUES (74, 'DIU', 29);
INSERT INTO `city` VALUES (75, 'DIVE AGAR', 16);
INSERT INTO `city` VALUES (76, 'DOOARS', 34);
INSERT INTO `city` VALUES (77, 'DURGAPUR', 34);
INSERT INTO `city` VALUES (78, 'DURSHET', 16);
INSERT INTO `city` VALUES (79, 'DWARKA', 8);
INSERT INTO `city` VALUES (80, 'FARIDABAD', 9);
INSERT INTO `city` VALUES (81, 'FIROZABAD', 31);
INSERT INTO `city` VALUES (82, 'GANAPATIPULE', 16);
INSERT INTO `city` VALUES (83, 'GANDHIDHAM', 8);
INSERT INTO `city` VALUES (84, 'GANDHINAGAR', 8);
INSERT INTO `city` VALUES (85, 'GANGOTRI', 33);
INSERT INTO `city` VALUES (86, 'GANGTOK', 26);
INSERT INTO `city` VALUES (87, 'GARHMUKTESHWAR', 31);
INSERT INTO `city` VALUES (88, 'GARHWAL', 33);
INSERT INTO `city` VALUES (89, 'GAYA', 4);
INSERT INTO `city` VALUES (90, 'GHAZIABAD', 22);
INSERT INTO `city` VALUES (91, 'GOA', 7);
INSERT INTO `city` VALUES (92, 'GOKHARNA', 14);
INSERT INTO `city` VALUES (93, 'GONDAL', 8);
INSERT INTO `city` VALUES (94, 'GORAKHPUR', 31);
INSERT INTO `city` VALUES (95, 'GREATER NOIDA', 20);
INSERT INTO `city` VALUES (96, 'GULMARG', 11);
INSERT INTO `city` VALUES (97, 'GURGAON', 9);
INSERT INTO `city` VALUES (98, 'GURUVAYOOR', 14);
INSERT INTO `city` VALUES (99, 'GUWAHATI', 3);
INSERT INTO `city` VALUES (100, 'GWALIOR', 15);
INSERT INTO `city` VALUES (101, 'HALEBID', 13);
INSERT INTO `city` VALUES (102, 'HAMPI', 13);
INSERT INTO `city` VALUES (103, 'HANSI', 9);
INSERT INTO `city` VALUES (104, 'HARIDWAR', 31);
INSERT INTO `city` VALUES (105, 'HASSAN', 13);
INSERT INTO `city` VALUES (106, 'HOSPET', 13);
INSERT INTO `city` VALUES (107, 'HOSUR', 27);
INSERT INTO `city` VALUES (108, 'HUBLI', 13);
INSERT INTO `city` VALUES (109, 'HYDERABAD', 2);
INSERT INTO `city` VALUES (110, 'IDUKKI', 14);
INSERT INTO `city` VALUES (111, 'IGATPURI', 16);
INSERT INTO `city` VALUES (112, 'IMPHAL', 17);
INSERT INTO `city` VALUES (113, 'INDORE', 15);
INSERT INTO `city` VALUES (114, 'JABALPUR', 15);
INSERT INTO `city` VALUES (115, 'JAIPUR', 25);
INSERT INTO `city` VALUES (116, 'JAISALMER', 25);
INSERT INTO `city` VALUES (117, 'JALANDHAR', 24);
INSERT INTO `city` VALUES (118, 'JALGAON', 16);
INSERT INTO `city` VALUES (119, 'JAMBUGODHA', 8);
INSERT INTO `city` VALUES (120, 'JAMMU', 11);
INSERT INTO `city` VALUES (121, 'JAMNAGAR', 8);
INSERT INTO `city` VALUES (122, 'JAMSHEDPUR', 12);
INSERT INTO `city` VALUES (123, 'JAWHAR', 16);
INSERT INTO `city` VALUES (124, 'JHANSI', 31);
INSERT INTO `city` VALUES (125, 'JODHPUR', 25);
INSERT INTO `city` VALUES (126, 'JOJAWAR', 25);
INSERT INTO `city` VALUES (127, 'JORHAT', 3);
INSERT INTO `city` VALUES (128, 'JUNAGADH', 8);
INSERT INTO `city` VALUES (129, 'KABINI', 13);
INSERT INTO `city` VALUES (130, 'KALIMPONG', 34);
INSERT INTO `city` VALUES (131, 'KANATAL', 33);
INSERT INTO `city` VALUES (132, 'KANCHIPURAM', 27);
INSERT INTO `city` VALUES (133, 'KANHA', 15);
INSERT INTO `city` VALUES (134, 'KANPUR', 31);
INSERT INTO `city` VALUES (135, 'KANYAKUMARI', 27);
INSERT INTO `city` VALUES (136, 'KARGIL', 11);
INSERT INTO `city` VALUES (137, 'KARJAT', 16);
INSERT INTO `city` VALUES (138, 'KARNAL', 9);
INSERT INTO `city` VALUES (139, 'KARUR', 27);
INSERT INTO `city` VALUES (140, 'KARWAR', 13);
INSERT INTO `city` VALUES (141, 'KASARGOD', 14);
INSERT INTO `city` VALUES (142, 'KASAULI', 10);
INSERT INTO `city` VALUES (143, 'KASHID', 16);
INSERT INTO `city` VALUES (144, 'KASHIPUR', 33);
INSERT INTO `city` VALUES (145, 'KATRA', 11);
INSERT INTO `city` VALUES (146, 'KAUSANI', 32);
INSERT INTO `city` VALUES (147, 'KAZA', 10);
INSERT INTO `city` VALUES (148, 'KAZIRANGA', 3);
INSERT INTO `city` VALUES (149, 'KEDARNATH', 33);
INSERT INTO `city` VALUES (150, 'KHAJJIAR', 10);
INSERT INTO `city` VALUES (151, 'KHAJURAHO', 15);
INSERT INTO `city` VALUES (152, 'KHANDALA', 16);
INSERT INTO `city` VALUES (153, 'KHIMSAR', 25);
INSERT INTO `city` VALUES (154, 'KOCHIN', 14);
INSERT INTO `city` VALUES (155, 'KODAIKANAL', 27);
INSERT INTO `city` VALUES (156, 'KOLHAPUR', 16);
INSERT INTO `city` VALUES (157, 'KOLKATA', 34);
INSERT INTO `city` VALUES (158, 'KOLLAM', 14);
INSERT INTO `city` VALUES (159, 'KOTA', 25);
INSERT INTO `city` VALUES (160, 'KOTAGIRI', 27);
INSERT INTO `city` VALUES (161, 'KOTTAYAM', 14);
INSERT INTO `city` VALUES (162, 'KOVALAM', 14);
INSERT INTO `city` VALUES (163, 'KUFRI', 10);
INSERT INTO `city` VALUES (164, 'KULLU', 10);
INSERT INTO `city` VALUES (165, 'KUMARAKOM', 14);
INSERT INTO `city` VALUES (166, 'KUMBAKONAM', 27);
INSERT INTO `city` VALUES (167, 'KUMBALGARH', 25);
INSERT INTO `city` VALUES (168, 'KUMILY', 14);
INSERT INTO `city` VALUES (169, 'KURSEONG', 34);
INSERT INTO `city` VALUES (170, 'KUSHINAGAR', 31);
INSERT INTO `city` VALUES (171, 'LACHUNG', 26);
INSERT INTO `city` VALUES (172, 'LAKSHADWEEP', 30);
INSERT INTO `city` VALUES (173, 'LEH', 11);
INSERT INTO `city` VALUES (174, 'LONAVALA', 16);
INSERT INTO `city` VALUES (175, 'LOTHAL', 8);
INSERT INTO `city` VALUES (176, 'LUCKNOW', 31);
INSERT INTO `city` VALUES (177, 'LUDHIANA', 24);
INSERT INTO `city` VALUES (178, 'LUMBINI', 21);
INSERT INTO `city` VALUES (179, 'MADURAI', 27);
INSERT INTO `city` VALUES (180, 'MAHABALESHWAR', 16);
INSERT INTO `city` VALUES (181, 'MAHABALIPURAM', 27);
INSERT INTO `city` VALUES (182, 'MALAPPURAM', 14);
INSERT INTO `city` VALUES (183, 'MALPE', 13);
INSERT INTO `city` VALUES (184, 'MALSHEJ GHAT', 16);
INSERT INTO `city` VALUES (185, 'MALVAN', 16);
INSERT INTO `city` VALUES (186, 'MANALI', 10);
INSERT INTO `city` VALUES (187, 'MANDAVI', 8);
INSERT INTO `city` VALUES (188, 'MANDAWA', 25);
INSERT INTO `city` VALUES (189, 'MANDORMONI', 34);
INSERT INTO `city` VALUES (190, 'MANESAR', 9);
INSERT INTO `city` VALUES (191, 'MANGALORE', 13);
INSERT INTO `city` VALUES (192, 'MANMAD', 16);
INSERT INTO `city` VALUES (193, 'MARARRI', 14);
INSERT INTO `city` VALUES (194, 'MARCHULA', 33);
INSERT INTO `city` VALUES (195, 'MATHERAN', 16);
INSERT INTO `city` VALUES (196, 'MATHURA', 31);
INSERT INTO `city` VALUES (197, 'MCLEODGANJ', 10);
INSERT INTO `city` VALUES (198, 'MOHALI', 24);
INSERT INTO `city` VALUES (199, 'MORADABAD', 31);
INSERT INTO `city` VALUES (200, 'MORBI', 8);
INSERT INTO `city` VALUES (201, 'MOUNT ABU', 25);
INSERT INTO `city` VALUES (202, 'MUKTESHWAR', 33);
INSERT INTO `city` VALUES (203, 'MUMBAI', 16);
INSERT INTO `city` VALUES (204, 'MUNDRA', 16);
INSERT INTO `city` VALUES (205, 'MUNNAR', 14);
INSERT INTO `city` VALUES (206, 'MURUD JANJIRA', 16);
INSERT INTO `city` VALUES (207, 'MUSSOORIE', 33);
INSERT INTO `city` VALUES (208, 'MYSORE', 13);
INSERT INTO `city` VALUES (209, 'NADUKANI', 14);
INSERT INTO `city` VALUES (210, 'NAGAPATTINAM', 27);
INSERT INTO `city` VALUES (211, 'NAGARHOLE', 13);
INSERT INTO `city` VALUES (212, 'NAGAUR FORT', 25);
INSERT INTO `city` VALUES (213, 'NAGOTHANE', 16);
INSERT INTO `city` VALUES (214, 'NAGPUR', 16);
INSERT INTO `city` VALUES (215, 'NAHAN', 10);
INSERT INTO `city` VALUES (216, 'NAINITAL', 31);
INSERT INTO `city` VALUES (217, 'NALDHERA', 10);
INSERT INTO `city` VALUES (218, 'NANDED', 16);
INSERT INTO `city` VALUES (219, 'NAPNE', 16);
INSERT INTO `city` VALUES (220, 'NASIK', 16);
INSERT INTO `city` VALUES (221, 'NAVI MUMBAI', 16);
INSERT INTO `city` VALUES (222, 'NERAL', 16);
INSERT INTO `city` VALUES (223, 'NEW DELHI', 6);
INSERT INTO `city` VALUES (224, 'NILGIRIS', 27);
INSERT INTO `city` VALUES (225, 'NOIDA', 31);
INSERT INTO `city` VALUES (226, 'OOTY', 27);
INSERT INTO `city` VALUES (227, 'ORCHHA', 15);
INSERT INTO `city` VALUES (228, 'OSIAN', 25);
INSERT INTO `city` VALUES (229, 'PACHMARHI', 15);
INSERT INTO `city` VALUES (230, 'PAHALGAM', 11);
INSERT INTO `city` VALUES (231, 'PALAMPUR', 10);
INSERT INTO `city` VALUES (232, 'PALANPUR', 8);
INSERT INTO `city` VALUES (233, 'PALI', 25);
INSERT INTO `city` VALUES (234, 'PALITANA', 8);
INSERT INTO `city` VALUES (235, 'PALLAKAD', 14);
INSERT INTO `city` VALUES (236, 'PANCHGANI', 16);
INSERT INTO `city` VALUES (237, 'PANCHKULA', 9);
INSERT INTO `city` VALUES (238, 'PANHALA', 16);
INSERT INTO `city` VALUES (239, 'PANNA', 15);
INSERT INTO `city` VALUES (240, 'PANTNAGAR', 33);
INSERT INTO `city` VALUES (241, 'PANVEL', 16);
INSERT INTO `city` VALUES (242, 'PARWANOO', 10);
INSERT INTO `city` VALUES (243, 'PATHANKOT', 11);
INSERT INTO `city` VALUES (244, 'PATIALA', 24);
INSERT INTO `city` VALUES (245, 'PATNA', 4);
INSERT INTO `city` VALUES (246, 'PATNITOP', 11);
INSERT INTO `city` VALUES (247, 'PELLING', 26);
INSERT INTO `city` VALUES (248, 'PENCH', 15);
INSERT INTO `city` VALUES (249, 'PHAGWARA', 24);
INSERT INTO `city` VALUES (250, 'PHALODI', 25);
INSERT INTO `city` VALUES (251, 'PINJORE', 9);
INSERT INTO `city` VALUES (252, 'PONDICHERRY', 27);
INSERT INTO `city` VALUES (253, 'POOVAR', 14);
INSERT INTO `city` VALUES (254, 'PORBANDAR', 8);
INSERT INTO `city` VALUES (255, 'PORT BLAIR', 1);
INSERT INTO `city` VALUES (256, 'POSHINA', 8);
INSERT INTO `city` VALUES (257, 'PRAGPUR', 10);
INSERT INTO `city` VALUES (258, 'PUNE', 16);
INSERT INTO `city` VALUES (259, 'PURI', 23);
INSERT INTO `city` VALUES (260, 'PUSKHAR', 25);
INSERT INTO `city` VALUES (261, 'PUTTAPARTHI', 2);
INSERT INTO `city` VALUES (262, 'RAI BAREILLY', 31);
INSERT INTO `city` VALUES (263, 'RAICHAK', 34);
INSERT INTO `city` VALUES (264, 'RAIPUR', 5);
INSERT INTO `city` VALUES (265, 'RAJAHMUNDRY', 2);
INSERT INTO `city` VALUES (266, 'RAJASTHAN', 25);
INSERT INTO `city` VALUES (267, 'RAJGIR', 4);
INSERT INTO `city` VALUES (268, 'RAJKOT', 8);
INSERT INTO `city` VALUES (269, 'RAJPIPLA', 8);
INSERT INTO `city` VALUES (270, 'RAJSAMAND', 25);
INSERT INTO `city` VALUES (271, 'RAM NAGAR', 31);
INSERT INTO `city` VALUES (272, 'RAMESHWARAM', 27);
INSERT INTO `city` VALUES (273, 'RAMGARH', 25);
INSERT INTO `city` VALUES (274, 'RANAKPUR', 25);
INSERT INTO `city` VALUES (275, 'RANCHI', 12);
INSERT INTO `city` VALUES (276, 'RANIKHET', 33);
INSERT INTO `city` VALUES (277, 'RANNY', 14);
INSERT INTO `city` VALUES (278, 'RANTHAMBORE', 25);
INSERT INTO `city` VALUES (279, 'RATNAGIRI', 16);
INSERT INTO `city` VALUES (280, 'RAVANGLA', 26);
INSERT INTO `city` VALUES (281, 'RISHIKESH', 31);
INSERT INTO `city` VALUES (282, 'RISHYAP', 34);
INSERT INTO `city` VALUES (283, 'ROHETGARH', 25);
INSERT INTO `city` VALUES (284, 'ROURKELA', 23);
INSERT INTO `city` VALUES (285, 'SAJAN', 16);
INSERT INTO `city` VALUES (286, 'SALEM', 27);
INSERT INTO `city` VALUES (287, 'SAPUTARA', 8);
INSERT INTO `city` VALUES (288, 'SASAN GIR', 8);
INSERT INTO `city` VALUES (289, 'SATTAL', 33);
INSERT INTO `city` VALUES (290, 'SAWAI MADHOPUR', 25);
INSERT INTO `city` VALUES (291, 'SAWANTWADI', 16);
INSERT INTO `city` VALUES (292, 'SECUNDERABAD', 2);
INSERT INTO `city` VALUES (293, 'SHARAVANBELGOLA', 13);
INSERT INTO `city` VALUES (294, 'SHILLONG', 18);
INSERT INTO `city` VALUES (295, 'SHIMLA', 10);
INSERT INTO `city` VALUES (296, 'SHIMLIPAL', 23);
INSERT INTO `city` VALUES (297, 'SHIRDI', 16);
INSERT INTO `city` VALUES (298, 'SHIVANASAMUDRA', 13);
INSERT INTO `city` VALUES (299, 'SIANA', 25);
INSERT INTO `city` VALUES (300, 'SILIGURI', 34);
INSERT INTO `city` VALUES (301, 'SILVASSA', 29);
INSERT INTO `city` VALUES (302, 'SIVAGANGA', 27);
INSERT INTO `city` VALUES (303, 'SOLAN', 10);
INSERT INTO `city` VALUES (304, 'SONAULI', 31);
INSERT INTO `city` VALUES (305, 'SRINAGAR', 11);
INSERT INTO `city` VALUES (306, 'SUNDERBAN', 34);
INSERT INTO `city` VALUES (307, 'SURAT', 8);
INSERT INTO `city` VALUES (308, 'TANJORE', 27);
INSERT INTO `city` VALUES (309, 'TAPOLA', 16);
INSERT INTO `city` VALUES (310, 'TARAPITH', 34);
INSERT INTO `city` VALUES (311, 'THANE', 16);
INSERT INTO `city` VALUES (312, 'THEKKADY', 14);
INSERT INTO `city` VALUES (313, 'THIRUVANANTHAPURAM', 14);
INSERT INTO `city` VALUES (314, 'THIRVANNAMALAI', 27);
INSERT INTO `city` VALUES (315, 'THRISSUR', 14);
INSERT INTO `city` VALUES (316, 'TIRUCHIRAPALLI', 27);
INSERT INTO `city` VALUES (317, 'TIRUPATI', 2);
INSERT INTO `city` VALUES (318, 'TIRUPUR', 27);
INSERT INTO `city` VALUES (319, 'UDAIPUR', 25);
INSERT INTO `city` VALUES (320, 'UDHAMPUR', 11);
INSERT INTO `city` VALUES (321, 'UDUPI', 13);
INSERT INTO `city` VALUES (322, 'UJJAIN', 15);
INSERT INTO `city` VALUES (323, 'UTTARKASHI', 33);
INSERT INTO `city` VALUES (324, 'VADODARA', 8);
INSERT INTO `city` VALUES (325, 'VAGAMON', 14);
INSERT INTO `city` VALUES (326, 'VAPI', 8);
INSERT INTO `city` VALUES (327, 'VARANASI', 31);
INSERT INTO `city` VALUES (328, 'VARKALA', 14);
INSERT INTO `city` VALUES (329, 'VELANKANNI', 27);
INSERT INTO `city` VALUES (330, 'VELLORE', 27);
INSERT INTO `city` VALUES (331, 'VERAVAL', 16);
INSERT INTO `city` VALUES (332, 'VIJAYAWADA', 2);
INSERT INTO `city` VALUES (333, 'VIKRAMGADH', 16);
INSERT INTO `city` VALUES (334, 'VISHAKAPATNAM', 2);
INSERT INTO `city` VALUES (335, 'WANKANER', 8);
INSERT INTO `city` VALUES (336, 'WAYANAD', 14);
INSERT INTO `city` VALUES (337, 'YAMUNOTRI', 33);
INSERT INTO `city` VALUES (338, 'YERCAUD', 27);
INSERT INTO `city` VALUES (339, 'YUKSOM', 26);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `company_id` bigint NOT NULL,
  `aadhaar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account_ype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `alternate_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_pincode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `beneficiary_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blood_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_pincode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `date_of_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_of_established` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `driving_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enable_app` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enable_web` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `entry_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `father_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gstin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ifsc_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `no_of_branch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_pincode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `place_of_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qualification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `service_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `registered_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`company_id`) USING BTREE,
  INDEX `FKcduw2smf6femc5f0jfufbw926`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKcduw2smf6femc5f0jfufbw926` FOREIGN KEY (`user_id`) REFERENCES `user_management` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES (1001, '875465498526', '9895654624612546546', 'Savings account', '9554845645', 'Amruthnagar', 'BANGALORE', 'Bandhan Bank Ltd', '565656', 'KARNATAKA', 'Panneer', '(B+)', 'Sole Proprietorships', 'Amruthnagar', 'BANGALORE', 'logo.png', 'Ingroinfo', 'C:/Company/Ingroinfo/', '560092', 'KARNATAKA', '2022-11-24 16:06:01.855000', '1999-08-24', '2022-11-24', 'KHH86786', 'panneer.ingroinfo@gmail.com', 'on', 'on', '2022-11-24', 'Kuppan', 'HJGJ67857', 'Panneer', 'Male', 'UIHU767HHJG7866', 'IBHNU67575', 'Selvam', '2022-11-24 16:06:01.855000', '9500931156', '2', 'Amruthnagar', 'BANGALORE', 'panneer867@gmail.com', '569987', 'KARNATAKA', 'VGYHVG76722', 'bangalore', 'Panneer.jpg', 'UG', 'Stationery', 'Panneer', '9544587888', 'http://ingroinfo.com', '2022-11-24', 1);

-- ----------------------------
-- Table structure for company_old
-- ----------------------------
DROP TABLE IF EXISTS `company_old`;
CREATE TABLE `company_old`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `aadhaar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account_ype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `alternate_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_pincode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `beneficiary_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blood_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_id` bigint NULL DEFAULT NULL,
  `company_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_pincode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `date_of_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_of_established` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `driving_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enable_app` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enable_web` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `entry_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `father_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gstin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ifsc_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `no_of_branch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_pincode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `place_of_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qualification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `record_created` datetime(6) NULL DEFAULT NULL,
  `service_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `registered_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_old
-- ----------------------------

-- ----------------------------
-- Table structure for company_sequence
-- ----------------------------
DROP TABLE IF EXISTS `company_sequence`;
CREATE TABLE `company_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_sequence
-- ----------------------------
INSERT INTO `company_sequence` VALUES (1001);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `aadhaar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `alternate_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blood_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_id` bigint NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `date_of_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `driving_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `father_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `marital_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pin_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `place_of_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qualification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for item_sequence
-- ----------------------------
DROP TABLE IF EXISTS `item_sequence`;
CREATE TABLE `item_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_sequence
-- ----------------------------
INSERT INTO `item_sequence` VALUES (201);

-- ----------------------------
-- Table structure for master_brand
-- ----------------------------
DROP TABLE IF EXISTS `master_brand`;
CREATE TABLE `master_brand`  (
  `brand_id` bigint NOT NULL AUTO_INCREMENT,
  `brand_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_brand
-- ----------------------------
INSERT INTO `master_brand` VALUES (1, 'National Read.png', 'National Read', '2022-12-07 16:06:58.141000', '2022-12-07 16:06:58.141000');

-- ----------------------------
-- Table structure for master_brand_publisher
-- ----------------------------
DROP TABLE IF EXISTS `master_brand_publisher`;
CREATE TABLE `master_brand_publisher`  (
  `publisher_id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `publisher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `supplier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`publisher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_brand_publisher
-- ----------------------------
INSERT INTO `master_brand_publisher` VALUES (1, 'National Read', 'Books', '2022-12-07 16:13:35.038000', '2022-12-07 16:13:35.038000', 'Ingroinfo', 'Latest Books', 'Panneer');

-- ----------------------------
-- Table structure for master_bundle
-- ----------------------------
DROP TABLE IF EXISTS `master_bundle`;
CREATE TABLE `master_bundle`  (
  `bundle_id` bigint NOT NULL AUTO_INCREMENT,
  `bundle_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `grand_total` double NOT NULL,
  `gst_amt` double NOT NULL,
  `gst_percent` double NOT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `medium` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school_id` bigint NULL DEFAULT NULL,
  `standard` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sub_total` double NOT NULL,
  PRIMARY KEY (`bundle_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_bundle
-- ----------------------------
INSERT INTO `master_bundle` VALUES (1, 'Bundle-1', '2022-12-16 17:27:26.564000', 7965, 1215, 18, '2022-12-16 17:27:26.564000', 'Kannada', 1, '10', 6750);
INSERT INTO `master_bundle` VALUES (2, 'Bundle-2', '2022-12-16 17:28:25.800000', 5664, 864, 18, '2022-12-16 17:28:25.800000', 'English', 2, 'PUC1', 4800);
INSERT INTO `master_bundle` VALUES (3, 'Custom Bundle', '2022-12-16 17:29:02.075000', 8968, 1368, 18, '2022-12-16 17:29:02.075000', 'Hindi', 3, '10', 7600);

-- ----------------------------
-- Table structure for master_category
-- ----------------------------
DROP TABLE IF EXISTS `master_category`;
CREATE TABLE `master_category`  (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_category
-- ----------------------------
INSERT INTO `master_category` VALUES (1, 'Books', 'Available', '2022-12-07 16:07:16.308000', '2022-12-07 16:07:16.308000');

-- ----------------------------
-- Table structure for master_hsn
-- ----------------------------
DROP TABLE IF EXISTS `master_hsn`;
CREATE TABLE `master_hsn`  (
  `hsn_id` bigint NOT NULL AUTO_INCREMENT,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `hsn_code` bigint NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `category_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`hsn_id`) USING BTREE,
  INDEX `FK4gvc29258dfkdd8e9et7ilefw`(`category_id` ASC) USING BTREE,
  CONSTRAINT `FK4gvc29258dfkdd8e9et7ilefw` FOREIGN KEY (`category_id`) REFERENCES `master_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_hsn
-- ----------------------------
INSERT INTO `master_hsn` VALUES (1, '2022-12-07 16:07:40.226000', 49011010, '2022-12-07 16:07:40.226000', 1);

-- ----------------------------
-- Table structure for master_item
-- ----------------------------
DROP TABLE IF EXISTS `master_item`;
CREATE TABLE `master_item`  (
  `item_id` bigint NOT NULL,
  `brand_id` bigint NULL DEFAULT NULL,
  `category_id` bigint NULL DEFAULT NULL,
  `cost_price` double NOT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hsn_code_id` bigint NULL DEFAULT NULL,
  `item_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `item_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `mrp_price` double NOT NULL,
  `publisher_id` bigint NULL DEFAULT NULL,
  `selling_price` double NOT NULL,
  `supplier_id` bigint NULL DEFAULT NULL,
  `unit_of_measure` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_item
-- ----------------------------
INSERT INTO `master_item` VALUES (101, 1, 1, 400, '2022-12-07 16:15:25.559000', 'Latest Books', 1, 'Appetite for Self-Destruction_46240.jpg', 'Appetite for Self-Destruction', 'Available', '2022-12-07 18:21:57.054000', 500, 1, 500, 1, 'pcs');
INSERT INTO `master_item` VALUES (102, 1, 1, 599, '2022-12-07 16:19:35.508000', 'Latest Books', 1, 'Big Tech\'s Battle to Erase the Trump Movement and Steal the Election_75084.jpg', 'Big Tech\'s Battle to Erase the Trump Movement and Steal the Election', 'Available', '2022-12-07 16:19:35.508000', 600, 1, 600, 1, 'pcs');
INSERT INTO `master_item` VALUES (103, 1, 1, 899, '2022-12-07 16:19:54.563000', 'Latest Books', 1, 'ComSec_93180.jpg', 'ComSec', 'Available', '2022-12-07 16:19:54.563000', 950, 1, 950, 1, 'pcs');
INSERT INTO `master_item` VALUES (104, 1, 1, 659, '2022-12-07 16:20:11.867000', 'Latest Books', 1, 'How We Got to Now_51831.jpg', 'How We Got to Now', 'Available', '2022-12-07 16:20:11.867000', 700, 1, 700, 1, 'pcs');
INSERT INTO `master_item` VALUES (105, 1, 1, 279, '2022-12-07 16:20:48.514000', 'Latest Books', 1, 'Java New Edition_7412.jpg', 'Java New Edition', 'Available', '2022-12-07 16:20:48.514000', 300, 1, 300, 1, 'pcs');
INSERT INTO `master_item` VALUES (106, 1, 1, 399, '2022-12-07 16:21:05.079000', 'Latest Books', 1, 'Life 3.0_25477.jpg', 'Life 3.0', 'Available', '2022-12-07 16:21:05.079000', 500, 1, 500, 1, 'pcs');
INSERT INTO `master_item` VALUES (107, 1, 1, 567, '2022-12-07 16:21:48.273000', 'Latest Books', 1, 'Online Film Production in China Using Blockchain and Smart Contracts_15350.jpg', 'Online Film Production in China Using Blockchain and Smart Contracts', 'Available', '2022-12-07 16:21:48.273000', 600, 1, 600, 1, 'pcs');
INSERT INTO `master_item` VALUES (108, 1, 1, 834, '2022-12-07 16:22:08.098000', 'Latest Books', 1, 'Paradigm Shift in Technologies and Innovation Systems_77491.jpg', 'Paradigm Shift in Technologies and Innovation Systems', 'Available', '2022-12-07 16:22:08.098000', 900, 1, 900, 1, 'pcs');
INSERT INTO `master_item` VALUES (109, 1, 1, 700, '2022-12-07 16:22:27.245000', 'Latest Books', 1, 'Race After Technology_28632.jpg', 'Race After Technology', 'Available', '2022-12-07 16:22:27.245000', 900, 1, 900, 1, 'pcs');
INSERT INTO `master_item` VALUES (110, 1, 1, 149, '2022-12-07 16:22:51.163000', 'Latest Books', 1, 'Rage Inside the Machine_43981.jpg', 'Rage Inside the Machine', 'Available', '2022-12-07 16:22:51.163000', 300, 1, 300, 1, 'pcs');
INSERT INTO `master_item` VALUES (111, 1, 1, 1080, '2022-12-07 16:23:08.099000', 'Latest Books', 1, 'Stand out of our Light_59753.jpg', 'Stand out of our Light', 'Available', '2022-12-07 16:23:08.099000', 1500, 1, 1500, 1, 'pcs');
INSERT INTO `master_item` VALUES (112, 1, 1, 406, '2022-12-07 16:23:26.634000', 'Latest Books', 1, 'Steve Jobs_3134.jpg', 'Steve Jobs', 'Available', '2022-12-07 16:23:26.634000', 600, 1, 600, 1, 'pcs');
INSERT INTO `master_item` VALUES (113, 1, 1, 386, '2022-12-07 16:23:42.205000', 'Latest Books', 1, 'The Box_29211.jpg', 'The Box', 'Available', '2022-12-07 16:23:42.205000', 800, 1, 800, 1, 'pcs');
INSERT INTO `master_item` VALUES (114, 1, 1, 275, '2022-12-07 16:23:58.363000', 'Latest Books', 1, 'The Exponential Age_53751.jpg', 'The Exponential Age', 'Available', '2022-12-07 16:23:58.363000', 400, 1, 400, 1, 'pcs');
INSERT INTO `master_item` VALUES (115, 1, 1, 935, '2022-12-07 16:24:12.860000', 'Latest Books', 1, 'The History of the Future_69203.jpg', 'The History of the Future', 'Available', '2022-12-07 16:24:12.860000', 1000, 1, 1000, 1, 'pcs');
INSERT INTO `master_item` VALUES (116, 1, 1, 926, '2022-12-07 16:27:19.537000', 'Latest Books', 1, 'The Hype Machine_6033.jpg', 'The Hype Machine', 'Available', '2022-12-07 16:27:19.537000', 1200, 1, 1200, 1, 'pcs');
INSERT INTO `master_item` VALUES (117, 1, 1, 748, '2022-12-07 16:27:35.405000', 'Latest Books', 1, 'The Science of Storytelling_58408.jpg', 'The Science of Storytelling', 'Available', '2022-12-07 16:27:35.405000', 800, 1, 800, 1, 'pcs');
INSERT INTO `master_item` VALUES (118, 1, 1, 357, '2022-12-07 16:27:51.702000', 'Latest Books', 1, 'The Technology Fallacy_18887.jpg', 'The Technology Fallacy', 'Available', '2022-12-07 16:27:51.702000', 478, 1, 478, 1, 'pcs');
INSERT INTO `master_item` VALUES (119, 1, 1, 875, '2022-12-07 16:28:08.491000', 'Latest Books', 1, 'Tools and Weapons_53187.jpg', 'Tools and Weapons', 'Available', '2022-12-07 16:28:08.491000', 899, 1, 899, 1, 'pcs');
INSERT INTO `master_item` VALUES (120, 1, 1, 934, '2022-12-07 16:35:14.233000', 'Latest Books', 1, 'Digital Forensics_81178.jpg', 'Digital Forensics', 'Available', '2022-12-07 16:35:14.233000', 999, 1, 999, 1, 'pcs');

-- ----------------------------
-- Table structure for master_role
-- ----------------------------
DROP TABLE IF EXISTS `master_role`;
CREATE TABLE `master_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_role
-- ----------------------------
INSERT INTO `master_role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `master_role` VALUES (2, 'ROLE_BRANCH');
INSERT INTO `master_role` VALUES (3, 'ROLE_USER');

-- ----------------------------
-- Table structure for master_school
-- ----------------------------
DROP TABLE IF EXISTS `master_school`;
CREATE TABLE `master_school`  (
  `school_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `board` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pin_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_school
-- ----------------------------
INSERT INTO `master_school` VALUES (1, 'Bangalore', 'STATE BOARD', 'LEH', '2022-12-07 16:29:28.131000', 'Michael@gmail.com', '2022-12-07 16:31:17.372000', '9234929323', '604530', 'New School', 'St. Michael\'s Higher Secondary Matriculation School', 'Higher Secondary', 'JAMMU & KASHMIR');
INSERT INTO `master_school` VALUES (2, 'Bangalore', 'STATE BOARD', 'AMBALA', '2022-12-07 16:30:14.427000', 'marry@gmail.com', '2022-12-07 16:30:14.427000', '8975464656', '675464', 'New School', 'St. Marry\'s Higher Secondary School', 'Higher Secondary', 'HARYANA');
INSERT INTO `master_school` VALUES (3, 'Bangalore', 'CBSE', 'JAMSHEDPUR', '2022-12-07 16:30:58.913000', 'jeeva@gmail.com', '2022-12-07 16:30:58.913000', '856566564', '956564', 'New School', 'Jeeva Velu International School', 'Higher Secondary', 'JHARKHAND');

-- ----------------------------
-- Table structure for master_supplier
-- ----------------------------
DROP TABLE IF EXISTS `master_supplier`;
CREATE TABLE `master_supplier`  (
  `supplier_id` bigint NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `balance_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fax_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gst_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gstin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ifsc_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `paid_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pin_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recipt_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_purchased_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_supplier
-- ----------------------------
INSERT INTO `master_supplier` VALUES (1, '955674444799922', 'Bangalore', '0', 'HDFC Bank Ltd', 'BANGALORE', '2022-12-07 16:12:58.750000', 'Panneer867@gmail.com', '', 'Taxable', '22AAAAA0000A1Z5', 'HFDC233T33', '2022-12-07 16:12:58.750000', '9503248348', '56000', '560092', '1092', 'KARNATAKA', 'Panneer', '9347894343', '56000');

-- ----------------------------
-- Table structure for master_unit
-- ----------------------------
DROP TABLE IF EXISTS `master_unit`;
CREATE TABLE `master_unit`  (
  `unit_id` bigint NOT NULL AUTO_INCREMENT,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `unit_of_measure` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of master_unit
-- ----------------------------
INSERT INTO `master_unit` VALUES (1, '2022-12-07 16:07:47.243000', 'Pieces', '2022-12-07 16:07:47.243000', 'pcs');

-- ----------------------------
-- Table structure for password_reset_token
-- ----------------------------
DROP TABLE IF EXISTS `password_reset_token`;
CREATE TABLE `password_reset_token`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs48bkfwgpb8ykmc2t2qadwe89`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKs48bkfwgpb8ykmc2t2qadwe89` FOREIGN KEY (`user_id`) REFERENCES `user_management` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of password_reset_token
-- ----------------------------

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES (1, 'BRANCH_PRIVILEGE');
INSERT INTO `privilege` VALUES (2, 'USER_PRIVILEGE');

-- ----------------------------
-- Table structure for role_privileges
-- ----------------------------
DROP TABLE IF EXISTS `role_privileges`;
CREATE TABLE `role_privileges`  (
  `role_id` bigint NOT NULL,
  `privilege_id` bigint NOT NULL,
  INDEX `FK9bh6h5cm4bq0u3q9pcotkydq8`(`privilege_id` ASC) USING BTREE,
  INDEX `FKjfgsh67m2rbom2r0lsgvus7ns`(`role_id` ASC) USING BTREE,
  CONSTRAINT `FK9bh6h5cm4bq0u3q9pcotkydq8` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjfgsh67m2rbom2r0lsgvus7ns` FOREIGN KEY (`role_id`) REFERENCES `master_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_privileges
-- ----------------------------
INSERT INTO `role_privileges` VALUES (1, 1);
INSERT INTO `role_privileges` VALUES (1, 2);
INSERT INTO `role_privileges` VALUES (2, 1);
INSERT INTO `role_privileges` VALUES (3, 2);

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (1, 'ANDAMAN & NICOBAR ISLANDS');
INSERT INTO `state` VALUES (2, 'ANDHRA PRADESH');
INSERT INTO `state` VALUES (3, 'ASSAM');
INSERT INTO `state` VALUES (4, 'BIHAR');
INSERT INTO `state` VALUES (5, 'CHHATTISGARH');
INSERT INTO `state` VALUES (6, 'DELHI');
INSERT INTO `state` VALUES (7, 'GOA');
INSERT INTO `state` VALUES (8, 'GUJARAT');
INSERT INTO `state` VALUES (9, 'HARYANA');
INSERT INTO `state` VALUES (10, 'HIMACHAL PRADESH');
INSERT INTO `state` VALUES (11, 'JAMMU & KASHMIR');
INSERT INTO `state` VALUES (12, 'JHARKHAND');
INSERT INTO `state` VALUES (13, 'KARNATAKA');
INSERT INTO `state` VALUES (14, 'KERALA');
INSERT INTO `state` VALUES (15, 'MADHYA PRADESH');
INSERT INTO `state` VALUES (16, 'MAHARASHTRA');
INSERT INTO `state` VALUES (17, 'MANIPUR');
INSERT INTO `state` VALUES (18, 'MEGHALAYA');
INSERT INTO `state` VALUES (19, 'MIZORAM');
INSERT INTO `state` VALUES (20, 'NCR');
INSERT INTO `state` VALUES (21, 'NEPAL');
INSERT INTO `state` VALUES (22, 'NEW DELHI');
INSERT INTO `state` VALUES (23, 'ODISHA');
INSERT INTO `state` VALUES (24, 'PUNJAB');
INSERT INTO `state` VALUES (25, 'RAJASTHAN');
INSERT INTO `state` VALUES (26, 'SIKKIM');
INSERT INTO `state` VALUES (27, 'TAMIL NADU');
INSERT INTO `state` VALUES (28, 'TRIPURA');
INSERT INTO `state` VALUES (29, 'UNION TERRITORY of DADRA & NAGAR HAVELI');
INSERT INTO `state` VALUES (30, 'UNION TERRITORY OF LAKSHADWEEP');
INSERT INTO `state` VALUES (31, 'UTTAR PRADESH');
INSERT INTO `state` VALUES (32, 'UTTARAKHAND');
INSERT INTO `state` VALUES (33, 'UTTARANCHAL');
INSERT INTO `state` VALUES (34, 'WEST BENGAL');

-- ----------------------------
-- Table structure for temp_bundle_items
-- ----------------------------
DROP TABLE IF EXISTS `temp_bundle_items`;
CREATE TABLE `temp_bundle_items`  (
  `row_id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint NULL DEFAULT NULL,
  `item_mrp` double NOT NULL,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` bigint NULL DEFAULT NULL,
  `sl_no` bigint NULL DEFAULT NULL,
  `total_cost` double NOT NULL,
  PRIMARY KEY (`row_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temp_bundle_items
-- ----------------------------

-- ----------------------------
-- Table structure for user_management
-- ----------------------------
DROP TABLE IF EXISTS `user_management`;
CREATE TABLE `user_management`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NULL DEFAULT NULL,
  `date_created` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_management
-- ----------------------------
INSERT INTO `user_management` VALUES (1, NULL, '2022-11-24 16:06:01.624000', 'panneer.ingroinfo@gmail.com', 'Panneer', '2022-12-16 17:20:12.145000', '9500931156', '$2a$10$6QeWE0QlpomY6uQtvDN0SOd4ga65FGgINlkSeV0m4a1gRc8IuUTbS', 'COMPANY OWNER', 'Admin');

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  INDEX `FKm4xxvstysyp0i68aaf15g5fr4`(`role_id` ASC) USING BTREE,
  INDEX `FKotrf1jdcnuspv166s88e847or`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKm4xxvstysyp0i68aaf15g5fr4` FOREIGN KEY (`role_id`) REFERENCES `master_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKotrf1jdcnuspv166s88e847or` FOREIGN KEY (`user_id`) REFERENCES `user_management` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
