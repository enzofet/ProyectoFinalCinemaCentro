-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2025 a las 00:14:41
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
(5, 0, 'user', 'user', 'user', '1998-10-23', 1),
(6, 11111111, '1234', 'martin', 'cirio', '2001-03-10', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleticket`
--

CREATE TABLE `detalleticket` (
  `id_ticket` int(11) NOT NULL,
  `id_funcion` int(11) DEFAULT NULL,
  `asiento` varchar(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `fecha_emision` date NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleticket`
--

INSERT INTO `detalleticket` (`id_ticket`, `id_funcion`, `asiento`, `id_venta`, `fecha_emision`, `estado`) VALUES
(9, 6, 'A-1', 6, '2025-11-27', 1),
(10, 6, 'A-2', 7, '2025-11-27', 1),
(11, 6, 'A-3', 7, '2025-11-27', 1),
(12, 6, 'A-4', 7, '2025-11-27', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcion`
--

CREATE TABLE `funcion` (
  `id_funcion` int(11) NOT NULL,
  `id_pelicula` int(11) NOT NULL,
  `nro_sala` int(11) NOT NULL,
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
(5, 3, 3, 'Inglés', 1, '18:00:00', '20:00:00', 5000, '2025-11-26', 1, 1),
(6, 1, 2, 'Español', 0, '19:00:00', '20:30:00', 6000, '2025-11-28', 0, 1);

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
(5, 1, 'Efectivo', 3, 10500, 'Taquilla', NULL, '2025-11-13'),
(6, NULL, 'Efectivo', 1, 6000, 'Taquilla', NULL, '2025-11-27'),
(7, NULL, 'Efectivo', 3, 18000, 'Taquilla', NULL, '2025-11-27');

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
  ADD UNIQUE KEY `UNIQUE_KEY` (`id_funcion`,`id_venta`,`asiento`) USING BTREE,
  ADD KEY `FK_id_funcion` (`id_funcion`),
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
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `funcion`
--
ALTER TABLE `funcion`
  MODIFY `id_funcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `id_pelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD CONSTRAINT `FK_id_funcion` FOREIGN KEY (`id_funcion`) REFERENCES `funcion` (`id_funcion`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_id_venta` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `funcion`
--
ALTER TABLE `funcion`
  ADD CONSTRAINT `FK_id_pelicula` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id_pelicula`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_nro_sala` FOREIGN KEY (`nro_sala`) REFERENCES `sala` (`nro_sala`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FK_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
