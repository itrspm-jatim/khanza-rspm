/*
 Navicat Premium Data Transfer

 Source Server         : RSD
 Source Server Type    : MySQL
 Source Server Version : 100414 (10.4.14-MariaDB-log)
 Source Host           : 103.227.24.42:9545
 Source Schema         : sik_27okt

 Target Server Type    : MySQL
 Target Server Version : 100414 (10.4.14-MariaDB-log)
 File Encoding         : 65001

 Date: 28/11/2024 14:21:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fisik_rehab_medik
-- ----------------------------
DROP TABLE IF EXISTS `fisik_rehab_medik`;
CREATE TABLE `fisik_rehab_medik` (
  `no_rawat` varchar(17) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `anamesa` text NOT NULL,
  `fisik_ujifungsi` text NOT NULL,
  `pemeriksaan_penunjang` text NOT NULL,
  `diagnosa_utama` varchar(80) NOT NULL,
  `kd_diagnosa_utama` varchar(10) NOT NULL,
  `diagnosa_sekunder` varchar(255) NOT NULL,
  `kd_diagnosa_sekunder` varchar(10) NOT NULL,
  `diagnosa_sekunder2` varchar(80) NOT NULL,
  `kd_diagnosa_sekunder2` varchar(10) NOT NULL,
  `diagnosa_sekunder3` varchar(80) NOT NULL,
  `kd_diagnosa_sekunder3` varchar(10) NOT NULL,
  `diagnosa_sekunder4` varchar(80) NOT NULL,
  `kd_diagnosa_sekunder4` varchar(10) NOT NULL,
  `prosedur_utama` varchar(255) NOT NULL,
  `kd_prosedur_utama` varchar(8) NOT NULL,
  `prosedur_sekunder` varchar(255) NOT NULL,
  `kd_prosedur_sekunder` varchar(8) NOT NULL,
  `prosedur_sekunder2` varchar(255) NOT NULL,
  `kd_prosedur_sekunder2` varchar(8) NOT NULL,
  `prosedur_sekunder3` varchar(255) NOT NULL,
  `kd_prosedur_sekunder3` varchar(8) NOT NULL,
  `anjuran` text NOT NULL,
  `evaluasi` text NOT NULL,
  `suspek` enum('Tidak','Ya') NOT NULL,
  PRIMARY KEY (`no_rawat`) USING BTREE,
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  CONSTRAINT `fisik_rehab_medik_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fisik_rehab_medik_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
