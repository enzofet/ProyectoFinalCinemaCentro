-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2025 a las 03:37:59
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cinemacentro`
--
CREATE DATABASE IF NOT EXISTS `cinemacentro` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cinemacentro`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrativo`
--

CREATE TABLE `administrativo` (
  `id_administrativo` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `cargo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrativo`
--

INSERT INTO `administrativo` (`id_administrativo`, `nombre`, `apellido`, `usuario`, `password`, `cargo`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'Administrativo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asiento`
--

CREATE TABLE `asiento` (
  `id_asiento` int(11) NOT NULL,
  `nro_sala` int(11) DEFAULT NULL,
  `numero_asiento` int(11) NOT NULL,
  `fila_asiento` char(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asiento`
--

INSERT INTO `asiento` (`id_asiento`, `nro_sala`, `numero_asiento`, `fila_asiento`, `estado`) VALUES
(1, 1, 1, 'A', 1),
(2, 1, 2, 'A', 1),
(3, 1, 3, 'A', 1),
(4, 1, 4, 'A', 1),
(5, 1, 5, 'A', 1),
(6, 1, 6, 'A', 1),
(7, 1, 7, 'A', 1),
(8, 1, 8, 'A', 1),
(9, 1, 9, 'A', 1),
(10, 1, 10, 'A', 1),
(11, 1, 11, 'A', 1),
(12, 1, 12, 'A', 1),
(13, 1, 13, 'A', 1),
(14, 1, 14, 'A', 1),
(15, 1, 15, 'A', 1),
(16, 1, 16, 'A', 1),
(17, 1, 17, 'A', 1),
(18, 1, 18, 'A', 1),
(19, 1, 19, 'A', 1),
(20, 1, 20, 'A', 1),
(21, 1, 21, 'A', 1),
(22, 1, 22, 'A', 1),
(23, 1, 23, 'A', 1),
(24, 1, 1, 'B', 1),
(25, 1, 2, 'B', 1),
(26, 1, 3, 'B', 1),
(27, 1, 4, 'B', 1),
(28, 1, 5, 'B', 1),
(29, 1, 6, 'B', 1),
(30, 1, 7, 'B', 1),
(31, 1, 8, 'B', 1),
(32, 1, 9, 'B', 1),
(33, 1, 10, 'B', 1),
(34, 1, 11, 'B', 1),
(35, 1, 12, 'B', 1),
(36, 1, 13, 'B', 1),
(37, 1, 14, 'B', 1),
(38, 1, 15, 'B', 1),
(39, 1, 16, 'B', 1),
(40, 1, 17, 'B', 1),
(41, 1, 18, 'B', 1),
(42, 1, 19, 'B', 1),
(43, 1, 20, 'B', 1),
(44, 1, 21, 'B', 1),
(45, 1, 22, 'B', 1),
(46, 1, 23, 'B', 1),
(47, 1, 1, 'C', 1),
(48, 1, 2, 'C', 1),
(49, 1, 3, 'C', 1),
(50, 1, 4, 'C', 1),
(51, 1, 5, 'C', 1),
(52, 1, 6, 'C', 1),
(53, 1, 7, 'C', 1),
(54, 1, 8, 'C', 1),
(55, 1, 9, 'C', 1),
(56, 1, 10, 'C', 1),
(57, 1, 11, 'C', 1),
(58, 1, 12, 'C', 1),
(59, 1, 13, 'C', 1),
(60, 1, 14, 'C', 1),
(61, 1, 15, 'C', 1),
(62, 1, 16, 'C', 1),
(63, 1, 17, 'C', 1),
(64, 1, 18, 'C', 1),
(65, 1, 19, 'C', 1),
(66, 1, 20, 'C', 1),
(67, 1, 21, 'C', 1),
(68, 1, 22, 'C', 1),
(69, 1, 23, 'C', 1),
(70, 1, 1, 'D', 1),
(71, 1, 2, 'D', 1),
(72, 1, 3, 'D', 1),
(73, 1, 4, 'D', 1),
(74, 1, 5, 'D', 1),
(75, 1, 6, 'D', 1),
(76, 1, 7, 'D', 1),
(77, 1, 8, 'D', 1),
(78, 1, 9, 'D', 1),
(79, 1, 10, 'D', 1),
(80, 1, 11, 'D', 1),
(81, 1, 12, 'D', 1),
(82, 1, 13, 'D', 1),
(83, 1, 14, 'D', 1),
(84, 1, 15, 'D', 1),
(85, 1, 16, 'D', 1),
(86, 1, 17, 'D', 1),
(87, 1, 18, 'D', 1),
(88, 1, 19, 'D', 1),
(89, 1, 20, 'D', 1),
(90, 1, 21, 'D', 1),
(91, 1, 22, 'D', 1),
(92, 1, 23, 'D', 1),
(93, 1, 1, 'E', 1),
(94, 1, 2, 'E', 1),
(95, 1, 3, 'E', 1),
(96, 1, 4, 'E', 1),
(97, 1, 5, 'E', 1),
(98, 1, 6, 'E', 1),
(99, 1, 7, 'E', 1),
(100, 1, 8, 'E', 1),
(101, 1, 9, 'E', 1),
(102, 1, 10, 'E', 1),
(103, 1, 11, 'E', 1),
(104, 1, 12, 'E', 1),
(105, 1, 13, 'E', 1),
(106, 1, 14, 'E', 1),
(107, 1, 15, 'E', 1),
(108, 1, 16, 'E', 1),
(109, 1, 17, 'E', 1),
(110, 1, 18, 'E', 1),
(111, 1, 19, 'E', 1),
(112, 1, 20, 'E', 1),
(113, 1, 21, 'E', 1),
(114, 1, 22, 'E', 1),
(115, 1, 23, 'E', 1),
(116, 1, 1, 'F', 1),
(117, 1, 2, 'F', 1),
(118, 1, 3, 'F', 1),
(119, 1, 4, 'F', 1),
(120, 1, 5, 'F', 1),
(121, 1, 6, 'F', 1),
(122, 1, 7, 'F', 1),
(123, 1, 8, 'F', 1),
(124, 1, 9, 'F', 1),
(125, 1, 10, 'F', 1),
(126, 1, 11, 'F', 1),
(127, 1, 12, 'F', 1),
(128, 1, 13, 'F', 1),
(129, 1, 14, 'F', 1),
(130, 1, 15, 'F', 1),
(131, 1, 16, 'F', 1),
(132, 1, 17, 'F', 1),
(133, 1, 18, 'F', 1),
(134, 1, 19, 'F', 1),
(135, 1, 20, 'F', 1),
(136, 1, 21, 'F', 1),
(137, 1, 22, 'F', 1),
(138, 1, 23, 'F', 0),
(139, 1, 1, 'G', 1),
(140, 1, 2, 'G', 1),
(141, 1, 3, 'G', 1),
(142, 1, 4, 'G', 1),
(143, 1, 5, 'G', 1),
(144, 1, 6, 'G', 1),
(145, 1, 7, 'G', 1),
(146, 1, 8, 'G', 1),
(147, 1, 9, 'G', 1),
(148, 1, 10, 'G', 1),
(149, 1, 11, 'G', 1),
(150, 1, 12, 'G', 1),
(151, 1, 13, 'G', 1),
(152, 1, 14, 'G', 1),
(153, 1, 15, 'G', 1),
(154, 1, 16, 'G', 1),
(155, 1, 17, 'G', 1),
(156, 1, 18, 'G', 1),
(157, 1, 19, 'G', 1),
(158, 1, 20, 'G', 1),
(159, 1, 21, 'G', 1),
(160, 1, 22, 'G', 1),
(161, 1, 23, 'G', 0),
(162, 1, 1, 'H', 1),
(163, 1, 2, 'H', 1),
(164, 1, 3, 'H', 1),
(165, 1, 4, 'H', 1),
(166, 1, 5, 'H', 1),
(167, 1, 6, 'H', 1),
(168, 1, 7, 'H', 1),
(169, 1, 8, 'H', 0),
(170, 1, 9, 'H', 0),
(173, 2, 1, 'A', 1),
(174, 2, 2, 'A', 1),
(175, 2, 3, 'A', 1),
(176, 2, 4, 'A', 1),
(177, 2, 5, 'A', 1),
(178, 2, 6, 'A', 1),
(179, 2, 7, 'A', 1),
(180, 2, 8, 'A', 1),
(181, 2, 9, 'A', 1),
(182, 2, 10, 'A', 1),
(183, 2, 11, 'A', 1),
(184, 2, 12, 'A', 1),
(185, 2, 13, 'A', 1),
(186, 2, 14, 'A', 1),
(187, 2, 15, 'A', 1),
(188, 2, 16, 'A', 1),
(189, 2, 17, 'A', 1),
(190, 2, 18, 'A', 1),
(191, 2, 19, 'A', 1),
(192, 2, 20, 'A', 1),
(193, 2, 21, 'A', 1),
(194, 2, 22, 'A', 1),
(195, 2, 23, 'A', 1),
(196, 2, 1, 'B', 1),
(197, 2, 2, 'B', 1),
(198, 2, 3, 'B', 1),
(199, 2, 4, 'B', 1),
(200, 2, 5, 'B', 1),
(201, 2, 6, 'B', 1),
(202, 2, 7, 'B', 1),
(203, 2, 8, 'B', 1),
(204, 2, 9, 'B', 1),
(205, 2, 10, 'B', 1),
(206, 2, 11, 'B', 1),
(207, 2, 12, 'B', 1),
(208, 2, 13, 'B', 1),
(209, 2, 14, 'B', 1),
(210, 2, 15, 'B', 1),
(211, 2, 16, 'B', 1),
(212, 2, 17, 'B', 1),
(213, 2, 18, 'B', 1),
(214, 2, 19, 'B', 1),
(215, 2, 20, 'B', 1),
(216, 2, 21, 'B', 1),
(217, 2, 22, 'B', 1),
(218, 2, 23, 'B', 1),
(219, 2, 1, 'C', 1),
(220, 2, 2, 'C', 1),
(221, 2, 3, 'C', 1),
(222, 2, 4, 'C', 1),
(223, 2, 5, 'C', 1),
(224, 2, 6, 'C', 1),
(225, 2, 7, 'C', 1),
(226, 2, 8, 'C', 1),
(227, 2, 9, 'C', 1),
(228, 2, 10, 'C', 1),
(229, 2, 11, 'C', 1),
(230, 2, 12, 'C', 1),
(231, 2, 13, 'C', 1),
(232, 2, 14, 'C', 1),
(233, 2, 15, 'C', 1),
(234, 2, 16, 'C', 1),
(235, 2, 17, 'C', 1),
(236, 2, 18, 'C', 1),
(237, 2, 19, 'C', 1),
(238, 2, 20, 'C', 1),
(239, 2, 21, 'C', 1),
(240, 2, 22, 'C', 1),
(241, 2, 23, 'C', 1),
(242, 2, 1, 'D', 1),
(243, 2, 2, 'D', 1),
(244, 2, 3, 'D', 1),
(245, 2, 4, 'D', 1),
(246, 2, 5, 'D', 1),
(247, 2, 6, 'D', 1),
(248, 2, 7, 'D', 1),
(249, 2, 8, 'D', 1),
(250, 2, 9, 'D', 1),
(251, 2, 10, 'D', 1),
(252, 2, 11, 'D', 1),
(253, 2, 12, 'D', 1),
(254, 2, 13, 'D', 1),
(255, 2, 14, 'D', 1),
(256, 2, 15, 'D', 1),
(257, 2, 16, 'D', 1),
(258, 2, 17, 'D', 1),
(259, 2, 18, 'D', 1),
(260, 2, 19, 'D', 1),
(261, 2, 20, 'D', 1),
(262, 2, 21, 'D', 1),
(263, 2, 22, 'D', 1),
(264, 2, 23, 'D', 1),
(265, 2, 1, 'E', 1),
(266, 2, 2, 'E', 1),
(267, 2, 3, 'E', 1),
(268, 2, 4, 'E', 1),
(269, 2, 5, 'E', 1),
(270, 2, 6, 'E', 1),
(271, 2, 7, 'E', 1),
(272, 2, 8, 'E', 1),
(273, 2, 9, 'E', 1),
(274, 2, 10, 'E', 1),
(275, 2, 11, 'E', 1),
(276, 2, 12, 'E', 1),
(277, 2, 13, 'E', 1),
(278, 2, 14, 'E', 1),
(279, 2, 15, 'E', 1),
(280, 2, 16, 'E', 1),
(281, 2, 17, 'E', 1),
(282, 2, 18, 'E', 1),
(283, 2, 19, 'E', 1),
(284, 2, 20, 'E', 1),
(285, 2, 21, 'E', 1),
(286, 2, 22, 'E', 1),
(287, 2, 23, 'E', 1),
(288, 2, 1, 'F', 1),
(289, 2, 2, 'F', 1),
(290, 2, 3, 'F', 1),
(291, 2, 4, 'F', 1),
(292, 2, 5, 'F', 1),
(293, 2, 6, 'F', 1),
(294, 2, 7, 'F', 1),
(295, 2, 8, 'F', 1),
(296, 2, 9, 'F', 1),
(297, 2, 10, 'F', 1),
(298, 2, 11, 'F', 1),
(299, 2, 12, 'F', 1),
(300, 2, 13, 'F', 1),
(301, 2, 14, 'F', 1),
(302, 2, 15, 'F', 1),
(303, 2, 16, 'F', 1),
(304, 2, 17, 'F', 1),
(305, 2, 18, 'F', 1),
(306, 2, 19, 'F', 1),
(307, 2, 20, 'F', 1),
(308, 2, 21, 'F', 1),
(309, 2, 22, 'F', 1),
(310, 2, 23, 'F', 1),
(311, 2, 1, 'G', 1),
(312, 2, 2, 'G', 1),
(313, 2, 3, 'G', 1),
(314, 2, 4, 'G', 1),
(315, 2, 5, 'G', 1),
(316, 2, 6, 'G', 1),
(317, 2, 7, 'G', 1),
(318, 2, 8, 'G', 1),
(319, 2, 9, 'G', 1),
(320, 2, 10, 'G', 1),
(321, 2, 11, 'G', 1),
(322, 2, 12, 'G', 1),
(323, 2, 13, 'G', 1),
(324, 2, 14, 'G', 1),
(325, 2, 15, 'G', 1),
(326, 2, 16, 'G', 1),
(327, 2, 17, 'G', 1),
(328, 2, 18, 'G', 1),
(329, 2, 19, 'G', 1),
(330, 2, 20, 'G', 1),
(331, 2, 21, 'G', 1),
(332, 2, 22, 'G', 1),
(333, 2, 23, 'G', 1),
(334, 2, 1, 'H', 1),
(335, 2, 2, 'H', 1),
(336, 2, 3, 'H', 1),
(337, 2, 4, 'H', 1),
(338, 2, 5, 'H', 1),
(339, 2, 6, 'H', 1),
(340, 2, 7, 'H', 1),
(341, 2, 8, 'H', 1),
(342, 2, 9, 'H', 1),
(343, 2, 10, 'H', 1),
(344, 2, 11, 'H', 1),
(345, 2, 12, 'H', 1),
(346, 2, 13, 'H', 1),
(347, 2, 14, 'H', 1),
(348, 2, 15, 'H', 1),
(349, 2, 16, 'H', 1),
(350, 2, 17, 'H', 1),
(351, 2, 18, 'H', 1),
(352, 2, 19, 'H', 1),
(353, 2, 20, 'H', 1),
(354, 2, 21, 'H', 1),
(355, 2, 22, 'H', 1),
(356, 2, 23, 'H', 1),
(357, 2, 1, 'I', 1),
(358, 2, 2, 'I', 1),
(359, 2, 3, 'I', 1),
(360, 2, 4, 'I', 1),
(361, 2, 5, 'I', 1),
(362, 2, 6, 'I', 1),
(363, 3, 1, 'A', 0),
(364, 3, 2, 'A', 0),
(365, 3, 3, 'A', 0),
(366, 3, 4, 'A', 0),
(367, 3, 5, 'A', 0),
(368, 3, 6, 'A', 0),
(369, 3, 7, 'A', 0),
(370, 3, 8, 'A', 0),
(371, 3, 9, 'A', 0),
(372, 3, 10, 'A', 0),
(373, 3, 11, 'A', 0),
(374, 3, 12, 'A', 0),
(375, 3, 13, 'A', 1),
(376, 3, 14, 'A', 1),
(377, 3, 15, 'A', 1),
(378, 3, 16, 'A', 1),
(379, 3, 17, 'A', 1),
(380, 3, 18, 'A', 1),
(381, 3, 19, 'A', 1),
(382, 3, 20, 'A', 1),
(383, 3, 21, 'A', 1),
(384, 3, 22, 'A', 1),
(385, 3, 23, 'A', 1),
(386, 3, 1, 'B', 1),
(387, 3, 2, 'B', 1),
(388, 3, 3, 'B', 1),
(389, 3, 4, 'B', 1),
(390, 3, 5, 'B', 1),
(391, 3, 6, 'B', 1),
(392, 3, 7, 'B', 1),
(393, 3, 8, 'B', 1),
(394, 3, 9, 'B', 1),
(395, 3, 10, 'B', 1),
(396, 3, 11, 'B', 1),
(397, 3, 12, 'B', 1),
(398, 3, 13, 'B', 1),
(399, 3, 14, 'B', 1),
(400, 3, 15, 'B', 1),
(401, 3, 16, 'B', 1),
(402, 3, 17, 'B', 1),
(403, 3, 18, 'B', 1),
(404, 3, 19, 'B', 1),
(405, 3, 20, 'B', 1),
(406, 3, 21, 'B', 1),
(407, 3, 22, 'B', 1),
(408, 3, 23, 'B', 1),
(409, 3, 1, 'C', 1),
(410, 3, 2, 'C', 1),
(411, 3, 3, 'C', 1),
(412, 3, 4, 'C', 1),
(413, 3, 5, 'C', 1),
(414, 3, 6, 'C', 1),
(415, 3, 7, 'C', 1),
(416, 3, 8, 'C', 1),
(417, 3, 9, 'C', 1),
(418, 3, 10, 'C', 1),
(419, 3, 11, 'C', 1),
(420, 3, 12, 'C', 1),
(421, 3, 13, 'C', 1),
(422, 3, 14, 'C', 1),
(423, 3, 15, 'C', 1),
(424, 3, 16, 'C', 1),
(425, 3, 17, 'C', 1),
(426, 3, 18, 'C', 1),
(427, 3, 19, 'C', 1),
(428, 3, 20, 'C', 1),
(429, 3, 21, 'C', 1),
(430, 3, 22, 'C', 1),
(431, 3, 23, 'C', 1),
(432, 3, 1, 'D', 1),
(433, 3, 2, 'D', 1),
(434, 3, 3, 'D', 1),
(435, 3, 4, 'D', 1),
(436, 3, 5, 'D', 1),
(437, 3, 6, 'D', 1),
(438, 3, 7, 'D', 1),
(439, 3, 8, 'D', 1),
(440, 3, 9, 'D', 1),
(441, 3, 10, 'D', 1),
(442, 3, 11, 'D', 1),
(443, 3, 12, 'D', 1),
(444, 3, 13, 'D', 1),
(445, 3, 14, 'D', 1),
(446, 3, 15, 'D', 1),
(447, 3, 16, 'D', 1),
(448, 3, 17, 'D', 1),
(449, 3, 18, 'D', 1),
(450, 3, 19, 'D', 1),
(451, 3, 20, 'D', 1),
(452, 3, 21, 'D', 1),
(453, 3, 22, 'D', 1),
(454, 3, 23, 'D', 1),
(455, 3, 1, 'E', 1),
(456, 3, 2, 'E', 1),
(457, 3, 3, 'E', 1),
(458, 3, 4, 'E', 1),
(459, 3, 5, 'E', 1),
(460, 3, 6, 'E', 1),
(461, 3, 7, 'E', 1),
(462, 3, 8, 'E', 1),
(463, 3, 9, 'E', 1),
(464, 3, 10, 'E', 1),
(465, 3, 11, 'E', 1),
(466, 3, 12, 'E', 1),
(467, 3, 13, 'E', 1),
(468, 3, 14, 'E', 1),
(469, 3, 15, 'E', 1),
(470, 3, 16, 'E', 1),
(471, 3, 17, 'E', 1),
(472, 3, 18, 'E', 1),
(473, 3, 19, 'E', 1),
(474, 3, 20, 'E', 1),
(475, 3, 21, 'E', 1),
(476, 3, 22, 'E', 1),
(477, 3, 23, 'E', 1),
(478, 3, 1, 'F', 1),
(479, 3, 2, 'F', 1),
(480, 3, 3, 'F', 1),
(481, 3, 4, 'F', 1),
(482, 3, 5, 'F', 1),
(483, 3, 6, 'F', 1),
(484, 3, 7, 'F', 1),
(485, 3, 8, 'F', 1),
(486, 3, 9, 'F', 1),
(487, 3, 10, 'F', 1),
(488, 3, 11, 'F', 1),
(489, 3, 12, 'F', 1),
(490, 3, 13, 'F', 1),
(491, 3, 14, 'F', 1),
(492, 3, 15, 'F', 1),
(493, 3, 16, 'F', 1),
(494, 3, 17, 'F', 1),
(495, 3, 18, 'F', 1),
(496, 3, 19, 'F', 1),
(497, 3, 20, 'F', 1),
(498, 3, 21, 'F', 1),
(499, 3, 22, 'F', 1),
(500, 3, 23, 'F', 1),
(501, 3, 1, 'G', 1),
(502, 3, 2, 'G', 1),
(503, 3, 3, 'G', 1),
(504, 3, 4, 'G', 1),
(505, 3, 5, 'G', 1),
(506, 3, 6, 'G', 1),
(507, 3, 7, 'G', 1),
(508, 3, 8, 'G', 1),
(509, 3, 9, 'G', 1),
(510, 3, 10, 'G', 1),
(511, 3, 11, 'G', 1),
(512, 3, 12, 'G', 1),
(513, 3, 13, 'G', 1),
(514, 3, 14, 'G', 1),
(515, 3, 15, 'G', 1),
(516, 3, 16, 'G', 1),
(517, 3, 17, 'G', 1),
(518, 3, 18, 'G', 1),
(519, 3, 19, 'G', 1),
(520, 3, 20, 'G', 1),
(521, 3, 21, 'G', 1),
(522, 3, 22, 'G', 1),
(523, 3, 23, 'G', 1),
(524, 3, 1, 'H', 1),
(525, 3, 2, 'H', 1),
(526, 3, 3, 'H', 1),
(527, 3, 4, 'H', 1),
(528, 3, 5, 'H', 1),
(529, 3, 6, 'H', 1),
(530, 3, 7, 'H', 1),
(531, 3, 8, 'H', 1),
(532, 3, 9, 'H', 1),
(533, 3, 10, 'H', 1),
(534, 3, 11, 'H', 1),
(535, 3, 12, 'H', 1),
(536, 3, 13, 'H', 1),
(537, 3, 14, 'H', 1),
(538, 3, 15, 'H', 1),
(539, 3, 16, 'H', 1),
(540, 3, 17, 'H', 1),
(541, 3, 18, 'H', 1),
(542, 3, 19, 'H', 1),
(543, 3, 20, 'H', 1),
(544, 3, 21, 'H', 1),
(545, 3, 22, 'H', 1),
(546, 3, 23, 'H', 1),
(547, 3, 1, 'I', 1),
(548, 3, 2, 'I', 1),
(549, 3, 3, 'I', 1),
(550, 3, 4, 'I', 1),
(551, 3, 5, 'I', 1),
(552, 3, 6, 'I', 1),
(553, 3, 7, 'I', 1),
(554, 3, 8, 'I', 1),
(555, 3, 9, 'I', 1),
(556, 3, 10, 'I', 1),
(557, 3, 11, 'I', 1),
(558, 3, 12, 'I', 1),
(559, 3, 13, 'I', 1),
(560, 3, 14, 'I', 1),
(561, 3, 15, 'I', 1),
(562, 3, 16, 'I', 1),
(563, 3, 17, 'I', 1),
(564, 3, 18, 'I', 1),
(565, 3, 19, 'I', 1),
(566, 3, 20, 'I', 1),
(567, 3, 21, 'I', 1),
(568, 3, 22, 'I', 1),
(569, 3, 23, 'I', 1),
(570, 3, 1, 'J', 1),
(571, 3, 2, 'J', 1),
(572, 3, 3, 'J', 1),
(573, 3, 4, 'J', 1),
(574, 3, 5, 'J', 1),
(575, 3, 6, 'J', 1),
(576, 3, 7, 'J', 1),
(577, 3, 8, 'J', 1),
(578, 3, 9, 'J', 1),
(579, 3, 10, 'J', 1),
(580, 3, 11, 'J', 1),
(581, 3, 12, 'J', 1),
(582, 3, 13, 'J', 1),
(583, 3, 14, 'J', 1),
(584, 3, 15, 'J', 1),
(585, 3, 16, 'J', 1),
(586, 3, 17, 'J', 1),
(587, 3, 18, 'J', 1),
(588, 3, 19, 'J', 1),
(589, 3, 20, 'J', 1),
(590, 3, 21, 'J', 1),
(591, 3, 22, 'J', 1),
(592, 3, 23, 'J', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `dni` int(11) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `dni`, `password`, `nombre`, `apellido`, `fecha_nacimiento`, `estado`) VALUES
(1, 40111222, '123', 'Juan', 'Perez', '1990-06-10', 1),
(2, 39222333, 'abcd', 'Maria', 'Gomez', '1988-11-22', 1),
(5, 0, 'user', 'user', 'user', '1998-10-23', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleticket`
--

CREATE TABLE `detalleticket` (
  `id_ticket` int(11) NOT NULL,
  `id_funcion` int(11) DEFAULT NULL,
  `id_asiento` int(11) DEFAULT NULL,
  `id_venta` int(11) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleticket`
--

INSERT INTO `detalleticket` (`id_ticket`, `id_funcion`, `id_asiento`, `id_venta`, `fecha_emision`, `estado`) VALUES
(1, NULL, 592, 5, '2025-11-13', 1),
(2, NULL, 592, 5, '2025-11-13', 1),
(3, NULL, 592, 5, '2025-11-13', 1),
(5, NULL, 592, 5, '2025-11-13', 1),
(6, 2, 177, 5, '2025-11-13', 1),
(7, 4, 169, 5, '2025-11-13', 0),
(8, 1, 170, 5, '2025-11-13', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcion`
--

CREATE TABLE `funcion` (
  `id_funcion` int(11) NOT NULL,
  `id_pelicula` int(11) DEFAULT NULL,
  `nro_sala` int(11) DEFAULT NULL,
  `idioma` varchar(30) NOT NULL,
  `es3D` tinyint(1) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `precio_entrada` double NOT NULL,
  `fecha_funcion` date NOT NULL,
  `subtitulada` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `funcion`
--

INSERT INTO `funcion` (`id_funcion`, `id_pelicula`, `nro_sala`, `idioma`, `es3D`, `hora_inicio`, `hora_fin`, `precio_entrada`, `fecha_funcion`, `subtitulada`, `estado`) VALUES
(1, 1, 1, 'Español', 0, '18:00:00', '19:45:00', 2500, '2025-11-15', 0, 1),
(2, 1, 2, 'Inglés', 0, '19:00:00', '20:30:00', 3500, '2025-11-20', 1, 1),
(4, 2, 1, 'Español', 0, '17:00:00', '19:00:00', 5000, '2025-01-06', 0, 1),
(5, 3, 3, 'Inglés', 1, '18:00:00', '20:00:00', 5000, '2025-11-26', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `id_pelicula` int(11) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `director` varchar(100) NOT NULL,
  `reparto` text NOT NULL,
  `pais_origen` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `enCartelera` tinyint(1) NOT NULL,
  `estreno` date NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`id_pelicula`, `titulo`, `director`, `reparto`, `pais_origen`, `genero`, `enCartelera`, `estreno`, `estado`) VALUES
(1, 'Coco', 'Lee Unkrich', 'Anthony Gonzalez, Gael García Bernal, Benjamin Bratt', 'Estados Unidos', 'Animación', 1, '2017-10-27', 1),
(2, 'Parasite', 'Bong Joon-ho', 'Song Kang-ho, Choi Woo-shik, Park So-dam', 'Corea del Sur', 'Drama', 1, '2019-05-30', 1),
(3, 'DEPREDADOR: TIERRAS SALVAJES', 'Dan Trachtenberg', 'Elle Fanning, Dimitrius Koloamatangi', 'Estados Unidos', 'Thriller', 0, '2025-11-16', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `nro_sala` int(11) NOT NULL,
  `capacidad` smallint(6) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `apta3D` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`nro_sala`, `capacidad`, `estado`, `apta3D`) VALUES
(1, 180, 1, 0),
(2, 190, 1, 0),
(3, 230, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `medio_pago` varchar(30) NOT NULL,
  `cantidad_entradas` int(11) NOT NULL,
  `importe_total` double NOT NULL,
  `medio_compra` enum('Online','Taquilla') NOT NULL,
  `token` bigint(20) DEFAULT NULL,
  `fecha_venta` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `id_cliente`, `medio_pago`, `cantidad_entradas`, `importe_total`, `medio_compra`, `token`, `fecha_venta`) VALUES
(1, 2, 'Debito', 2, 5000, 'Online', 15615156, '2025-10-17'),
(2, 1, 'Efectivo', 3, 15000, 'Taquilla', NULL, '2025-11-28'),
(3, NULL, 'Efectivo', 5, 15000, 'Taquilla', NULL, '2025-11-13'),
(5, 1, 'Efectivo', 3, 10500, 'Taquilla', NULL, '2025-11-13');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrativo`
--
ALTER TABLE `administrativo`
  ADD PRIMARY KEY (`id_administrativo`),
  ADD UNIQUE KEY `UNIQUE_USUARIO` (`usuario`);

--
-- Indices de la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD PRIMARY KEY (`id_asiento`) USING BTREE,
  ADD UNIQUE KEY `uk_asiento_sala` (`fila_asiento`,`numero_asiento`,`nro_sala`) USING BTREE,
  ADD KEY `FK_nro_s` (`nro_sala`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD PRIMARY KEY (`id_ticket`),
  ADD KEY `FK_id_funcion` (`id_funcion`),
  ADD KEY `FK_id_asiento` (`id_asiento`),
  ADD KEY `FK_id_venta` (`id_venta`);

--
-- Indices de la tabla `funcion`
--
ALTER TABLE `funcion`
  ADD PRIMARY KEY (`id_funcion`),
  ADD UNIQUE KEY `UNIQUE_KEY` (`id_funcion`,`id_pelicula`,`nro_sala`) USING BTREE,
  ADD KEY `FK_id_pelicula` (`id_pelicula`),
  ADD KEY `FK_nro_sala` (`nro_sala`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`id_pelicula`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`nro_sala`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `FK_id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrativo`
--
ALTER TABLE `administrativo`
  MODIFY `id_administrativo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `asiento`
--
ALTER TABLE `asiento`
  MODIFY `id_asiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=593;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `funcion`
--
ALTER TABLE `funcion`
  MODIFY `id_funcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `id_pelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD CONSTRAINT `FK_nro_s` FOREIGN KEY (`nro_sala`) REFERENCES `sala` (`nro_sala`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD CONSTRAINT `FK_id_asiento` FOREIGN KEY (`id_asiento`) REFERENCES `asiento` (`id_asiento`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_id_funcion` FOREIGN KEY (`id_funcion`) REFERENCES `funcion` (`id_funcion`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_id_venta` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `funcion`
--
ALTER TABLE `funcion`
  ADD CONSTRAINT `FK_id_pelicula` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id_pelicula`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_nro_sala` FOREIGN KEY (`nro_sala`) REFERENCES `sala` (`nro_sala`) ON DELETE SET NULL;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FK_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
