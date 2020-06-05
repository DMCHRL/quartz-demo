CREATE TABLE `scheduler` (
  `id` int NOT NULL,
  `bean_name` varchar(100) NOT NULL,
  `cron` varchar(100) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;